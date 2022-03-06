package mall.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mall.domain.MemberDto;
import mall.mapper.AuthMapper;
import mall.mapper.MemberMapper;
import mall.security.AuthToken;
import mall.security.Role;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtAuthTokenProvider jwtAuthTokenProvider;
	private final CustomUserDetailsService customUserDetailsService;
	private final static long LOGIN_RETENTION_MINUTES = 30;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuthMapper memberMapper;

	public Optional<MemberDto> login(String email, String pw) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,pw);

		// 사용자 비밀번호 체크, 패스워드 일치하지 않는다면 Exception 발생 및 이후 로직 실행 안됨
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		Role role = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst()
				.map(Role::of).orElse(Role.UNKNOWN);

		MemberDto MemberDto = memberMapper.selectMemberDetailToEmail(email);
				//MemberDto.builder().userName("eddy").email(email).role(role).build();

		return Optional.ofNullable(MemberDto);
	}

	@Override
	public AuthToken createAuthToken(MemberDto MemberDto) {

		Date expiredDate = Date.from(
				LocalDateTime.now().plusMinutes(LOGIN_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
		return jwtAuthTokenProvider.createAuthToken(MemberDto.getEmail(), MemberDto.getRole().getCode(), expiredDate);
	}
}