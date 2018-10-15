package com.reporting.service;

import java.util.List;

import com.brownstone.dto.LoginDTO;
import com.reporting.pojo.Admin;

public interface AdminService {

	public void createAdmin(Admin admin);
	public List<Admin> getAdmin();
	public Admin findById(long id);
	public Admin update(Admin admin, long l);
    public void deleteAdminById(long id);
    public Admin login(LoginDTO loginDTO);
}
