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
import com.mycompany.carmanagement.domain.Customer;
import com.mycompany.carmanagement.exception.BusinessException;
import com.mycompany.carmanagement.respository.CarRepository;
import com.mycompany.carmanagement.respository.CustomerRepository;
import com.mycompany.carmanagement.web.json.bean.CustomerJsonBean;

@Service
public class CustomerService {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	CustomerRepository customerRepository;

	public long getCount() {
		return this.customerRepository.count();
	}

	public List<CustomerJsonBean> getAll(int jtStartIndex, int jtPageSize) throws BusinessException {
		List<CustomerJsonBean> jsnCustomers = new ArrayList<CustomerJsonBean>();
		try {
			Page<Customer> customers = this.customerRepository.findAll(new PageRequest(jtStartIndex, jtPageSize));
			for (Customer customer : customers) {
				CustomerJsonBean jsnCustomer = new CustomerJsonBean();
				jsnCustomer.setId(new Long(customer.getId()).toString());
				jsnCustomer.setName(customer.getName());
				jsnCustomer.setDescription(customer.getDescription());
				jsnCustomers.add(jsnCustomer);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing customers from - " + jtStartIndex + " to " + jtPageSize + " - " + e.getMessage());
			throw new BusinessException("Exception while listing expenses from - " + jtStartIndex + " to " + jtPageSize + " - "
					+ e.getMessage());
		}
		return jsnCustomers;
	}

	public void add(CustomerJsonBean jsnCustomerBean) throws BusinessException {
		try {
			Customer customer = new Customer();
			customer.setName(jsnCustomerBean.getName());
			customer.setDescription(jsnCustomerBean.getDescription());
			this.customerRepository.save(customer);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding customer" + jsnCustomerBean.toString() + e.getMessage());
			throw new BusinessException("Exception thrown while adding customer" + jsnCustomerBean.toString() + e.getMessage());
		}
	}

	public void update(CustomerJsonBean jsnCustomerBean) throws BusinessException {
		try {
			Customer customer = new Customer();
			customer.setId(new Long(jsnCustomerBean.getId()));
			customer.setName(jsnCustomerBean.getName());
			customer.setDescription(jsnCustomerBean.getDescription());
			this.customerRepository.save(customer);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding customer" + jsnCustomerBean.toString() + e.getMessage());
			throw new BusinessException("Exception thrown while adding customer" + jsnCustomerBean.toString() + e.getMessage());
		}
	}

	public void delete(Long customerId) {
		this.customerRepository.delete(customerId);
	}
}
