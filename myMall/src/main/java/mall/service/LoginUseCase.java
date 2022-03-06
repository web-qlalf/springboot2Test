package mall.service;

import java.util.Optional;

import mall.domain.MemberDto;
import mall.security.AuthToken;

public interface LoginUseCase {
    Optional<MemberDto> login(String id, String password) throws Exception;
    AuthToken createAuthToken(MemberDto memberDTO);
}
