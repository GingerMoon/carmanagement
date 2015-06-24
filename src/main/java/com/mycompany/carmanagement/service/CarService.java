package com.mycompany.carmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.exception.BusinessException;
import com.mycompany.carmanagement.respository.CarRepository;
import com.mycompany.carmanagement.web.json.bean.CarJsonBean;

@Service
public class CarService {

	private static final Logger LOG = LoggerFactory
			.getLogger(CarService.class);

	@Autowired
	CarRepository carRepository;

	public long getCount() {
		return this.carRepository.count();
	}

	public List<CarJsonBean> getAll(int jtStartIndex, int jtPageSize)
			throws BusinessException {
		List<CarJsonBean> jsnCars = new ArrayList<CarJsonBean>();
		try {
			Page<Car> customers = this.carRepository
					.findAll(new PageRequest(jtStartIndex, jtPageSize));
			for (Car customer : customers) {
				CarJsonBean jsnCar = new CarJsonBean();
				jsnCar.setId(new Long(customer.getId()).toString());
				jsnCar.setName(customer.getName());
				jsnCar.setDescription(customer.getDescription());
				jsnCars.add(jsnCar);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing customers from - "
					+ jtStartIndex + " to " + jtPageSize + " - "
					+ e.getMessage());
			throw new BusinessException(
					"Exception while listing expenses from - " + jtStartIndex
							+ " to " + jtPageSize + " - " + e.getMessage());
		}
		return jsnCars;
	}

	public void add(CarJsonBean jsnCarBean) throws BusinessException {
		try {
			Car customer = new Car();
			customer.setName(jsnCarBean.getName());
			customer.setDescription(jsnCarBean.getDescription());
			this.carRepository.save(customer);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding customer"
					+ jsnCarBean.toString() + e.getMessage());
			throw new BusinessException(
					"Exception thrown while adding customer"
							+ jsnCarBean.toString() + e.getMessage());
		}
	}

	public void update(CarJsonBean jsnCarBean)
			throws BusinessException {
		try {
			Car customer = new Car();
			customer.setId(new Long(jsnCarBean.getId()));
			customer.setName(jsnCarBean.getName());
			customer.setDescription(jsnCarBean.getDescription());
			this.carRepository.save(customer);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding customer"
					+ jsnCarBean.toString() + e.getMessage());
			throw new BusinessException(
					"Exception thrown while adding customer"
							+ jsnCarBean.toString() + e.getMessage());
		}
	}

	public void delete(Long customerId) {
		this.carRepository.delete(customerId);
	}
}
