package hska.iwi.eShopMaster.microservices.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import hska.iwi.eShopMaster.model.database.dataobjects.User;

public interface UserRepository extends Repository<User, Integer> {

	@Query("SELECT count(*) from customer")
	public int countUsers();
}
