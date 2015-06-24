package com.mycompany.carmanagement.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	@Query("select c from Customer c where c.name like :name and c.description like :description")
	List<Customer> findWithoutID(@Param("name") String name, @Param("description") String description);

	Customer findById(long id);
}
