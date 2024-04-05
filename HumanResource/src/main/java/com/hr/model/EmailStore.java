package com.hr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "hr_email")
public class EmailStore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "scheduled_id")
	private long scheduled_id;
	@Column(name = "receiver")
	private String receiver;
	@Column(name = "receiver_emailid")
	private String receiverEmailId;
	@Column(name = "email_subject")
	private String emailSubject;
	@Column(name = "email_text")
	private String emailText;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "email_datetime")
	private Date emailDateTime;
	@Column(name = "email_status")
	private String emailStatus;
	@Column(name = "email_attachment")
	private String emailattachment;
	
	
	public EmailStore() {
		super();
	
	}


	public EmailStore(int id, long scheduled_id, String receiver, String receiverEmailId, String emailSubject,
			String emailText, Date emailDateTime, String emailStatus, String emailattachment) {
		super();
		this.id = id;
		this.scheduled_id = scheduled_id;
		this.receiver = receiver;
		this.receiverEmailId = receiverEmailId;
		this.emailSubject = emailSubject;
		this.emailText = emailText;
		this.emailDateTime = emailDateTime;
		this.emailStatus = emailStatus;
		this.emailattachment = emailattachment;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public long getScheduled_id() {
		return scheduled_id;
	}


	public void setScheduled_id(long scheduled_id) {
		this.scheduled_id = scheduled_id;
	}


	public String getReceiver() {
		return receiver;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public String getReceiverEmailId() {
		return receiverEmailId;
	}


	public void setReceiverEmailId(String receiverEmailId) {
		this.receiverEmailId = receiverEmailId;
	}


	public String getEmailSubject() {
		return emailSubject;
	}


	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}


	public String getEmailText() {
		return emailText;
	}


	public void setEmailText(String emailText) {
		this.emailText = emailText;
	}


	public Date getEmailDateTime() {
		return emailDateTime;
	}


	public void setEmailDateTime(Date emailDateTime) {
		this.emailDateTime = emailDateTime;
	}


	public String getEmailStatus() {
		return emailStatus;
	}


	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}


	public String getEmailattachment() {
		return emailattachment;
	}


	public void setEmailattachment(String emailattachment) {
		this.emailattachment = emailattachment;
	}


	@Override
	public String toString() {
		return "EmailStore [id=" + id + ", scheduled_id=" + scheduled_id + ", receiver=" + receiver
				+ ", receiverEmailId=" + receiverEmailId + ", emailSubject=" + emailSubject + ", emailText=" + emailText
				+ ", emailDateTime=" + emailDateTime + ", emailStatus=" + emailStatus + ", emailattachment="
				+ emailattachment + "]";
	}

    
	
	
	
}
