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

import mall.dto.MemberDto;
import mall.mapper.MemberMapper;
import mall.service.MemberService;


@Controller
@RequestMapping
public class MemberController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberMapper memberMapper;
	
	/*
	 * @RequestMapping("/member/openMemberList.do") public String
	 * openMemberList(@RequestBody MemberDto memberDto, Model model) throws
	 * Exception { log.debug("openMemberList");
	 * 
	 * // model.addAttribute("memberInfo", memberDto); return "/member/memberList";
	 * }
	 */
	@RequestMapping("/member/openMemberListAgGrid.do")
	public String openMemberListAgGrid(Model model) throws Exception {
//		log.debug("openMemberList");
		List<MemberDto> list = memberService.selectMemberListAgGrid();
		model.addAttribute("detailPage", "/member/memberDetail.do");
		model.addAttribute("list", list);
		return "/member/memberList-AG";
	}
	@GetMapping("/member/openMemberList.do")
	public String openMemberList(@ModelAttribute("params") MemberDto memberDto, Model model) throws Exception {
//		CommonDTO params = new CommonDTO();
//		Criteria cri = new Criteria();
//		PaginationInfo pn = new PaginationInfo(cri);
//		int smttc = memberMapper.selectMemberTotalCount(cri);
//		pn.setTotalRecordCount(smttc);
//		params.setPaginationInfo(pn);
		System.out.println("+=+=+=+=++=+=+=+=+=+=+=++==++=+=+=+=+=+=+=++=+=+=+=+=+=+=++=+=+=+=+=+=+=+=+=+");
		System.out.println(memberDto);
		System.out.println("+=+=+=+=++=+=+=+=+=+=+=++==++=+=+=+=+=+=+=++=+=+=+=+=+=+=++=+=+=+=+=+=+=+=+=+");
		List<MemberDto> list = memberService.selectMemberList(memberDto);
		System.out.println(list);
		System.out.println("+=+=+=+=++=+=+=+=+=+=+=++==++=+=+=+=+=+=+=++=+=+=+=+=+=+=++=+=+=+=+=+=+=+=+=+");
		model.addAttribute("list", list);
//		model.addAttribute("params", memberDto);
		model.addAttribute("detailPage", "/member/memberDetail.do");
		model.addAttribute("getPage", "/member/getUserList.do");
		return "/member/memberList";
	}

	@RequestMapping("/member/getUserList.do")
	@ResponseBody 
	public List<MemberDto> getUserList(@ModelAttribute("params") MemberDto memberDto) throws Exception {
		
		List<MemberDto> list = memberService.selectMemberList(memberDto);
		System.out.println("********************************************************************************************************************");
		System.out.println(list);
		System.out.println("********************************************************************************************************************");
		return list;
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
