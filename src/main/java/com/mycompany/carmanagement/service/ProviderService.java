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
import com.mycompany.carmanagement.domain.Provider;
import com.mycompany.carmanagement.domain.Provider;
import com.mycompany.carmanagement.exception.BusinessException;
import com.mycompany.carmanagement.respository.CarRepository;
import com.mycompany.carmanagement.respository.ProviderRepository;
import com.mycompany.carmanagement.respository.ProviderRepository;
import com.mycompany.carmanagement.web.json.bean.ProviderJsonBean;

@Service
public class ProviderService {

	private static final Logger LOG = LoggerFactory.getLogger(ProviderService.class);

	@Autowired
	ProviderRepository providerRepository;

	public long getCount() {
		return this.providerRepository.count();
	}

	public List<ProviderJsonBean> getAll(int jtStartIndex, int jtPageSize) throws BusinessException {
		List<ProviderJsonBean> jsnProviders = new ArrayList<ProviderJsonBean>();
		try {
			Page<Provider> providers = this.providerRepository.findAll(new PageRequest(jtStartIndex, jtPageSize));
			for (Provider provider : providers) {
				ProviderJsonBean jsnProvider = new ProviderJsonBean();
				jsnProvider.setId(new Long(provider.getId()).toString());
				jsnProvider.setName(provider.getName());
				jsnProvider.setDescription(provider.getDescription());
				jsnProviders.add(jsnProvider);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing providers from - " + jtStartIndex + " to " + jtPageSize + " - " + e.getMessage());
			throw new BusinessException("Exception while listing expenses from - " + jtStartIndex + " to " + jtPageSize + " - "
					+ e.getMessage());
		}
		return jsnProviders;
	}

	public void add(ProviderJsonBean jsnProviderBean) throws BusinessException {
		try {
			Provider provider = new Provider();
			provider.setName(jsnProviderBean.getName());
			provider.setDescription(jsnProviderBean.getDescription());
			this.providerRepository.save(provider);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding provider" + jsnProviderBean.toString() + e.getMessage());
			throw new BusinessException("Exception thrown while adding provider" + jsnProviderBean.toString() + e.getMessage());
		}
	}

	public void update(ProviderJsonBean jsnProviderBean) throws BusinessException {
		try {
			Provider provider = new Provider();
			provider.setId(new Long(jsnProviderBean.getId()));
			provider.setName(jsnProviderBean.getName());
			provider.setDescription(jsnProviderBean.getDescription());
			this.providerRepository.save(provider);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding provider" + jsnProviderBean.toString() + e.getMessage());
			throw new BusinessException("Exception thrown while adding provider" + jsnProviderBean.toString() + e.getMessage());
		}
	}

	public void delete(Long providerId) {
		this.providerRepository.delete(providerId);
	}
}
