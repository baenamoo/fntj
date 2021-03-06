package com.mbeti.controller;

import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mbeti.domain.MemberVO;
import com.mbeti.domain.PageMaker;
import com.mbeti.domain.SearchCriteria;
import com.mbeti.service.BoardService;
import com.mbeti.service.FreeBoardService;
import com.mbeti.service.MemberService;

@Controller
@RequestMapping("/user/*")
public class MemberController {

   private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
   
   @Inject
   BoardService boardService;
   
   @Inject
   MemberService service;
   
   @Inject
   FreeBoardService fbservice;
   
   @Inject
	BCryptPasswordEncoder pwdEncoder;
   
   @Autowired
   private JavaMailSender mailSender;	
   
// 회원가입 get
@RequestMapping(value = "/user/register", method = RequestMethod.GET)
public void getRegister() throws Exception {
   logger.info("get register");
}
//회원가입 agree 
@RequestMapping(value = "/user/register_agree", method = RequestMethod.GET)
public void getRegister_agree() throws Exception {
   logger.info("get register_agree");
}

//회원가입 agree 
@RequestMapping(value = "/user/register_success", method = RequestMethod.GET)
public void getRegister_success() throws Exception {
   logger.info("get register_success");
}

// 아이디 중복 체크
@ResponseBody
@RequestMapping(value="/user/idChk", method = RequestMethod.POST)
public int idChk(MemberVO vo) throws Exception {
   int result = service.idChk(vo);
   return result;
}

//닉네임 중복 체크
@ResponseBody
@RequestMapping(value="/user/nameChk", method = RequestMethod.POST)
public int nameChk(MemberVO vo) throws Exception {
   int result = service.nameChk(vo);
   return result;
}

// 회원가입 post
@RequestMapping(value = "/user/register", method = RequestMethod.POST)
public String postRegister(MemberVO vo) throws Exception {
   logger.info("post register");
   int result = service.idChk(vo);
   int result2 = service.nameChk(vo);
   
   try {
      if(result == 1 || result2 == 1) {
         return "/user/register";
      }else if(result == 0 || result2 == 0) {
    	 String inputPass = vo.getUserPassword(); 
    	 String pwd = pwdEncoder.encode(inputPass);
    	 vo.setUserPassword(pwd);
    	 
         service.register(vo);
      }
      // 요기에서~ 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기 
      // 존재하지 않는다면 -> register
   } catch (Exception e) {
      throw new RuntimeException();
   }
   return "redirect:/user/register_success";
}

//로그인 POST
@RequestMapping(value = "/user/login", method = RequestMethod.POST)
public String login(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
	logger.info("post login");

	session.getAttribute("member");
	MemberVO login = service.login(vo);
	boolean pwdMatch;
	if(login != null) {
		pwdMatch = pwdEncoder.matches(vo.getUserPassword(), login.getUserPassword());
	} else {
		pwdMatch = false;
	}

	if(login != null && pwdMatch == true) {
		session.setAttribute("member", login);
	} else {
		session.setAttribute("member", null);
		rttr.addFlashAttribute("msg", false);
	}
	
	return "redirect:/login";
}
//로그인 GET
@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
public String logout(HttpSession session) throws Exception{
   
   session.invalidate();
   
   return "redirect:/";
}

//회원정보수정 뷰
@RequestMapping(value="/user/memberUpdateView", method = RequestMethod.GET)
public String registerUpdateView() throws Exception{
   
   return "/user/memberUpdateView";
}

//회원정보수정
@RequestMapping(value="/user/memberUpdate", method = RequestMethod.POST)
public String registerUpdate(MemberVO vo, HttpSession session) throws Exception{
	MemberVO login = service.login(vo);
	
	String inputPass = vo.getUserPassword(); 
	String pwd = pwdEncoder.encode(inputPass);
	vo.setUserPassword(pwd);
	
	boolean pwdMatch = pwdEncoder.matches(vo.getUserPassword(), login.getUserPassword());
	if(!pwdMatch) {
		service.memberUpdate(vo);
		login = service.login(vo);
		session.setAttribute("member", login);
		//session.invalidate();
	}
	
	return "redirect:/user/memberProfile";
}

// 회원 탈퇴 get
@RequestMapping(value="/user/memberDeleteView", method = RequestMethod.GET)
public String memberDeleteView() throws Exception{
   return "/user/memberDeleteView";
}

// 회원 탈퇴 post
@RequestMapping(value="/user/memberDelete", method = RequestMethod.POST)
public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
   
