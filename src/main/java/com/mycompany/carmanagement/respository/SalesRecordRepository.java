package com.mycompany.carmanagement.respository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.carmanagement.domain.SalesRecord;

@Repository
public interface SalesRecordRepository extends PagingAndSortingRepository<SalesRecord, Long> {

	SalesRecord findById(long id);

	/*******************************************************************************************************************************/
	@Query("select s from SalesRecord s where s.car.id = :car_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<SalesRecord> findByCarId(@Param("car_id") long car_id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate,
			Pageable pageable);

	@Query("select count(*) from SalesRecord s where s.car.id = :car_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long countByCarId(@Param("car_id") long car_id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
	

	@Query("select sum(price) from SalesRecord s where s.car.id = :car_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long totalPriceByCarId(@Param("car_id") long car_id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

	/*******************************************************************************************************************************/
	@Query("select s from SalesRecord s where s.customer.id = :customer_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<SalesRecord> findByCustomerId(@Param("customer_id") long customer_id, @Param("beginDate") Date beginDate,
			@Param("endDate") Date endDate, Pageable pageable);

	@Query("select count(*) from SalesRecord s where s.customer.id = :customer_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long countByCustomerId(@Param("customer_id") long customer_id, @Param("beginDate") Date beginDate,
			@Param("endDate") Date endDate);

	@Query("select sum(price) from SalesRecord s where s.customer.id = :customer_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long totalPriceByCustomerId(@Param("customer_id") long customer_id, @Param("beginDate") Date beginDate,
			@Param("endDate") Date endDate);
	/*******************************************************************************************************************************/
	@Query("select s from SalesRecord s where s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<SalesRecord> findByEmployeeId(@Param("employee_id") long employee_id, @Param("beginDate") Date beginDate,
			@Param("endDate") Date endDate, Pageable pageable);

	@Query("select count(*) from SalesRecord s where s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long countByEmployeeId(@Param("employee_id") long employee_id, @Param("beginDate") Date beginDate,
			@Param("endDate") Date endDate);

	@Query("select sum(price) from SalesRecord s where s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long totalPriceByEmployeeId(@Param("employee_id") long employee_id, @Param("beginDate") Date beginDate,
			@Param("endDate") Date endDate);
	/*******************************************************************************************************************************/
	@Query("select s from SalesRecord s where s.car.id = :car_id and s.customer.id = :customer_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<SalesRecord> findByCarIdCustomerId(@Param("car_id") long car_id, @Param("customer_id") long customer_id,
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, Pageable pageable);
	
	@Query("select count(*) from SalesRecord s where s.car.id = :car_id and s.customer.id = :customer_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long countByCarIdCustomerId(@Param("car_id") long car_id, @Param("customer_id") long customer_id,
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
	
	@Query("select sum(price) from SalesRecord s where s.car.id = :car_id and s.customer.id = :customer_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long totalPriceByCarIdCustomerId(@Param("car_id") long car_id, @Param("customer_id") long customer_id,
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

	/*******************************************************************************************************************************/
	@Query("select s from SalesRecord s where s.customer.id = :customer_id and s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<SalesRecord> findByCustomerIdEmployeeId(@Param("customer_id") long customer_id, @Param("employee_id") long employee_id,
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, Pageable pageable);
	
	@Query("select count(*) from SalesRecord s where s.customer.id = :customer_id and s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long countByCustomerIdEmployeeId(@Param("customer_id") long customer_id, @Param("employee_id") long employee_id,
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

	@Query("select sum(price) from SalesRecord s where s.customer.id = :customer_id and s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long totalPriceByCustomerIdEmployeeId(@Param("customer_id") long customer_id, @Param("employee_id") long employee_id,
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

	/*******************************************************************************************************************************/
	@Query("select s from SalesRecord s where s.car.id = :car_id and s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<SalesRecord> findByCarIdEmployeeId(@Param("car_id") long car_id, @Param("employee_id") long employee_id,
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, Pageable pageable);
	
	@Query("select count(*) from SalesRecord s where s.car.id = :car_id and s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long countByCarIdEmployeeId(@Param("car_id") long car_id, @Param("employee_id") long employee_id,
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
	
	@Query("select sum(price) from SalesRecord s where s.car.id = :car_id and s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long totalPriceByCarIdEmployeeId(@Param("car_id") long car_id, @Param("employee_id") long employee_id,
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

	/*******************************************************************************************************************************/
	@Query("select s from SalesRecord s where s.car.id = :car_id and s.customer.id = :customer_id and s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<SalesRecord> findByCarIdCustomerIdEmployeeId(@Param("car_id") long car_id, @Param("customer_id") long customer_id,
			@Param("employee_id") long employee_id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate, Pageable pageable);
	
	@Query("select count(*) from SalesRecord s where s.car.id = :car_id and s.customer.id = :customer_id and s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long countByCarIdCustomerIdEmployeeId(@Param("car_id") long car_id, @Param("customer_id") long customer_id,
			@Param("employee_id") long employee_id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
	
	@Query("select sum(price) from SalesRecord s where s.car.id = :car_id and s.customer.id = :customer_id and s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	long totalPriceByCarIdCustomerIdEmployeeId(@Param("car_id") long car_id, @Param("customer_id") long customer_id,
			@Param("employee_id") long employee_id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

	/*******************************************************************************************************************************/
	@Query("select s from SalesRecord s where s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<SalesRecord> findByTime(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, Pageable pageable);
	
	@Query("select count(*) from SalesRecord s where s.beginDate >= :beginDate and s.endDate <= :endDate")
	long countByTime(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
	
	@Query("select sum(price) from SalesRecord s where s.beginDate >= :beginDate and s.endDate <= :endDate")
	long totalPriceByTime(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
	/*******************************************************************************************************************************/
}
