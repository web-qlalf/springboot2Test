package mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.dto.MemberDto;
import mall.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public List<MemberDto> selectMemberList() throws Exception{
		return memberMapper.selectMemberList();
	}
	
	@Override
	public void insertMember(MemberDto memberDto) throws Exception{
		memberMapper.insertMember(memberDto);
	}
}
