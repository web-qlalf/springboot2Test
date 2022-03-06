package mall.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mall.domain.MemberDto;
import mall.mapper.MemberMapper;
import mall.paging.CommonDTO;
import mall.paging.Criteria;
import mall.paging.PaginationInfo;
import mall.security.AuthToken;

@Service
public class MemberServiceImpl implements MemberService{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public List<MemberDto> selectMemberListAgGrid() throws Exception{
		return memberMapper.selectMemberListAgGrid();
	}
	
	@Override
	public List<MemberDto> selectMemberList(MemberDto memberDto) throws Exception{
		
		List<MemberDto> memberList = Collections.emptyList();

		log.debug("selectMemberTotalCount =================================== START");
		int memberTotalCount = memberMapper.selectMemberTotalCount(memberDto);
		log.debug("selectMemberTotalCount =================================== END");

		log.debug("paginationInfo =================================== START");
		PaginationInfo paginationInfo = new PaginationInfo(memberDto);
		log.debug("paginationInfo =================================== END");
		
		log.debug("setTotalRecordCount =================================== START");
		paginationInfo.setTotalRecordCount(memberTotalCount);
		log.debug("setTotalRecordCount =================================== END");
		
		
		log.debug("setPaginationInfo =================================== START");
		memberDto.setPaginationInfo(paginationInfo);
		log.debug("setPaginationInfo =================================== END");

		if (memberTotalCount > 0) {
			memberList = memberMapper.selectMemberList(memberDto);
		}

		return memberList;
		
	
//		return memberMapper.selectMemberList();
	}
	
	@Override
	public void insertMember(MemberDto memberDto) throws Exception{
		memberMapper.insertMember(memberDto);
//		return memberDto.getUsridx();
	}

	@Override
	public  MemberDto selectMemberDetail(String userId) throws Exception {
//		memberMapper.uppdate
		return memberMapper.selectMemberDetail(userId);
//		Optional<MemberDetail> result = memberMapper.selectMemberDetail(user_id);
//		return result.isPresent()? MemberDto(result.get()): null;
	}


	
	




}
