package com.mycompany.carmanagement.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {

	@Query("select c from Provider c where c.name like :name and c.description like :description")
	List<Provider> findWithoutID(@Param("name") String name, @Param("description") String description);

	Provider findById(long id);
}
