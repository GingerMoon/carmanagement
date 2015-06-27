package com.mycompany.carmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.exception.BusinessException;
import com.mycompany.carmanagement.respository.EmployeeRepository;
import com.mycompany.carmanagement.web.json.bean.EmployeeJsonBean;

@Service
public class EmployeeService {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	EmployeeRepository employeeRepository;

	public long getCount() {
		return this.employeeRepository.count();
	}

	public List<EmployeeJsonBean> getAll(int jtStartIndex, int jtPageSize) throws BusinessException {
		List<EmployeeJsonBean> jsnEmployees = new ArrayList<EmployeeJsonBean>();
		try {
			Page<Employee> employees = this.employeeRepository.findAll(new PageRequest(jtStartIndex, jtPageSize));
			for (Employee employee : employees) {
				EmployeeJsonBean jsnEmployee = new EmployeeJsonBean();
				jsnEmployee.setId(new Long(employee.getId()).toString());
				jsnEmployee.setName(employee.getName());
				jsnEmployee.setDescription(employee.getDescription());
				jsnEmployees.add(jsnEmployee);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing employees from - " + jtStartIndex + " to " + jtPageSize + " - " + e.getMessage());
			throw new BusinessException("Exception while listing expenses from - " + jtStartIndex + " to " + jtPageSize + " - "
					+ e.getMessage());
		}
		return jsnEmployees;
	}

	public void add(EmployeeJsonBean jsnEmployeeBean) throws BusinessException {
		try {
			Employee employee = new Employee();
			employee.setName(jsnEmployeeBean.getName());
			employee.setDescription(jsnEmployeeBean.getDescription());
			this.employeeRepository.save(employee);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding employee" + jsnEmployeeBean.toString() + e.getMessage());
			throw new BusinessException("Exception thrown while adding employee" + jsnEmployeeBean.toString() + e.getMessage());
		}
	}

	public void update(EmployeeJsonBean jsnEmployeeBean) throws BusinessException {
		try {
			Employee employee = new Employee();
			employee.setId(new Long(jsnEmployeeBean.getId()));
			employee.setName(jsnEmployeeBean.getName());
			employee.setDescription(jsnEmployeeBean.getDescription());
			this.employeeRepository.save(employee);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding employee" + jsnEmployeeBean.toString() + e.getMessage());
			throw new BusinessException("Exception thrown while adding employee" + jsnEmployeeBean.toString() + e.getMessage());
		}
	}

	public void delete(Long employeeId) {
		this.employeeRepository.delete(employeeId);
	}
}
