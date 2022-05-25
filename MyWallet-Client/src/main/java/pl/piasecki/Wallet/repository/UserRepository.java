package pl.piasecki.Wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pl.piasecki.Wallet.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	
	@Query("SELECT u FROM User u")
	List<User> findAllUsers();
}
