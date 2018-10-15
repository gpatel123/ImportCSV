package com.reporting.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.reporting.pojo.Lead;

public interface LeadRepository extends CrudRepository<Lead, Integer>{

	List<Lead> findByAdminId(int adminId);
}
