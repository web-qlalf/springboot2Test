package mall.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import mall.exception.ErrorCode;
import mall.domain.MemberDto;
import mall.mapper.AuthMapper;
import mall.mapper.MemberMapper;
import mall.security.Member;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

//    private final MemberRepository memberRepository;
	private final AuthMapper memberRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	System.out.println("???????customUserDetailsService??????");
        try {
			return memberRepository.findByEmail(email)
			        .map(this::createSpringSecurityUser)
			        .orElseThrow(RuntimeException::new);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }


	private User createSpringSecurityUser(Member member) {
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(member.getRole()));
        //TODO: username 에 email을 넣는 방법이 적합한지?
        System.out.println("======================================");
//        System.out.println(member.getEmail());
//        System.out.println(member.getPassword());
        return new User(member.getEmail(), member.getPassword(), grantedAuthorities);
    }
}
