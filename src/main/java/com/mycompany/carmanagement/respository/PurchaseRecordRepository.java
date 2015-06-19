package com.mycompany.carmanagement.respository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.carmanagement.domain.PurchaseRecord;
import com.mycompany.carmanagement.domain.SalesRecord;

@Repository
public interface PurchaseRecordRepository extends CrudRepository<PurchaseRecord, Long> {

	PurchaseRecord findById(long id);

	@Query("select s from PurchaseRecord s where s.car.id like :car_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<PurchaseRecord> findByCarId(@Param("car_id") long car_id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);	
	
	@Query("select s from PurchaseRecord s where s.customer.id like :customer_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<PurchaseRecord> findByCustomerId(@Param("customer_id") long customer_id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
	
	@Query("select s from PurchaseRecord s where s.employee.id like :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<PurchaseRecord> findByEmployeeId(@Param("employee_id") long employee_id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
	
	@Query("select s from PurchaseRecord s where s.car.id like :car_id and s.customer.id like :customer_id and s.employee.id like :employee_id")
	List<PurchaseRecord> findWithoutID(@Param("car_id") long car_id, @Param("customer_id") long customer_id, @Param("employee_id") long employee_id);
	
	@Query("select s from PurchaseRecord s where s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<PurchaseRecord> findByTime(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
}