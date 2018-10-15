package com.reporting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brownstone.dto.Errors;
import com.brownstone.dto.LoginDTO;
import com.reporting.pojo.Admin;
import com.reporting.service.AdminService;

@CrossOrigin
@RestController
@RequestMapping( "/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Admin> getUserById(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);
		Admin admin = adminService.findById(id);
		if (admin == null) {
			return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> create(@RequestBody Admin admin) {
		adminService.createAdmin(admin);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Admin> getAllUser() {
		List<Admin> admin = adminService.getAdmin();
		return admin;
	}
	
	@PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> update(@RequestBody Admin admin){
        Admin newAdmin = adminService.findById(admin.getId());
        if (newAdmin==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        
        adminService.update(admin, admin.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

	@PostMapping(value = "/login", headers = "Accept=application/json")
	public ResponseEntity<Object> login(@RequestBody LoginDTO login) {
	
		Admin admin = adminService.login(login);
		if(admin !=null) {
	
			return new ResponseEntity<Object>(admin,HttpStatus.CREATED);
	
		}else {
			Errors error = new Errors();
			error.setErrorCode(HttpStatus.UNAUTHORIZED.toString());
			error.setMessage("Invalid username or password");
			return new ResponseEntity<Object>(error,HttpStatus.UNAUTHORIZED);
		}
		
		
	}
	
}
