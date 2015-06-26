package com.mycompany.carmanagement.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mycompany.carmanagement.domain.PurchaseRecord;
import com.mycompany.carmanagement.exception.BusinessException;
import com.mycompany.carmanagement.respository.PurchaseRecordRepository;
import com.mycompany.carmanagement.web.json.bean.PurchaseRecordJsonBean;

@Service
public class PurchaseRecordService {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static final Logger LOG = LoggerFactory.getLogger(PurchaseRecordService.class);

	@Autowired
	PurchaseRecordRepository purchaseRecordRepository;

	private PurchaseRecordJsonBean bean2json(PurchaseRecord purchaseRecord) {
		PurchaseRecordJsonBean jsnPurchaseRecord = new PurchaseRecordJsonBean();
		jsnPurchaseRecord.setId(new Long(purchaseRecord.getId()).toString());
		jsnPurchaseRecord.setCarId(new Long(purchaseRecord.getCar().getId()).toString());
		jsnPurchaseRecord.setCarName(purchaseRecord.getCar().getName());
		jsnPurchaseRecord.setEmployeeId(new Long(purchaseRecord.getEmployee().getId()).toString());
		jsnPurchaseRecord.setEmployeeName(purchaseRecord.getEmployee().getName());
		jsnPurchaseRecord.setProviderId(new Long(purchaseRecord.getProvider().getId()).toString());
		jsnPurchaseRecord.setProviderName(purchaseRecord.getProvider().getName());
		jsnPurchaseRecord.setBeginDate(dateFormat.format(purchaseRecord.getBeginDate()));
		jsnPurchaseRecord.setEndDate(dateFormat.format(purchaseRecord.getEndDate()));
		jsnPurchaseRecord.setDescription(purchaseRecord.getDescription());
		jsnPurchaseRecord.setPrice(new Long(purchaseRecord.getPrice()).toString());
		return jsnPurchaseRecord;
	}

	private PurchaseRecord json2bean(PurchaseRecordJsonBean jsnPurchaseRecord) throws ParseException {
		PurchaseRecord purchaseRecord = new PurchaseRecord();
		purchaseRecord.setId(new Long(jsnPurchaseRecord.getId()));
		purchaseRecord.getCar().setId(new Long(jsnPurchaseRecord.getCarId()));
		purchaseRecord.getCar().setName(jsnPurchaseRecord.getCarName());
		purchaseRecord.getEmployee().setId(new Long(jsnPurchaseRecord.getEmployeeId()));
		purchaseRecord.getEmployee().setName(jsnPurchaseRecord.getEmployeeName());
		purchaseRecord.getProvider().setId(new Long(jsnPurchaseRecord.getProviderId()));
		purchaseRecord.getProvider().setName(jsnPurchaseRecord.getProviderName());
		purchaseRecord.setBeginDate(dateFormat.parse(jsnPurchaseRecord.getBeginDate()));
		purchaseRecord.setEndDate(dateFormat.parse(jsnPurchaseRecord.getEndDate()));
		purchaseRecord.setDescription(jsnPurchaseRecord.getDescription());
		purchaseRecord.setPrice(new Long(jsnPurchaseRecord.getPrice()));
		return purchaseRecord;
	}

	public long getCount(PurchaseRecord purchaseRecord) {
		return this.purchaseRecordRepository.count();
	}

