package br.com.jsonreadinsertmongo.repository;

import br.com.jsonreadinsertmongo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
