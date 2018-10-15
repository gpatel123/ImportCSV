package com.reporting.service;

import java.util.List;

import com.reporting.pojo.Lead;

public interface LeadService {

	public void create(Lead lead);
	public List<Lead> getList(int adminId);
	public Lead findById(int id);
	public Lead update(Lead lead, int l);
    public void deleteById(int id);
}
