package com.mycompany.carmanagement.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Customer;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.domain.SalesRecord;
import com.mycompany.carmanagement.exception.BusinessException;
import com.mycompany.carmanagement.respository.CarRepository;
import com.mycompany.carmanagement.respository.CustomerRepository;
import com.mycompany.carmanagement.respository.EmployeeRepository;
import com.mycompany.carmanagement.respository.SalesRecordRepository;
import com.mycompany.carmanagement.web.json.bean.SalesRecordJsonBean;

@Service
public class SalesRecordService {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static final Logger LOG = LoggerFactory.getLogger(SalesRecordService.class);

	@Autowired
	SalesRecordRepository salesRecordRepository;

	private SalesRecordJsonBean bean2json(SalesRecord salesRecord) {
		SalesRecordJsonBean jsnSalesRecord = new SalesRecordJsonBean();
		jsnSalesRecord.setId(new Long(salesRecord.getId()).toString());
		jsnSalesRecord.setCarId(new Long(salesRecord.getCar().getId()).toString());
		jsnSalesRecord.setCarName(salesRecord.getCar().getName());
		jsnSalesRecord.setEmployeeId(new Long(salesRecord.getEmployee().getId()).toString());
		jsnSalesRecord.setEmployeeName(salesRecord.getEmployee().getName());
		jsnSalesRecord.setCustomerId(new Long(salesRecord.getCustomer().getId()).toString());
		jsnSalesRecord.setCustomerName(salesRecord.getCustomer().getName());
		jsnSalesRecord.setBeginDate(dateFormat.format(salesRecord.getBeginDate()));
		jsnSalesRecord.setEndDate(dateFormat.format(salesRecord.getEndDate()));
		jsnSalesRecord.setDescription(salesRecord.getDescription());
		jsnSalesRecord.setPrice(new Long(salesRecord.getPrice()).toString());
		return jsnSalesRecord;
	}

	private SalesRecord json2bean(SalesRecordJsonBean jsnSalesRecord) throws ParseException {
		SalesRecord salesRecord = new SalesRecord();
		salesRecord.setId(new Long(jsnSalesRecord.getId()));
		salesRecord.getCar().setId(new Long(jsnSalesRecord.getCarId()));
		salesRecord.getCar().setName(jsnSalesRecord.getCarName());
		salesRecord.getEmployee().setId(new Long(jsnSalesRecord.getEmployeeId()));
		salesRecord.getEmployee().setName(jsnSalesRecord.getEmployeeName());
		salesRecord.getCustomer().setId(new Long(jsnSalesRecord.getCustomerId()));
		salesRecord.getCustomer().setName(jsnSalesRecord.getCustomerName());
		salesRecord.setBeginDate(dateFormat.parse(jsnSalesRecord.getBeginDate()));
		salesRecord.setEndDate(dateFormat.parse(jsnSalesRecord.getEndDate()));
		salesRecord.setDescription(jsnSalesRecord.getDescription());
		salesRecord.setPrice(new Long(jsnSalesRecord.getPrice()));
		return salesRecord;
	}

	public long getCount() {
		return this.salesRecordRepository.count();
	}

	public List<SalesRecordJsonBean> getAll(int jtStartIndex, int jtPageSize) throws BusinessException {
		List<SalesRecordJsonBean> jsnSalesRecords = new ArrayList<SalesRecordJsonBean>();
		try {
			Page<SalesRecord> salesRecords = this.salesRecordRepository.findAll(new PageRequest(jtStartIndex, jtPageSize));
			for (SalesRecord salesRecord : salesRecords) {
				SalesRecordJsonBean jsnSalesRecord = bean2json(salesRecord);
				jsnSalesRecords.add(jsnSalesRecord);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing salesRecords from - " + jtStartIndex + " to " + jtPageSize + " - " + e.getMessage());
			throw new BusinessException("Exception while listing expenses from - " + jtStartIndex + " to " + jtPageSize + " - "
					+ e.getMessage());
		}
		return jsnSalesRecords;
	}

	public void add(SalesRecordJsonBean jsnSalesRecordBean) throws BusinessException {
		try {
			SalesRecord salesRecord = json2bean(jsnSalesRecordBean);
			this.salesRecordRepository.save(salesRecord);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding salesRecord" + jsnSalesRecordBean.toString() + e.getMessage());
			throw new BusinessException("Exception thrown while adding salesRecord" + jsnSalesRecordBean.toString() + e.getMessage());
		}
	}

	public void update(SalesRecordJsonBean jsnSalesRecordBean) throws BusinessException {
		try {
			SalesRecord salesRecord = json2bean(jsnSalesRecordBean);
			this.salesRecordRepository.save(salesRecord);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding salesRecord" + jsnSalesRecordBean.toString() + e.getMessage());
			throw new BusinessException("Exception thrown while adding salesRecord" + jsnSalesRecordBean.toString() + e.getMessage());
		}
	}

	public void delete(Long salesRecordId) {
		this.salesRecordRepository.delete(salesRecordId);
	}

}
