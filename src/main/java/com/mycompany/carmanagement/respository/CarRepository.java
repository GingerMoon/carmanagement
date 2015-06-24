package com.mycompany.carmanagement.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Customer;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

	@Query("select c from Car c where c.name like :name and c.description like :description")
	List<Car> findWithoutID(@Param("name") String name, @Param("description") String description);

	Car findById(long id);
	
}
