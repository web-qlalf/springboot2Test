package mall.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

import mall.domain.MemberDto;
import mall.domain.RefreshToken;
import mall.security.Member;
import mall.service.User;

@Mapper
public interface AuthMapper {


	MemberDto selectMemberDetailToEmail(String email) throws Exception;

	 Optional<Member> findByEmail(String email) throws Exception;
}
