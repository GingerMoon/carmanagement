package com.mycompany.carmanagement.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.carmanagement.domain.SalesRecord;

@Repository
public interface SalesRecordRepository extends CrudRepository<SalesRecord, Long> {

	@Query("select s from SalesRecord s  where s.car.id like :car_id and s.customer.id like :customer_id and s.employee.id like :employee_id")
	List<SalesRecord> findWithoutID(@Param("car_id") long car_id, @Param("customer_id") long customer_id, @Param("employee_id") long employee_id);
	
	SalesRecord findById(long id);
}
