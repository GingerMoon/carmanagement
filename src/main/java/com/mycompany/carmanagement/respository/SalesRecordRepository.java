package com.mycompany.carmanagement.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.carmanagement.domain.Customer;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.domain.SalesRecord;

public interface SalesRecordRepository extends CrudRepository<SalesRecord, Long> {

}
