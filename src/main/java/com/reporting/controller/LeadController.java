package com.reporting.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.brownstone.dto.Errors;
import com.brownstone.dto.LoginDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.reporting.pojo.Admin;
import com.reporting.pojo.Lead;
import com.reporting.service.AdminService;
import com.reporting.service.LeadService;

@CrossOrigin
@RestController
@RequestMapping( "/lead")
public class LeadController {

	
	@Autowired
	LeadService leadService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Lead> getById(@PathVariable("id") int id) {
		System.out.println("Fetching User with id " + id);
		Lead lead = leadService.findById(id);
		if (lead == null) {
			return new ResponseEntity<Lead>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Lead>(lead, HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> create(@RequestBody Lead lead) {
		leadService.create(lead);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/get/{adminId}", headers = "Accept=application/json")
	public List<Lead> getAll(@PathVariable("adminId") int adminId) {
		List<Lead> leadList = leadService.getList(adminId);
		return leadList;
	}
	
	@PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> update(@RequestBody Lead lead){
        Lead newLead = leadService.findById(lead.getId());
        if (newLead==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        
        leadService.update(lead, lead.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

	@PostMapping(value = "/upload/{adminId}")
	public ResponseEntity<String> uploadAttendee(@PathVariable("adminId") int adminId,@RequestParam("file") MultipartFile file){
		
	    String message = "";
	    try {
	    	File newfile = convert(file);
	    /*    storageService.store(file);
	        files.add(file.getOriginalFilename());
*/
	    	CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
	    	CsvMapper mapper = new CsvMapper();
	    	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    //	mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
	   
	    	MappingIterator<Lead> readValues = mapper.readerFor(Lead.class).with(bootstrapSchema).readValues(new InputStreamReader(new FileInputStream(newfile), "ISO-8859-1"));
	    	List<Lead> allValues= readValues.readAll();
	    	
	    
	    	System.out.println(allValues.get(0).getName());
	    	for(Lead lead : allValues ) {
	    		lead.setAdminId(adminId);
	    		System.out.println("Lead Created");
	    		leadService.create(lead);
	    	}
	        message = "You successfully uploaded " + file.getOriginalFilename() + "!";
	        System.out.println("message =================="+message);

	       // Here I am to import into database 


	        return ResponseEntity.status(HttpStatus.OK).body(message);
	    } catch (Exception e) {
	    	e.printStackTrace();
	        message = "FAIL to upload " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	    }
	}	

	/*@PostMapping(value = "upload", consumes =  {"multipart/form-data"})
	public void uploadFile(
			 @RequestPart("properties") @Valid ConnectionProperties properties,
		        @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) {


	    System.out.println("path::"+path);
	    String uploadedFileLocation = path
	            + fileDetail.getFileName();

	    // save it
	    writeToFile(uploadedInputStream, uploadedFileLocation);

	    String output = "File uploaded to : " + uploadedFileLocation;

	//    return Response.status(200).entity(output).build();

	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
	        String uploadedFileLocation) {

	    try {
	        OutputStream out = new FileOutputStream(new File(
	                uploadedFileLocation));
	        int read = 0;
	        byte[] bytes = new byte[1024];

	        out = new FileOutputStream(new File(uploadedFileLocation));
	        while ((read = uploadedInputStream.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	    } catch (IOException e) {

	        e.printStackTrace();
	    }

	   }*/

	public File convert(MultipartFile file) throws IOException{    
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile(); 
	    FileOutputStream fos = new FileOutputStream(convFile); 
	    fos.write(file.getBytes());
	    fos.close(); 
	    return convFile;
	}
}
