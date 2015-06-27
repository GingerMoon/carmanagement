package com.mycompany.carmanagement.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.carmanagement.domain.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	@Query("select e from Employee e where e.name like :name and e.description like :description")
	List<Employee> findWithoutID(@Param("name") String name, @Param("description") String description);

	Employee findById(long id);
}
