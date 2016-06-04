package hska.iwi.eShopMaster.microservices.users.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

public interface UserRepository extends Repository<User, Integer> {

	public User findByUsername(String username);
	
	public User save(User user);
	
	@Query("SELECT count(*) from User")
	public int countUsers();
}
