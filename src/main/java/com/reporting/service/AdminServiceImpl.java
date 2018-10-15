package com.reporting.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brownstone.dto.LoginDTO;
import com.reporting.pojo.Admin;
import com.reporting.repository.AdminRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public void createAdmin(Admin admin) {
		
		adminRepository.save(admin);
	}

	@Override
	public List<Admin> getAdmin() {
		return (List<Admin>) adminRepository.findAll();
	}

	@Override
	public Admin findById(long id) {
		Admin admin = adminRepository.findOne(id);
		return admin;
	}

	@Override
	public Admin update(Admin admin, long l) {
		// TODO Auto-generated method stub
		return adminRepository.save(admin);
	}

	@Override
	public void deleteAdminById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Admin login(LoginDTO loginDTO) {
		String selectQuery = "FROM Admin where username= :username and password= :password";

		try {
		Query query = entityManager.createQuery(selectQuery);
		query.setParameter("username", loginDTO.getUsername());
		query.setParameter("password", loginDTO.getPassword());
		Object result =query.getSingleResult();
		if(result!=null) {
			return (Admin) result;
		}else {
		return null;
		}
		}catch (NoResultException nre){
			return null;
		}
	}

	
}
