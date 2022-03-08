package mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mall.domain.MemberDto;
import mall.service.MemberService;

@Controller
public class MemberController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MemberService memberService;
	/*
	 * @RequestMapping("/member/openMemberList.do") public String
	 * openMemberList(@RequestBody MemberDto memberDto, Model model) throws
	 * Exception { log.debug("openMemberList");
	 * 
	 * // model.addAttribute("memberInfo", memberDto); return "/member/memberList";
	 * }
	 */

	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "Security-StatusCheck (3)";
	}

	@RequestMapping("/denine")
	public @ResponseBody String denine() throws Exception {
		return "다시 로그인 해 주십시오.";
	}

	@RequestMapping("/guest/welcome")
	public String welcome1() {

		return "guest/welcome1";
	}

	@RequestMapping("/member/welcome")
	public String welcome2() {

		return "member/welcome2";
	}

	@RequestMapping("/admin/welcome")
	public String welcome3() {
		System.out.println("???");
		return "admin/welcome3";
	}

	@RequestMapping("/loginForm1")
	public String loginForm() {

		return "security/loginForm";
	}

	@RequestMapping("/admin/openMemberListAgGrid.do")
	public String openMemberListAgGrid(Model model) throws Exception {

		// log.debug("openMemberList"); List<MemberDto> list =
		memberService.selectMemberListAgGrid();
		model.addAttribute("detailPage", "/member/memberDetail.do");
		model.addAttribute("list", list);
		return "/member/memberList-AG";
	}

	@GetMapping("/admin/openMemberList.do")
	public String openMemberList(@ModelAttribute("params") MemberDto memberDto, Model model) throws Exception {
		List<MemberDto> list = memberService.selectMemberList(memberDto);
		System.out.println(list);
		model.addAttribute("list", list); // model.addAttribute("params", memberDto);
		model.addAttribute("detailPage", "/member/memberDetail.do");
		model.addAttribute("getPage", "/member/getUserList.do");
		return "/member/memberList";
	}

	@RequestMapping("/admih/getUserList.do")

	@ResponseBody
	public List<MemberDto> getUserList(@ModelAttribute("params") MemberDto memberDto) throws Exception {

		List<MemberDto> list = memberService.selectMemberList(memberDto);
		return list;
	}

	@RequestMapping("/admin/openMemberJoin.do")
	public String openMemberJoin() throws Exception {
		return "/member/memberJoin";
	}

	@RequestMapping("/admin/insertMember.do")
	public String insertMember(MemberDto memberDto) throws Exception { //
		System.out.println(memberDto);
		memberService.insertMember(memberDto);
		System.out.println(memberDto.getUsridx());
		if (memberDto.getUsridx() > 0) {
			return "redirect:/member/openMemberList.do";
		} else {
			return "redirect:/member/insertMember.do";
		}

	}

	@RequestMapping("/admin/memberDetail.do")
	public String memberDatil(@RequestParam("user_id") String userid, Model model) throws  Exception {
		log.debug("memberDatil");
		System.out.println("**********************************************************"); // return
		memberService.selectMemberDetail(userid);
	  
	  if (userid == null) { return "redirect:/member/openMemberList.do"; }
	  
	  MemberDto memberDto = memberService.selectMemberDetail(userid);
	  if (memberDto == null || "3".equals(memberDto.getUserauth().toString()) || "4".equals(memberDto.getUserauth().toString())) {
		  // 휴면/탈퇴회원 거르기 return
		  "redirect:/admin/openMemberList.do";
		  }
	  model.addAttribute("memberInfo",memberDto);
	  
	  return "/member/memberDetail";
	  }

}
