package mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mall.dto.MemberDto;

@Mapper
public interface MemberMapper {
	List<MemberDto> selectMemberList() throws Exception;
	void insertMember(MemberDto memberDto) throws Exception;
}