	public List<PurchaseRecordJsonBean> getAll(PurchaseRecord purchaseRecord, Pageable pageable) throws BusinessException {
		List<PurchaseRecordJsonBean> jsnPurchaseRecords = new ArrayList<PurchaseRecordJsonBean>();
		try {
			long id = purchaseRecord.getId();

			List<PurchaseRecord> purchaseRecords = null;

			if (id == -1) {
				if ((purchaseRecord.getCar().getId() != -1) && (purchaseRecord.getProvider().getId() != -1) // 1,1,1
						&& (purchaseRecord.getEmployee().getId() != -1)) {
					purchaseRecords = this.purchaseRecordRepository.findByCarIdProviderIdEmployeeId(purchaseRecord.getCar().getId(),
							purchaseRecord.getProvider().getId(), purchaseRecord.getEmployee().getId(), purchaseRecord.getBeginDate(),
							purchaseRecord.getEndDate(), pageable);
				} else if ((purchaseRecord.getCar().getId() == -1) && (purchaseRecord.getProvider().getId() == -1) // -1,-1,-1
						&& (purchaseRecord.getEmployee().getId() == -1)) {
					purchaseRecords = this.purchaseRecordRepository.findByTime(purchaseRecord.getBeginDate(), purchaseRecord.getEndDate(),
							pageable);
				} else if ((purchaseRecord.getCar().getId() != -1) && (purchaseRecord.getProvider().getId() != -1) // 1,1,-1
						&& (purchaseRecord.getEmployee().getId() == -1)) {
					purchaseRecords = this.purchaseRecordRepository.findByCarIdProviderId(purchaseRecord.getCar().getId(), purchaseRecord
							.getProvider().getId(), purchaseRecord.getBeginDate(), purchaseRecord.getEndDate(), pageable);
				} else if ((purchaseRecord.getCar().getId() != -1) && (purchaseRecord.getProvider().getId() == -1) // 1,-1,1
						&& (purchaseRecord.getEmployee().getId() != -1)) {
					purchaseRecords = this.purchaseRecordRepository.findByCarIdEmployeeId(purchaseRecord.getCar().getId(), purchaseRecord
							.getEmployee().getId(), purchaseRecord.getBeginDate(), purchaseRecord.getEndDate(), pageable);
				} else if ((purchaseRecord.getCar().getId() == -1) && (purchaseRecord.getProvider().getId() != -1) // -1,1,1
						&& (purchaseRecord.getEmployee().getId() != -1)) {
					purchaseRecords = this.purchaseRecordRepository.findByProviderIdEmployeeId(purchaseRecord.getProvider().getId(),
							purchaseRecord.getEmployee().getId(), purchaseRecord.getBeginDate(), purchaseRecord.getEndDate(), pageable);
				} else if ((purchaseRecord.getCar().getId() != -1) && (purchaseRecord.getProvider().getId() == -1) // 1,-1,-1
						&& (purchaseRecord.getEmployee().getId() == -1)) {
					purchaseRecords = this.purchaseRecordRepository.findByCarId(purchaseRecord.getCar().getId(),
							purchaseRecord.getBeginDate(), purchaseRecord.getEndDate(), pageable);
				} else if ((purchaseRecord.getCar().getId() == -1) && (purchaseRecord.getProvider().getId() != -1) // -1,1,-1
						&& (purchaseRecord.getEmployee().getId() == -1)) {
					purchaseRecords = this.purchaseRecordRepository.findByProviderId(purchaseRecord.getProvider().getId(),
							purchaseRecord.getBeginDate(), purchaseRecord.getEndDate(), pageable);
				} else if ((purchaseRecord.getCar().getId() == -1) && (purchaseRecord.getProvider().getId() == -1) // -1,-1,1
						&& (purchaseRecord.getEmployee().getId() != -1)) {
					purchaseRecords = this.purchaseRecordRepository.findByEmployeeId(purchaseRecord.getEmployee().getId(),
							purchaseRecord.getBeginDate(), purchaseRecord.getEndDate(), pageable);
				}
			} else {
				PurchaseRecord e = this.purchaseRecordRepository.findById(id);
				purchaseRecords = new ArrayList<PurchaseRecord>();
				purchaseRecords.add(e);
			}

			for (PurchaseRecord entity : purchaseRecords) {
				PurchaseRecordJsonBean jsnPurchaseRecord = bean2json(entity);
				jsnPurchaseRecords.add(jsnPurchaseRecord);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing purchaseRecords from - " + pageable.getPageNumber() + " to " + pageable.getPageSize()
					+ " - " + e.getMessage());
			throw new BusinessException("Exception while listing expenses from - " + pageable.getPageNumber() + " to "
					+ pageable.getPageSize() + " - " + e.getMessage());
		}
		return jsnPurchaseRecords;
	}

	public void add(PurchaseRecordJsonBean jsnPurchaseRecordBean) throws BusinessException {
		try {
			PurchaseRecord purchaseRecord = json2bean(jsnPurchaseRecordBean);
			this.purchaseRecordRepository.save(purchaseRecord);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding purchaseRecord" + jsnPurchaseRecordBean.toString() + e.getMessage());
			throw new BusinessException("Exception thrown while adding purchaseRecord" + jsnPurchaseRecordBean.toString() + e.getMessage());
		}
	}

	public void update(PurchaseRecordJsonBean jsnPurchaseRecordBean) throws BusinessException {
		try {
			PurchaseRecord purchaseRecord = json2bean(jsnPurchaseRecordBean);
			this.purchaseRecordRepository.save(purchaseRecord);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding purchaseRecord" + jsnPurchaseRecordBean.toString() + e.getMessage());
			throw new BusinessException("Exception thrown while adding purchaseRecord" + jsnPurchaseRecordBean.toString() + e.getMessage());
		}
	}

	public void delete(Long purchaseRecordId) {
		this.purchaseRecordRepository.delete(purchaseRecordId);
	}
}
