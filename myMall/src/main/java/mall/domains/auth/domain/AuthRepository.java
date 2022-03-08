package mall.domains.auth.domain;


import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import mall.domains.users.domain.UsersEntity;

@Mapper
public interface AuthRepository {

  
  void save(AuthEntity build);
  
  Optional<AuthEntity> findByUsersEntityId(String usr_email);
}
