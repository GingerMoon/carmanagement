package com.mycompany.carmanagement.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mycompany.carmanagement.domain.SalesRecord;
import com.mycompany.carmanagement.exception.BusinessException;
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

	public long getCount(SalesRecord salesRecord) {
		return this.salesRecordRepository.count();
	}

	public List<SalesRecordJsonBean> getAll(SalesRecord salesRecord, Pageable pageable, long[] salesRecordCount ) throws BusinessException {
		List<SalesRecordJsonBean> jsnSalesRecords = new ArrayList<SalesRecordJsonBean>();
		try {
			long id = salesRecord.getId();

			List<SalesRecord> salesRecords = null;

			if (id == -1) {
				if ((salesRecord.getCar().getId() != -1) && (salesRecord.getCustomer().getId() != -1) // 1,1,1
						&& (salesRecord.getEmployee().getId() != -1)) {
					salesRecords = this.salesRecordRepository.findByCarIdCustomerIdEmployeeId(salesRecord.getCar().getId(), salesRecord
							.getCustomer().getId(), salesRecord.getEmployee().getId(), salesRecord.getBeginDate(),
							salesRecord.getEndDate(), pageable);
					salesRecordCount[0] = this.salesRecordRepository.countByCarIdCustomerIdEmployeeId(salesRecord.getCar().getId(),
							salesRecord.getCustomer().getId(), salesRecord.getEmployee().getId(), salesRecord.getBeginDate(),
							salesRecord.getEndDate());
				} else if ((salesRecord.getCar().getId() == -1) && (salesRecord.getCustomer().getId() == -1) // -1,-1,-1
						&& (salesRecord.getEmployee().getId() == -1)) {
					salesRecords = this.salesRecordRepository.findByTime(salesRecord.getBeginDate(), salesRecord.getEndDate(), pageable);
					salesRecordCount[0] = this.salesRecordRepository.countByTime(salesRecord.getBeginDate(), salesRecord.getEndDate());
				} else if ((salesRecord.getCar().getId() != -1) && (salesRecord.getCustomer().getId() != -1) // 1,1,-1
						&& (salesRecord.getEmployee().getId() == -1)) {
					salesRecords = this.salesRecordRepository.findByCarIdCustomerId(salesRecord.getCar().getId(), salesRecord.getCustomer()
							.getId(), salesRecord.getBeginDate(), salesRecord.getEndDate(), pageable);
					salesRecordCount[0] = this.salesRecordRepository.countByCarIdCustomerId(salesRecord.getCar().getId(), salesRecord
							.getCustomer().getId(), salesRecord.getBeginDate(), salesRecord.getEndDate());
				} else if ((salesRecord.getCar().getId() != -1) && (salesRecord.getCustomer().getId() == -1) // 1,-1,1
						&& (salesRecord.getEmployee().getId() != -1)) {
					salesRecords = this.salesRecordRepository.findByCarIdEmployeeId(salesRecord.getCar().getId(), salesRecord.getEmployee()
							.getId(), salesRecord.getBeginDate(), salesRecord.getEndDate(), pageable);
					salesRecordCount[0] = this.salesRecordRepository.countByCarIdEmployeeId(salesRecord.getCar().getId(), salesRecord
							.getEmployee().getId(), salesRecord.getBeginDate(), salesRecord.getEndDate());
				} else if ((salesRecord.getCar().getId() == -1) && (salesRecord.getCustomer().getId() != -1) // -1,1,1
						&& (salesRecord.getEmployee().getId() != -1)) {
					salesRecords = this.salesRecordRepository.findByCustomerIdEmployeeId(salesRecord.getCustomer().getId(), salesRecord
							.getEmployee().getId(), salesRecord.getBeginDate(), salesRecord.getEndDate(), pageable);
					salesRecordCount[0] = this.salesRecordRepository.countByCustomerIdEmployeeId(salesRecord.getCustomer().getId(),
							salesRecord.getEmployee().getId(), salesRecord.getBeginDate(), salesRecord.getEndDate());
				} else if ((salesRecord.getCar().getId() != -1) && (salesRecord.getCustomer().getId() == -1) // 1,-1,-1
						&& (salesRecord.getEmployee().getId() == -1)) {
					salesRecords = this.salesRecordRepository.findByCarId(salesRecord.getCar().getId(), salesRecord.getBeginDate(),
							salesRecord.getEndDate(), pageable);
					salesRecordCount[0] = this.salesRecordRepository.countByCarId(salesRecord.getCar().getId(), salesRecord.getBeginDate(),
							salesRecord.getEndDate());
				} else if ((salesRecord.getCar().getId() == -1) && (salesRecord.getCustomer().getId() != -1) // -1,1,-1
						&& (salesRecord.getEmployee().getId() == -1)) {
					salesRecords = this.salesRecordRepository.findByCustomerId(salesRecord.getCustomer().getId(),
							salesRecord.getBeginDate(), salesRecord.getEndDate(), pageable);
					salesRecordCount[0] = this.salesRecordRepository.countByCustomerId(salesRecord.getCustomer().getId(),
							salesRecord.getBeginDate(), salesRecord.getEndDate());
				} else if ((salesRecord.getCar().getId() == -1) && (salesRecord.getCustomer().getId() == -1) // -1,-1,1
						&& (salesRecord.getEmployee().getId() != -1)) {
					salesRecords = this.salesRecordRepository.findByEmployeeId(salesRecord.getEmployee().getId(),
							salesRecord.getBeginDate(), salesRecord.getEndDate(), pageable);
					salesRecordCount[0] = this.salesRecordRepository.countByEmployeeId(salesRecord.getEmployee().getId(),
							salesRecord.getBeginDate(), salesRecord.getEndDate());
				}
			} else {
				SalesRecord e = this.salesRecordRepository.findById(id);
				if(e != null) {
					salesRecordCount[0] = 1;
				}
				salesRecords = new ArrayList<SalesRecord>();
				salesRecords.add(e);
			}

			for (SalesRecord entity : salesRecords) {
				SalesRecordJsonBean jsnSalesRecord = bean2json(entity);
				jsnSalesRecords.add(jsnSalesRecord);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing salesRecords from - " + pageable.getPageNumber() + " to " + pageable.getPageSize()
					+ " - " + e.getMessage());
			throw new BusinessException("Exception while listing expenses from - " + pageable.getPageNumber() + " to "
					+ pageable.getPageSize() + " - " + e.getMessage());
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
