package com.reporting.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="Lead")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lead {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="channel")
	private String channel;
	
	@Column(name="adminId")
	private int adminId;
	
	@Column(name="status")
	private String status;
	
	@Column(name="sms")
	private boolean sms;
	
	@Column(name="mail")
	private boolean mail;
	
	@Column(name="mobile")
	private String mobile;

	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String address;
	
	@Column(name="configuration")
	private String configuration;
	
	@Column(name="budget")
	private String budget;
	
	@Column(name="hotness")
	private String hotness;
	
	@Column(name="junkreason")
	private String junkreason;
	
	@Column(name="interestText")
	private String interestText;
	
	@Column(name="possession")
	private String possession;
	
	@Column(name="isDeleted")
	private boolean isDeleted=false;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="date")
	private Date createdDate;
	

	@Temporal(TemporalType.DATE)
	@Column(name="updateddate")
	private Date updatedDate;
	
	
	public Lead() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSms() {
		return sms;
	}

	public void setSms(boolean sms) {
		this.sms = sms;
	}

	public boolean isMail() {
		return mail;
	}

	public void setMail(boolean mail) {
		this.mail = mail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getHotness() {
		return hotness;
	}

	public void setHotness(String hotness) {
		this.hotness = hotness;
	}

	public String getJunkreason() {
		return junkreason;
	}

	public void setJunkreason(String junkreason) {
		this.junkreason = junkreason;
	}

	public String getInterestText() {
		return interestText;
	}

	public void setInterestText(String interestText) {
		this.interestText = interestText;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	
	public String getPossession() {
		return possession;
	}

	public void setPossession(String possession) {
		this.possession = possession;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	


	/*
	@Override
	public String toString() {
		return "Leads [id=" + id + ", name=" + name + ", channel=" + channel + ", project=" + project + ", status="
				+ status + ", sms=" + sms + ", mail=" + mail + ", mobile=" + mobile + ", email=" + email + ", address="
				+ address + ", configuration=" + configuration + ", budget=" + budget + ", hotness=" + hotness
				+ ", junkreason=" + junkreason + ", interestText=" + interestText + ", isDeleted=" + isDeleted
				+ ", projects_id=" + projects_id + ", salesPerson=" + salesPerson + ", followUpConnector_id="
				+ followUpConnector_id + "]";
	}*/

}