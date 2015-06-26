package com.mycompany.carmanagement.respository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.carmanagement.domain.PurchaseRecord;
import com.mycompany.carmanagement.domain.SalesRecord;

@Repository
public interface PurchaseRecordRepository extends PagingAndSortingRepository<PurchaseRecord, Long> {

	PurchaseRecord findById(long id);

	@Query("select s from PurchaseRecord s where s.car.id = :car_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<PurchaseRecord> findByCarId(@Param("car_id") long car_id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate,
			Pageable pageable);

	@Query("select s from PurchaseRecord s where s.provider.id = :provider_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<PurchaseRecord> findByProviderId(@Param("provider_id") long provider_id, @Param("beginDate") Date beginDate,
			@Param("endDate") Date endDate, Pageable pageable);

	@Query("select s from PurchaseRecord s where s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<PurchaseRecord> findByEmployeeId(@Param("employee_id") long employee_id, @Param("beginDate") Date beginDate,
			@Param("endDate") Date endDate, Pageable pageable);

	@Query("select s from PurchaseRecord s where s.car.id = :car_id and s.provider.id = :provider_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<PurchaseRecord> findByCarIdProviderId(@Param("car_id") long car_id, @Param("provider_id") long provider_id,
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, Pageable pageable);

	@Query("select s from PurchaseRecord s where s.provider.id = :provider_id and s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<PurchaseRecord> findByProviderIdEmployeeId(@Param("provider_id") long provider_id, @Param("employee_id") long employee_id,
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, Pageable pageable);

	@Query("select s from PurchaseRecord s where s.car.id = :car_id and s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<PurchaseRecord> findByCarIdEmployeeId(@Param("car_id") long car_id, @Param("employee_id") long employee_id,
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, Pageable pageable);

	@Query("select s from PurchaseRecord s where s.car.id = :car_id and s.provider.id = :provider_id and s.employee.id = :employee_id and s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<PurchaseRecord> findByCarIdProviderIdEmployeeId(@Param("car_id") long car_id, @Param("provider_id") long provider_id,
			@Param("employee_id") long employee_id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate, Pageable pageable);

	@Query("select s from PurchaseRecord s where s.beginDate >= :beginDate and s.endDate <= :endDate")
	List<PurchaseRecord> findByTime(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, Pageable pageable);
}