   service.memberDelete(vo);
   session.invalidate();
   return "redirect:/";
}

   // 패스워드 체크
   @ResponseBody
   @RequestMapping(value="/user/passChk", method = RequestMethod.POST)
   public boolean passChk(MemberVO vo) throws Exception {

		MemberVO login = service.login(vo);
		boolean pwdChk = pwdEncoder.matches(vo.getUserPassword(), login.getUserPassword());
		return pwdChk;
	}
   
   
   // 회원프로필
   @RequestMapping(value = "/user/memberProfile", method = RequestMethod.GET)
   public String memberProfile() {
      return "/user/memberProfile";
   }
   
   /*
    * // 회원작성글 // 공지사항
    * 
    * @RequestMapping(value = "/user/memberBoard", method = RequestMethod.GET)
    * public String list(Model model, @ModelAttribute("scri") SearchCriteria scri)
    * throws Exception{ logger.info("list");
    * 
    * model.addAttribute("list", boardService.list(scri));
    * 
    * PageMaker pageMaker = new PageMaker(); pageMaker.setCri(scri);
    * pageMaker.setTotalCount(boardService.listCount(scri));
    * 
    * model.addAttribute("pageMaker", pageMaker);
    * 
    * return "/user/memberBoard";
    * 
    * }
    */
   
   // 회원작성글 // 커뮤니티
   @RequestMapping(value = "/user/memberBoard", method = RequestMethod.GET)
   public String list(Model model,@ModelAttribute("scri") SearchCriteria scri) throws Exception{
      logger.info("fbList");
      
      model.addAttribute("fbList",fbservice.fbList(scri));
      
      PageMaker pageMaker = new PageMaker();
      pageMaker.setCri(scri);
      pageMaker.setTotalCount(fbservice.listCount(scri));
      
      model.addAttribute("pageMaker", pageMaker);
      model.addAttribute("fileList",fbservice.fileList());
      
      return "/user/memberBoard";
      
   }
   
   /* 이메일 인증 */
	@RequestMapping(value="/mailCheck", method=RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception{
		
		/* 뷰(View)로부터 넘어온 데이터 확인 */
		logger.info("이메일 데이터 전송 확인");
		logger.info("이메일 : " + email);
				
		/* 인증번호(난수) 생성 */
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		logger.info("인증번호 " + checkNum);
		
		/* 이메일 보내기 */
		String setFrom = "fntjambox@gmail.com";
		String toMail = email;
		String title = "jambox 회원가입 인증 이메일 입니다."; // 메일제목
		String content = 						// 메일내용
				"<div style='border:5px solid #ff665b;display:inline-block;padding:30px;text-align:center;font-family:noto sans;'>" +
				"<strong style='display:block;font-size:16px;'>" + "jambox 홈페이지를 방문해주셔서 감사합니다." + "</strong>" +
				"<br>" + 
				"인증 번호는 " + "<strong style='color:#ff665b;font-size:16px;'>" + checkNum + "</strong>" + "입니다." +				
				"<br>" + 
				"해당 인증번호를 인증번호 확인란에 기입하여 주세요." +
				"</div>";		
		

		try {
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content,true);
			mailSender.send(message);
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		String num = Integer.toString(checkNum);
		
		return num;
		
	}


   
}