package mall.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import mall.dto.MemberDto;
import mall.service.MemberService;


@Controller
@RequestMapping
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
	@RequestMapping("/member/openMemberList.do")
	public String openMemberList() throws Exception {
//		log.debug("openMemberList");
		return "/member/memberList";
	}

	@RequestMapping("/member/getUserList.do")
	@ResponseBody 
	public List<MemberDto> getUserList(@ModelAttribute("params") MemberDto memberDto) throws Exception {
		
		List<MemberDto> list = memberService.selectMemberList(memberDto);
		return list;
//		return "/member/memberList";
	}
//	@RequestMapping("/member/getUserList.do")
//	//
//	
//	public String getUserList(@ModelAttribute("params") MemberDto memberDto) throws Exception {
//		
//		System.out.println(memberDto);
//		List<MemberDto> list = memberService.selectMemberList();
//		
//		return list.toString();
//	}
	
	
//	@RequestMapping("/member/openMemberList.do")
//	public ModelAndView openMemberList() throws Exception {
//		log.debug("openMemberList");
//		ModelAndView mv = new ModelAndView("/member/memberList");
//		
//		List<MemberDto> list = memberService.selectMemberList();
//		System.out.println(list);
//		mv.addObject("list", list);
//		mv.addObject("detailPage", "/member/memberDetail.do");
//		return mv;
//	}
	
	
	@RequestMapping("/member/openMemberJoin.do")
	public String openMemberJoin() throws Exception{
		return "/member/memberJoin";
	}
	
	
	@RequestMapping("/member/insertMember.do")
	public String insertMember(MemberDto memberDto) throws Exception{
//		System.out.println(memberDto);
		memberService.insertMember(memberDto);
		System.out.println(memberDto.getUsridx());
		if(memberDto.getUsridx() > 0) {
			return "redirect:/member/openMemberList.do";
		}else {
			return "redirect:/member/insertMember.do";
		}

	}
	
	@RequestMapping("/member/memberDetail.do")
    public String memberDatil(@RequestParam("user_id") String userid, Model model) throws Exception{
		log.debug("memberDatil");
		System.out.println("**********************************************************");
//        return memberService.selectMemberDetail(user_id);
        
        if (userid == null) {
			return "redirect:/member/openMemberList.do";
		}

        MemberDto memberDto = memberService.selectMemberDetail(userid);
		if (memberDto == null || "3".equals(memberDto.getUserauth().toString()) || "4".equals(memberDto.getUserauth().toString())) {
			// 휴면/탈퇴회원 거르기
			return "redirect:/member/openMemberList.do";
		}
		model.addAttribute("memberInfo", memberDto);

		return "/member/memberDetail";
    }
	
	


}
