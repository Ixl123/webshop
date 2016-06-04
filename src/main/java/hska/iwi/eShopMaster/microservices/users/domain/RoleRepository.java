package hska.iwi.eShopMaster.microservices.users.domain;

import org.springframework.data.repository.Repository;

import hska.iwi.eShopMaster.model.database.dataobjects.Role;

public interface RoleRepository extends Repository<Role, Integer>{

	public Role findByLevel(int level);
}
