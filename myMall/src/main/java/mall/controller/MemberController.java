package mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import mall.dto.MemberDto;
import mall.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/member/openMemberList.do")
	public ModelAndView openMemberList() throws Exception {
		ModelAndView mv = new ModelAndView("/member/memberList");
		
		List<MemberDto> list = memberService.selectMemberList();
		System.out.println(list);
		mv.addObject("list", list);
		return mv;
	}
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
//		JsonObject jo = new JsonObject();
//		if(memberDto.getUsridx() > 0) {
//			
//			jo.addProperty("result", "succ");
//			jo.addProperty("url", "/member/openMemberList.do");
//
//			
//		}else {
//			
//			jo.addProperty("result", "fail");
//			jo.addProperty("url", "/member/insertMember.do");
//		}
//		System.out.println(jo);
//		return jo.toString();
	}
}
