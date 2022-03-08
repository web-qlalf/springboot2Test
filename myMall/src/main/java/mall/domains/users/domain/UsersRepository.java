package mall.domains.users.domain;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersRepository {

	Optional<UsersEntity> findByUserId(String userId);

	UsersEntity save(UsersEntity build);

	List<UsersEntity> findAll();
}
