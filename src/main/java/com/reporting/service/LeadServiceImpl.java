package com.reporting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reporting.pojo.Lead;
import com.reporting.repository.LeadRepository;

@Service
@Transactional
public class LeadServiceImpl implements LeadService{

	@Autowired
	LeadRepository leadRepository;
	
	@Override
	public void create(Lead lead) {
		leadRepository.save(lead);
		
	}

	@Override
	public List<Lead> getList(int adminId) {
		
		List<Lead> leadList = leadRepository.findByAdminId(adminId);
		return leadList;
	}

	@Override
	public Lead findById(int id) {
		
		Lead lead = leadRepository.findOne(id);
		return lead;
	}

	@Override
	public Lead update(Lead lead, int l) {
		lead = leadRepository.save(lead);
		return lead;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
