package com.hr.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hr.config.EmailAuditorAware;
import com.hr.config.EmailConfig;
import com.hr.config.PasswordEncryption;
import com.hr.dto.InterviewScheduleIdDto;
import com.hr.dto.InterviewerHrDto;
import com.hr.dto.roundPrevDto;
import com.hr.model.EmailStore;
import com.hr.model.ScheduledInterview;
import com.hr.repository.EmailStoreRepository;
import com.hr.repository.EmployeeRepository;
import com.hr.repository.candidaterepository;

@Service
public class EmailService {

	@Autowired
	public EmailConfig emailConfig;

	@Autowired
	public EmailStoreRepository es;

	@Autowired
	public candidaterepository cr;

	@Autowired
	public EmployeeRepository er;

	@Autowired
	PasswordEncryption passwordEncryption;

	@Autowired
	public EmailAuditorAware emailAuditorAware;

	@Autowired
	public EmailStoreRepository emailStoreRepository;

	@Value("${attachmentDocumentDrive}")
	String attachmentDocumentDrive;

	@Value("${attachmentIcsDrive}")
	String attachmentIcsDrive;

	Date currentDate = new Date();

	// Interview invitaion to candidate
	public void scheduleInteviewEmailToCandidate(ScheduledInterview si, InterviewScheduleIdDto d, InterviewerHrDto hr,
			String emailAddress) {
		try {
			EmailStore es = new EmailStore();

			if (si.getModeNo().equals("Video")) {
				String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
						+ "<br>Thank you for applying for the position of <span style='color:blue;'>" + d.getProfile()
						+ " </span> with Enhanced Software Solutions Pvt. Ltd., "
						+ "We are glad to inform you that your interview has been scheduled on"
						+ "&nbsp;<span style='color:blue;'>" + si.getRoundDateSort()
						+ " </span> &nbsp;at &nbsp;<span style='color:blue;'>" + si.getRoundTime() + ".</span><br><br>"
						+ "<br><b>Communication Link:&nbsp;</b>" + si.getVideoLinkDetails()
						+ "<br><br><b>Company Profile</b>: Founded in 1997 and headquartered in Mumbai, India, with offices throughout India and Singapore, Enhanced Software Solutions offers a variety of IT solutions-Turnkey Software Solutions, Business Service Management, Network Design and ITIL training and consultancy to enhance their customers’ productivity."
						+ "<br><br>Our mission is to develop and maintain beneficial and enduring relationships with their customers helping them implement IT solutions aligned to their business needs and enabling them to move towards market leadership."
						+ "<br><br>With a successful track record of delivering state-of-the-art IT solutions to a broad range of customers both on time and within specified budget parameters ESS is considered a valuable business partner to its clients."
						+ "<br><br>ESS specializes in IT solutions for the manufacturing, banking and finance, pharmaceuticals and warehousing industries."
						+ "<br><br><br>For more information about company please visit: http://ess.net.in//"
						+ "<br><br>In case of any query, kindly contact on " + hr.getEmpPhone()
						+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
						+ hr.getEmail();
				sendEmail(emailAddress, htmlContent, "ESSPL - Invitation for Interview Scheduled on "
						+ si.getRoundDateSort() + " - " + d.getProfile());
				es.setReceiver(si.getCandidateFullName());
				es.setReceiverEmailId(emailAddress);
				es.setEmailSubject("ESSPL - Invitation for Interview Scheduled on  " + si.getRoundDateSort() + " - "
						+ d.getProfile());
				es.setEmailText(htmlContent);
				es.setScheduled_id(si.getScheduledInterviewId());
				es.setEmailDateTime(currentDate);
				es.setEmailStatus("Email Send");
				emailStoreRepository.save(es);

			} else if (si.getModeNo().trim().equals("Telephonic")) {
				String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
						+ "<br>Thank you for applying for the position of <span style='color:blue;'>" + d.getProfile()
						+ " </span> with Enhanced Software Solutions Pvt. Ltd., "
						+ "We are glad to inform you that your interview has been scheduled on"
						+ "&nbsp;<span style='color:blue;'>" + si.getRoundDateSort()
						+ " </span> &nbsp;at &nbsp;<span style='color:blue;'>" + si.getRoundTime() + ".</span><br>"
						+ "<br>Mode of Interview is Telephonic.<br><br>"
						+ "<br><b>Company Profile</b>: Founded in 1997 and headquartered in Mumbai, India, with offices throughout India and Singapore, Enhanced Software Solutions offers a variety of IT solutions-Turnkey Software Solutions, Business Service Management, Network Design and ITIL training and consultancy to enhance their customers’ productivity."
						+ "<br><br>Our mission is to develop and maintain beneficial and enduring relationships with their customers helping them implement IT solutions aligned to their business needs and enabling them to move towards market leadership."
						+ "<br><br>With a successful track record of delivering state-of-the-art IT solutions to a broad range of customers both on time and within specified budget parameters ESS is considered a valuable business partner to its clients."
						+ "<br><br>ESS specializes in IT solutions for the manufacturing, banking and finance, pharmaceuticals and warehousing industries."
						+ "<br><br><br>For more information about company please visit: http://ess.net.in//"
						+ "<br><br>In case of any query, kindly contact on " + hr.getEmpPhone()
						+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
						+ hr.getEmail();
				sendEmail(emailAddress, htmlContent, "ESSPL - Invitation for Interview Scheduled on "
						+ si.getRoundDateSort() + " - " + d.getProfile());
				es.setReceiver(si.getCandidateFullName());
				es.setReceiverEmailId(emailAddress);
				es.setEmailSubject("ESSPL - Invitation for Interview Scheduled on  " + si.getRoundDateSort() + " - "
						+ d.getProfile());
				es.setEmailText(htmlContent);
				es.setScheduled_id(si.getScheduledInterviewId());
				es.setEmailDateTime(currentDate);
				es.setEmailStatus("Email Send");
				emailStoreRepository.save(es);
			} else {
				String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
						+ "<br>Thank you for applying for the position of <span style='color:blue;'>" + d.getProfile()
						+ " </span> with Enhanced Software Solutions Pvt. Ltd., "
						+ "We are glad to inform you that your interview has been scheduled on"
						+ "&nbsp;<span style='color:blue;'>" + si.getRoundDateSort()
						+ " </span> &nbsp;at &nbsp;<span style='color:blue;'>" + si.getRoundTime() + ".</span><br><br>"
						+ "<br><br><b>Venue Address:</b> Enhanced Software Solutions Pvt. Ltd."
						+ "909, 9th Floor, Lodha Supremus," + "Road No 22, MIDC, Wagle Estate,"
						+ "Thane(W) 400604. India"
						+ "<br><br><b>Company Profile</b>: Founded in 1997 and headquartered in Mumbai, India, with offices throughout India and Singapore, Enhanced Software Solutions offers a variety of IT solutions-Turnkey Software Solutions, Business Service Management, Network Design and ITIL training and consultancy to enhance their customers’ productivity."
						+ "<br><br>Our mission is to develop and maintain beneficial and enduring relationships with their customers helping them implement IT solutions aligned to their business needs and enabling them to move towards market leadership."
						+ "<br><br>With a successful track record of delivering state-of-the-art IT solutions to a broad range of customers both on time and within specified budget parameters ESS is considered a valuable business partner to its clients."
						+ "<br><br>ESS specializes in IT solutions for the manufacturing, banking and finance, pharmaceuticals and warehousing industries."
						+ "<br><br><br>For more information about company please visit: http://ess.net.in//"
						+ "<br><br>In case of any query, kindly contact on " + hr.getEmpPhone()
						+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
						+ hr.getEmail();
				sendEmail(emailAddress, htmlContent, "ESSPL - Invitation for Interview Scheduled on "
						+ si.getRoundDateSort() + " - " + d.getProfile());
				es.setReceiver(si.getCandidateFullName());
				es.setReceiverEmailId(emailAddress);
				es.setEmailSubject("ESSPL - Invitation for Interview Scheduled on  " + si.getRoundDateSort() + " - "
						+ d.getProfile());
				es.setEmailText(htmlContent);
				es.setScheduled_id(si.getScheduledInterviewId());
				es.setEmailDateTime(currentDate);
				es.setEmailStatus("Email Send");
				emailStoreRepository.save(es);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Interview invitaion to Candidate
	public void roundWiseEmailToCandidate(ScheduledInterview si, InterviewScheduleIdDto d, roundPrevDto rd,
			InterviewerHrDto hr, String emailAddress) {
		EmailStore e = new EmailStore();
		try {

			if (si.getModeNo().equals("Video")) {
				String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
						+ "<br>We are happy to inform you that you have been shortlisted in " + rd.getRoundName()
						+ " round of interview and we would like to invite you for " + d.getRoundName()
						+ " interview. Your " + d.getRoundName()
						+ " interview is scheduled on <span style='color:blue;'>&nbsp;" + si.getRoundDateSort()
						+ "</span> at  <span style='color:blue;'>" + si.getRoundTime() + "</span>.<br>"
						+ "<br>Please join below zoom meeting link for interview (before 5 minutes of interview time).<br>"
						+ "<br><b>Communication Link:&nbsp;</b>" + si.getVideoLinkDetails() + "<br>"
						+ "<br><br>All the best!!<br>" + "<br><br>In case of any query, kindly contact on "
						+ hr.getEmpPhone() + "<br>" + "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>"
						+ hr.getEmpPhone() + "<br>" + hr.getEmail();
				sendEmail(emailAddress, htmlContent, "ESSPL - Invitation for Interview Scheduled on "
						+ si.getRoundDateSort() + " - " + d.getProfile());
				e.setReceiver(si.getCandidateFullName());
				e.setReceiverEmailId(emailAddress);
				e.setEmailSubject("ESSPL - Invitation for Interview Scheduled on  " + si.getRoundDateSort() + " - "
						+ d.getProfile());
				e.setEmailText(htmlContent);
				e.setScheduled_id(si.getScheduledInterviewId());
				e.setEmailDateTime(currentDate);
				e.setEmailStatus("Email Send");
				emailStoreRepository.save(e);

			} else if (si.getModeNo().equalsIgnoreCase("Face To Face")) {
				String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
						+ "<br>We are happy to inform you that you have been shortlisted in " + rd.getRoundName()
						+ "&nbsp;round of interview and we would like to invite you for " + d.getRoundName()
						+ " interview. Your " + d.getRoundName()
						+ " interview is scheduled on<span style='color:blue;'>&nbsp;" + si.getRoundDateSort()
						+ "</span> at <span style='color:blue;'>" + si.getRoundTime() + "</span>.<br>"
						+ "<br><b>Venue Address:</b> Enhanced Software Solutions Pvt. Ltd."
						+ "909, 9th Floor, Lodha Supremus," + "Road No 22, MIDC, Wagle Estate,"
						+ "Thane(W) 400604. India" + "<br><br>All the best!!<br>"
						+ "<br><br>In case of any query, kindly contact on " + hr.getEmpPhone() + "<br>"
						+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
						+ hr.getEmail();
				sendEmail(emailAddress, htmlContent, "ESSPL - Invitation for Interview Scheduled on "
						+ si.getRoundDateSort() + " - " + d.getProfile());
				e.setReceiver(si.getCandidateFullName());
				e.setReceiverEmailId(emailAddress);
				e.setEmailSubject("ESSPL - Invitation for Interview Scheduled on  " + si.getRoundDateSort() + " - "
						+ d.getProfile());
				e.setEmailText(htmlContent);
				e.setScheduled_id(si.getScheduledInterviewId());
				e.setEmailDateTime(currentDate);
				e.setEmailStatus("Email Send");
				emailStoreRepository.save(e);
			} else {
				String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
						+ "<br>We are happy to inform you that you have been shortlisted in" + rd.getRoundName()
						+ "&nbsp;round of interview and we would like to invite you for " + d.getRoundName()
						+ "  interview. Your " + d.getRoundName()
						+ " round interview is scheduled on<span style='color:blue;'>&nbsp;" + si.getRoundDateSort()
						+ "</span> at <span style='color:blue;'>" + si.getRoundTime() + "</span>.<br>"
						+ "<br>Mode of Interview is Telephonic.<br>" + "<br><br>All the best!!<br>"
						+ "<br><br>In case of any query, kindly contact on " + hr.getEmpPhone() + "<br>"
						+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
						+ hr.getEmail();
				sendEmail(emailAddress, htmlContent, "ESSPL - Invitation for Interview Scheduled on "
						+ si.getRoundDateSort() + " - " + d.getProfile());
				e.setReceiver(si.getCandidateFullName());
				e.setReceiverEmailId(emailAddress);
				e.setEmailSubject("ESSPL - Invitation for Interview Scheduled on  " + si.getRoundDateSort() + " - "
						+ d.getProfile());
				e.setEmailText(htmlContent);
				e.setScheduled_id(si.getScheduledInterviewId());
				e.setEmailDateTime(currentDate);
				e.setEmailStatus("Email Send");
				emailStoreRepository.save(e);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Scheduled Interview invitaion to Interviewer
	public void scheduleInteviewEmailToInterviewer(ScheduledInterview si, InterviewScheduleIdDto d, InterviewerHrDto hr,
			String emailAddress) {
		try {

			EmailStore em = new EmailStore();

			if (si.getModeNo().equals("Video")) {
				String htmlContent = "Hello Sir/Ma'am, <br>"
						+ "<br>PFA resume and details for interview scheduled on <span style='color:blue;'>"
						+ si.getRoundDateSort() + "</span> at  <span style='color:blue;'>" + si.getRoundTime()
						+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
						+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
						+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
						+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Notice Period:&nbsp;" + d.getNoticePeriod()
						+ "&nbsp;Days" + "<br>Candidate Contact No:&nbsp;" + d.getContactNumber()
						+ "<br>Meeting Link:&nbsp;" + si.getVideoLinkDetails() + "<br>Communication Rating(1/10):&nbsp;"
						+ si.getCommunicationRating() + "<br>Ready To Relocate:&nbsp;" + d.getReadyToRelocate()
						+ "<br>HR Interview Feedback:&nbsp;" + d.getConstructiveFeedback()
						+ "<br>Interviewer Name:&nbsp;" + d.getFullName()
						+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
						+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
						+ hr.getEmail();
				sendEmailAttachment(emailAddress, htmlContent,
						"Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
						si.getCandidateResume(), si.getIcsFile());
				em.setReceiver(d.getFullName());
				em.setReceiverEmailId(emailAddress);
				em.setEmailSubject("Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
				em.setEmailText(htmlContent);
				em.setScheduled_id(si.getScheduledInterviewId());
				em.setEmailDateTime(currentDate);
				em.setEmailattachment(si.getCandidateResume());
				em.setEmailStatus("Email Send");
				emailStoreRepository.save(em);
			} else {
				String htmlContent = "Hello Sir/Ma'am, <br>"
						+ "<br>PFA resume and details for interview scheduled on <span style='color:blue;'>"
						+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
						+ "</span>." + "<br><br>Candidate Name: &nbsp;" + si.getCandidateFullName()
						+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
						+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
						+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Notice Period:&nbsp;" + d.getNoticePeriod()
						+ "&nbsp;Days" + "<br>Candidate Contact No:&nbsp;" + d.getContactNumber()
						+ "<br>Communication Rating(1/10):&nbsp;" + si.getCommunicationRating()
						+ "<br>Ready To Relocate:&nbsp;" + d.getReadyToRelocate() + "<br>HR Interview Feedback:&nbsp;"
						+ d.getConstructiveFeedback() + "<br>Interviewer Name:&nbsp;" + d.getFullName()
						+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
						+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
						+ hr.getEmail();
				sendEmailAttachment(emailAddress, htmlContent,
						"Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
						si.getCandidateResume(), si.getIcsFile());
				em.setReceiver(d.getFullName());
				em.setReceiverEmailId(emailAddress);
				em.setEmailSubject("Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
				em.setEmailText(htmlContent);
				em.setScheduled_id(si.getScheduledInterviewId());
				em.setEmailDateTime(currentDate);
				em.setEmailattachment(si.getCandidateResume());
				em.setEmailStatus("Email Send");
				emailStoreRepository.save(em);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Scheduled interview invitaion to Interviewer for HR Round
	public void InteviewScheduleEmailToInterviewer(ScheduledInterview si, InterviewScheduleIdDto d, InterviewerHrDto hr,
			String emailAddress) {
		try {
			EmailStore email = new EmailStore();

			if (si.getModeNo().equals("Video")) {
				String htmlContent = "Hello Sir/Ma'am, <br>"
						+ "<br>PFA resume and details for interview scheduled on <span style='color:blue;'>"
						+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
						+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
						+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
						+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
						+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Candidate Contact No:&nbsp;"
						+ d.getContactNumber() + "<br>Meeting Link:&nbsp;" + si.getVideoLinkDetails()
						+ "<br>Interviewer Name:&nbsp;" + d.getFullName() + "<br><br>Thanks & Regards,<br>"
						+ hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>" + hr.getEmail();
				sendEmailAttachment(emailAddress, htmlContent,
						"Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
						si.getCandidateResume(), si.getIcsFile());
				email.setReceiver(d.getFullName());
				email.setReceiverEmailId(emailAddress);
				email.setEmailSubject("Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
				email.setEmailText(htmlContent);
				email.setScheduled_id(si.getScheduledInterviewId());
				email.setEmailDateTime(currentDate);
				email.setEmailattachment(si.getCandidateResume());
				email.setEmailStatus("Email Send");
				emailStoreRepository.save(email);
			} else {
				String htmlContent = "Hello Sir/Ma'am, <br>"
						+ "<br>PFA resume and details for interview scheduled on  <span style='color:blue;'>"
						+ si.getRoundDateSort() + "</span> at  <span style='color:blue;'>" + si.getRoundTime()
						+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
						+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
						+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
						+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Candidate Contact No:&nbsp;"
						+ d.getContactNumber() + "<br>Interviewer Name:&nbsp;" + d.getFullName()
						+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
						+ hr.getEmail();
				sendEmailAttachment(emailAddress, htmlContent,
						"Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
						si.getCandidateResume(), si.getIcsFile());
				email.setReceiver(d.getFullName());
				email.setReceiverEmailId(emailAddress);
				email.setEmailSubject("Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
				email.setEmailText(htmlContent);
				email.setScheduled_id(si.getScheduledInterviewId());
				email.setEmailDateTime(currentDate);
				email.setEmailattachment(si.getCandidateResume());
				email.setEmailStatus("Email Send");
				emailStoreRepository.save(email);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Rescheduled Interview invitaion to Interviewer
	// Change getName to Createdby for else condition after Thanks & Regards,
	// October 9 2023
	public void rescheduledInteviewEmailToInterviewer(ScheduledInterview si, InterviewScheduleIdDto d,
			InterviewerHrDto hr, String emailAddress) {
		try {
			EmailStore ei = new EmailStore();
			if (si.getRoundDetails() == 1) {
				if (si.getModeNo().equals("Video")) {
					String htmlContent = "Hello Sir/Ma'am, <br>"
							+ "<br>PFA resume and details for interview rescheduled on <span style='color:blue;'>"
							+ si.getRoundDateSort() + "</span> at  <span style='color:blue;'>" + si.getRoundTime()
							+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
							+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
							+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
							+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Notice Period:&nbsp;" + d.getNoticePeriod()
							+ "&nbsp;Days" + "<br>Candidate Contact No:&nbsp;" + d.getContactNumber()
							+ "<br>Meeting Link:&nbsp;" + si.getVideoLinkDetails() + "<br>Interviewer Name:&nbsp;"
							+ d.getFullName() + "<br><br>To view the technical feedback, Kindly login to the HR Portal."
							+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
							+ hr.getEmail();
					sendEmailAttachment(emailAddress, htmlContent,
							"Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
							si.getCandidateResume(), si.getIcsFile());
					ei.setReceiver(d.getFullName());
					ei.setReceiverEmailId(emailAddress);
					ei.setEmailSubject("Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
					ei.setEmailText(htmlContent);
					ei.setScheduled_id(si.getScheduledInterviewId());
					ei.setEmailDateTime(currentDate);
					ei.setEmailattachment(si.getCandidateResume());
					ei.setEmailStatus("Email Send");
					emailStoreRepository.save(ei);

				} else {
					String htmlContent = "Hello Sir/Ma'am, <br>"
							+ "<br>PFA resume and details for interview rescheduled on <span style='color:blue;'>"
							+ si.getRoundDateSort() + "</span> at  <span style='color:blue;'>" + si.getRoundTime()
							+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
							+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
							+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
							+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Notice Period:&nbsp;" + d.getNoticePeriod()
							+ "&nbsp;Days" + "<br>Candidate Contact No:&nbsp;" + d.getContactNumber()
							+ "<br>Interviewer Name:&nbsp;" + d.getFullName()
							+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
							+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
							+ hr.getEmail();
					sendEmailAttachment(emailAddress, htmlContent,
							"Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
							si.getCandidateResume(), si.getIcsFile());
					ei.setReceiver(d.getFullName());
					ei.setReceiverEmailId(emailAddress);
					ei.setEmailSubject("Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
					ei.setEmailText(htmlContent);
					ei.setScheduled_id(si.getScheduledInterviewId());
					ei.setEmailDateTime(currentDate);
					ei.setEmailattachment(si.getCandidateResume());
					ei.setEmailStatus("Email Send");
					emailStoreRepository.save(ei);

				}

			} else {

				if (si.getModeNo().equals("Video")) {
					String htmlContent = "Hello Sir/Ma'am, <br>"
							+ "<br>PFA resume and details for interview rescheduled on <span style='color:blue;'>"
							+ si.getRoundDateSort() + "</span> at  <span style='color:blue;'>" + si.getRoundTime()
							+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
							+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
							+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
							+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Notice Period:&nbsp;" + d.getNoticePeriod()
							+ "&nbsp;Days" + "<br>Candidate Contact No:&nbsp;" + d.getContactNumber()
							+ "<br>Ready To Relocate:&nbsp;" + d.getReadyToRelocate() + "<br>Meeting Link:&nbsp;"
							+ si.getVideoLinkDetails() + "<br>HR Interview Feedback:&nbsp;"
							+ d.getConstructiveFeedback() + "<br>Interviewer Name:&nbsp;" + d.getFullName()
							+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
							+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
							+ hr.getEmail();
					sendEmailAttachment(emailAddress, htmlContent,
							"Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
							si.getCandidateResume(), si.getIcsFile());
					ei.setReceiver(d.getFullName());
					ei.setReceiverEmailId(emailAddress);
					ei.setEmailSubject("Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
					ei.setEmailText(htmlContent);
					ei.setScheduled_id(si.getScheduledInterviewId());
					ei.setEmailDateTime(currentDate);
					ei.setEmailattachment(si.getCandidateResume());
					ei.setEmailStatus("Email Send");
					emailStoreRepository.save(ei);

				} else {
					String htmlContent = "Hello Sir/Ma'am, <br>"
							+ "<br>PFA resume and details for interview rescheduled on <span style='color:blue;'>"
							+ si.getRoundDateSort() + "</span> at  <span style='color:blue;'>" + si.getRoundTime()
							+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
							+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
							+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
							+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Notice Period:&nbsp;" + d.getNoticePeriod()
							+ "&nbsp;Days" + "<br>Ready To Relocate:&nbsp;" + d.getReadyToRelocate()
							+ "<br>Candidate Contact No:&nbsp;" + d.getContactNumber()
							+ "<br>HR Interview Feedback:&nbsp;" + d.getConstructiveFeedback()
							+ "<br>Interviewer Name:&nbsp;" + d.getFullName()
							+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
							+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
							+ hr.getEmail();
					sendEmailAttachment(emailAddress, htmlContent,
							"Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
							si.getCandidateResume(), si.getIcsFile());
					ei.setReceiver(d.getFullName());
					ei.setReceiverEmailId(emailAddress);
					ei.setEmailSubject("Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
					ei.setEmailText(htmlContent);
					ei.setScheduled_id(si.getScheduledInterviewId());
					ei.setEmailDateTime(currentDate);
					ei.setEmailattachment(si.getCandidateResume());
					ei.setEmailStatus("Email Send");
					emailStoreRepository.save(ei);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// final round interview invitation to interviewer
	public void InteviewScheduleEmailToInterviewerFinal(ScheduledInterview si, InterviewScheduleIdDto d,
			InterviewerHrDto hr, String emailAddress) {
		try {

			EmailStore emailinterview = new EmailStore();
			String groupDescription = d.getGroupDescription();
			if (groupDescription != null && groupDescription.trim().equals("Interviewer Level 1")) {
				if (si.getModeNo().equals("Video")) {
					String htmlContent = "Hello Sir/Ma'am, <br>"
							+ "<br>PFA resume and details for interview scheduled on <span style='color:blue;'>"
							+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
							+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
							+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
							+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
							+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Notice Period:&nbsp;" + d.getNoticePeriod()
							+ "&nbsp;Days" + "<br>Candidate Contact No:&nbsp;" + d.getContactNumber()
							+ "<br>Communication Rating(1/10):&nbsp;" + si.getCommunicationRating()
							+ "<br>Ready To Relocate:&nbsp;" + d.getReadyToRelocate() + "<br>Profile Relevance:&nbsp;"
							+ si.getProfileRelevance() + "%" + "<br>Meeting Link:&nbsp;" + si.getVideoLinkDetails()
							+ "<br>HR Interview Feedback:&nbsp;" + d.getConstructiveFeedback()
							+ "<br>Interviewer Name:&nbsp;" + d.getFullName()
							+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
							+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
							+ hr.getEmail();
					sendEmailAttachment(emailAddress, htmlContent,
							"Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
							si.getCandidateResume(), si.getIcsFile());
					emailinterview.setReceiver(d.getFullName());
					emailinterview.setReceiverEmailId(emailAddress);
					emailinterview.setEmailSubject(
							"Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
					emailinterview.setEmailText(htmlContent);
					emailinterview.setScheduled_id(si.getScheduledInterviewId());
					emailinterview.setEmailDateTime(currentDate);
					emailinterview.setEmailattachment(si.getCandidateResume());
					emailinterview.setEmailStatus("Email Send");
					emailStoreRepository.save(emailinterview);

				} else {
					String htmlContent = "Hello Sir/Ma'am, <br>"
							+ "<br>PFA resume and details for interview scheduled on <span style='color:blue;'>"
							+ si.getRoundDateSort() + "</span> at  <span style='color:blue;'>" + si.getRoundTime()
							+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
							+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
							+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
							+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Notice Period:&nbsp;" + d.getNoticePeriod()
							+ "&nbsp;Days" + "<br>Candidate Contact No:&nbsp;" + d.getContactNumber()
							+ "<br>Communication Rating(1/10):&nbsp;" + si.getCommunicationRating()
							+ "<br>Ready To Relocate:&nbsp;" + d.getReadyToRelocate() + "<br>Profile Relevance:&nbsp;"
							+ si.getProfileRelevance() + "%" + "<br>HR Interview Feedback:&nbsp;"
							+ d.getConstructiveFeedback() + "<br>Interviewer Name:&nbsp;" + d.getFullName()
							+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
							+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
							+ hr.getEmail();
					sendEmailAttachment(emailAddress, htmlContent,
							"Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
							si.getCandidateResume(), si.getIcsFile());
					emailinterview.setReceiver(d.getFullName());
					emailinterview.setReceiverEmailId(emailAddress);
					emailinterview.setEmailSubject(
							"Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
					emailinterview.setEmailText(htmlContent);
					emailinterview.setScheduled_id(si.getScheduledInterviewId());
					emailinterview.setEmailDateTime(currentDate);
					emailinterview.setEmailattachment(si.getCandidateResume());
					emailinterview.setEmailStatus("Email Send");
					emailStoreRepository.save(emailinterview);

				}
			} else {

				if (si.getModeNo().equals("Video")) {
					String htmlContent = "Hello Sir/Ma'am, <br>"
							+ "<br>PFA resume and details for interview scheduled on <span style='color:blue;'>"
							+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
							+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
							+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
							+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
							+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Notice Period:&nbsp;" + d.getNoticePeriod()
							+ "&nbsp;Days" + "<br>Cost to Comapany(CTC):&nbsp;" + d.getCurrentSalary()
							+ "<br>Expected Cost to Company(ECTC):&nbsp;" + d.getExpectedSalary()
							+ "<br>Candidate Contact No:&nbsp;" + d.getContactNumber()
							+ "<br>Communication Rating(1/10):&nbsp;" + si.getCommunicationRating()
							+ "<br>Ready To Relocate:&nbsp;" + d.getReadyToRelocate() + "<br>Profile Relevance:&nbsp;"
							+ si.getProfileRelevance() + "%" + "<br>Meeting Link:&nbsp;" + si.getVideoLinkDetails()
							+ "<br>HR Interview Feedback:&nbsp;" + d.getConstructiveFeedback()
							+ "<br>Interviewer Name:&nbsp;" + d.getFullName()
							+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
							+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
							+ hr.getEmail();
					sendEmailAttachment(emailAddress, htmlContent,
							"Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
							si.getCandidateResume(), si.getIcsFile());
					emailinterview.setReceiver(d.getFullName());
					emailinterview.setReceiverEmailId(emailAddress);
					emailinterview.setEmailSubject(
							"Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
					emailinterview.setEmailText(htmlContent);
					emailinterview.setScheduled_id(si.getScheduledInterviewId());
					emailinterview.setEmailDateTime(currentDate);
					emailinterview.setEmailattachment(si.getCandidateResume());
					emailinterview.setEmailStatus("Email Send");
					emailStoreRepository.save(emailinterview);

				} else {
					String htmlContent = "Hello Sir/Ma'am, <br>"
							+ "<br>PFA resume and details for interview scheduled on <span style='color:blue;'>"
							+ si.getRoundDateSort() + "</span> at  <span style='color:blue;'>" + si.getRoundTime()
							+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
							+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
							+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
							+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Notice Period:&nbsp;" + d.getNoticePeriod()
							+ "&nbsp;Days" + "<br>Cost to Comapany(CTC):&nbsp;" + d.getCurrentSalary()
							+ "<br>Expected Cost to Company(ECTC):&nbsp;" + d.getExpectedSalary()
							+ "<br>Candidate Contact No:&nbsp;" + d.getContactNumber()
							+ "<br>Communication Rating(1/10):&nbsp;" + si.getCommunicationRating()
							+ "<br>Ready To Relocate:&nbsp;" + d.getReadyToRelocate() + "<br>Profile Relevance:&nbsp;"
							+ si.getProfileRelevance() + "%" + "<br>HR Interview Feedback:&nbsp;"
							+ d.getConstructiveFeedback() + "<br>Interviewer Name:&nbsp;" + d.getFullName()
							+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
							+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
							+ hr.getEmail();
					sendEmailAttachment(emailAddress, htmlContent,
							"Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
							si.getCandidateResume(), si.getIcsFile());
					emailinterview.setReceiver(d.getFullName());
					emailinterview.setReceiverEmailId(emailAddress);
					emailinterview.setEmailSubject(
							"Interview Scheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
					emailinterview.setEmailText(htmlContent);
					emailinterview.setScheduled_id(si.getScheduledInterviewId());
					emailinterview.setEmailDateTime(currentDate);
					emailinterview.setEmailattachment(si.getCandidateResume());
					emailinterview.setEmailStatus("Email Send");
					emailStoreRepository.save(emailinterview);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Reschedule Interview invitation to Candidate for next round
	public void reScheduleInteviewEmailToCandidate(ScheduledInterview si, InterviewScheduleIdDto d, InterviewerHrDto hr,
			String emailAddress) {
		try {
			EmailStore emailcan = new EmailStore();
			if (si.getModeNo().equals("Video")) {
				String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
						+ "<br>As per our telephonic conversation, we have rescheduled your technical interview on <span style='color:blue;'>"
						+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
						+ "</span>&nbsp;&nbsp;Please find the link for interview below:"
						+ "<br><br> Communication link:&nbsp;&nbsp;" + si.getVideoLinkDetails() + "<br><br>"
						+ "<br><br>In case of any query, kindly contact on " + hr.getEmpPhone() + "<br>"
						+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
						+ hr.getEmail();
				sendEmail(emailAddress, htmlContent, "ESSPL- Invitation for Interview Rescheduled on "
						+ si.getRoundDateSort() + " - " + d.getProfile());
				emailcan.setReceiver(si.getCandidateFullName());
				emailcan.setReceiverEmailId(emailAddress);
				emailcan.setEmailSubject("ESSPL- Invitation for Interview Rescheduled on " + si.getRoundDateSort()
						+ " - " + d.getProfile());
				emailcan.setEmailText(htmlContent);
				emailcan.setScheduled_id(si.getScheduledInterviewId());
				emailcan.setEmailDateTime(currentDate);
				emailcan.setEmailStatus("Email Send");
				emailStoreRepository.save(emailcan);

			} else if (si.getModeNo().equals("Telephonic")) {
				String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
						+ "<br>As discussed on call, we have rescheduled your telephonic technical interview on <span style='color:blue;'>"
						+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
						+ "</span>&nbsp;&nbsp;" + "<br><br>In case of any query, kindly contact on " + hr.getEmpPhone()
						+ "<br>" + "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone()
						+ "<br>" + hr.getEmail();
				sendEmail(emailAddress, htmlContent, "ESSPL- Invitation for Interview Rescheduled on "
						+ si.getRoundDateSort() + " - " + d.getProfile());
				emailcan.setReceiver(si.getCandidateFullName());
				emailcan.setReceiverEmailId(emailAddress);
				emailcan.setEmailSubject("ESSPL- Invitation for Interview Rescheduled on " + si.getRoundDateSort()
						+ " - " + d.getProfile());
				emailcan.setEmailText(htmlContent);
				emailcan.setScheduled_id(si.getScheduledInterviewId());
				emailcan.setEmailDateTime(currentDate);
				emailcan.setEmailStatus("Email Send");
				emailStoreRepository.save(emailcan);

			} else {
				String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
						+ "<br>As discussed on call, we have rescheduled your face to face technical interview on <span style='color:blue;'>"
						+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
						+ "</span><br>"
						+ "<br><b>Venue Address:</b> Enhanced Software Solutions Pvt. Ltd. 909, 9th Floor, Lodha Supremus, Road No 22, MIDC, Wagle Estate, Thane(W) 400604 India."
						+ "<br><br>In case of any query, kindly contact on " + hr.getEmpPhone() + "<br>"
						+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
						+ hr.getEmail();
				sendEmail(emailAddress, htmlContent, "ESSPL- Invitation for Interview Rescheduled on "
						+ si.getRoundDateSort() + " - " + d.getProfile());
				emailcan.setReceiver(si.getCandidateFullName());
				emailcan.setReceiverEmailId(emailAddress);
				emailcan.setEmailSubject("ESSPL- Invitation for Interview Rescheduled on " + si.getRoundDateSort()
						+ " - " + d.getProfile());
				emailcan.setEmailText(htmlContent);
				emailcan.setScheduled_id(si.getScheduledInterviewId());
				emailcan.setEmailDateTime(currentDate);
				emailcan.setEmailStatus("Email Send");
				emailStoreRepository.save(emailcan);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Reschedule Interview invitation to Candidate for HR round
	public void reScheduledInteviewHREmailToCandidate(ScheduledInterview si, InterviewScheduleIdDto d,
			InterviewerHrDto hr, String emailAddress) {
		try {

			EmailStore emailCanHr = new EmailStore();
			if (si.getModeNo().equals("Video")) {
				String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
						+ "<br>As per our telephonic conversation, we have rescheduled your interview on <span style='color:blue;'>"
						+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
						+ "</span>&nbsp;&nbsp;Please find the link for interview below:"
						+ "<br><br> Communication link:&nbsp;&nbsp;" + si.getVideoLinkDetails() + "<br><br>"
						+ "<br><br>In case of any query, kindly contact on " + hr.getEmpPhone() + "<br>"
						+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
						+ hr.getEmail();
				sendEmail(emailAddress, htmlContent, "ESSPL- Invitation for Interview Rescheduled on "
						+ si.getRoundDateSort() + " - " + d.getProfile());
				emailCanHr.setReceiver(si.getCandidateFullName());
				emailCanHr.setReceiverEmailId(emailAddress);
				emailCanHr.setEmailSubject("ESSPL- Invitation for Interview Rescheduled on" + si.getRoundDateSort()
						+ " - " + d.getProfile());
				emailCanHr.setEmailText(htmlContent);
				emailCanHr.setScheduled_id(si.getScheduledInterviewId());
				emailCanHr.setEmailDateTime(currentDate);
				emailCanHr.setEmailStatus("Email Send");
				emailStoreRepository.save(emailCanHr);

			} else if (si.getModeNo().equals("Telephonic")) {
				String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
						+ "<br>As discussed on call, we have rescheduled your telephonic interview on <span style='color:blue;'>"
						+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
						+ "</span>&nbsp;&nbsp;" + "<br><br>In case of any query, kindly contact on " + hr.getEmpPhone()
						+ "<br>" + "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone()
						+ "<br>" + hr.getEmail();
				sendEmail(emailAddress, htmlContent, "ESSPL- Invitation for Interview Rescheduled on "
						+ si.getRoundDateSort() + " - " + d.getProfile());
				emailCanHr.setReceiver(si.getCandidateFullName());
				emailCanHr.setReceiverEmailId(emailAddress);
				emailCanHr.setEmailSubject("ESSPL- Invitation for Interview Rescheduled on" + si.getRoundDateSort()
						+ " - " + d.getProfile());
				emailCanHr.setEmailText(htmlContent);
				emailCanHr.setScheduled_id(si.getScheduledInterviewId());
				emailCanHr.setEmailDateTime(currentDate);
				emailCanHr.setEmailStatus("Email Send");
				emailStoreRepository.save(emailCanHr);

			} else {
				String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
						+ "<br>As discussed on call, we have rescheduled your face to face interview on <span style='color:blue;'>"
						+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
						+ "</span><br>"
						+ "<br><b>Venue Address:</b> Enhanced Software Solutions Pvt. Ltd. 909, 9th Floor, Lodha Supremus, Road No 22, MIDC, Wagle Estate, Thane(W) 400604 India."
						+ "<br><br>In case of any query, kindly contact on " + hr.getEmpPhone() + "<br>"
						+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
						+ hr.getEmail();
				sendEmail(emailAddress, htmlContent, "ESSPL- Invitation for Interview Rescheduled on "
						+ si.getRoundDateSort() + " - " + d.getProfile());
				emailCanHr.setReceiver(si.getCandidateFullName());
				emailCanHr.setReceiverEmailId(emailAddress);
				emailCanHr.setEmailSubject("ESSPL- Invitation for Interview Rescheduled on" + si.getRoundDateSort()
						+ " - " + d.getProfile());
				emailCanHr.setEmailText(htmlContent);
				emailCanHr.setScheduled_id(si.getScheduledInterviewId());
				emailCanHr.setEmailDateTime(currentDate);
				emailCanHr.setEmailStatus("Email Send");
				emailStoreRepository.save(emailCanHr);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// final round rescheduled interview invitation to interviewer
	public void rescheduledInterviewEmailToInterviewerFinal(ScheduledInterview si, InterviewScheduleIdDto d,
			InterviewerHrDto hr, String emailAddress) {
		try {
			EmailStore emailres = new EmailStore();
			String groupDescription = d.getGroupDescription();
			// For Interviewer Level 1
			if (groupDescription != null && groupDescription.trim().equals("Interviewer Level 1")) {

				if (si.getModeNo().equals("Video")) {
					String htmlContent = "Hello Sir/Ma'am, <br>"
							+ "<br>PFA resume and details for interview scheduled on <span style='color:blue;'>"
							+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
							+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
							+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
							+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
							+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Candidate Contact No:&nbsp;"
							+ d.getContactNumber() + "<br>Communication Rating(1/10):&nbsp;"
							+ si.getCommunicationRating() + "<br>Ready To Relocate:&nbsp;" + d.getReadyToRelocate()
							+ "<br>Profile Relevance:&nbsp;" + si.getProfileRelevance() + "%"
							+ "<br>Meeting Link:&nbsp;" + si.getVideoLinkDetails() + "<br>HR Interview Feedback:&nbsp;"
							+ d.getConstructiveFeedback() + "<br>Interviewer Name:&nbsp;" + d.getFullName()
							+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
							+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
							+ hr.getEmail();
					sendEmailAttachment(emailAddress, htmlContent,
							"Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
							si.getCandidateResume(), si.getIcsFile());
					emailres.setReceiver(d.getFullName());
					emailres.setReceiverEmailId(emailAddress);
					emailres.setEmailSubject(
							"Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
					emailres.setEmailText(htmlContent);
					emailres.setScheduled_id(si.getScheduledInterviewId());
					emailres.setEmailDateTime(currentDate);
					emailres.setEmailattachment(si.getCandidateResume());
					emailres.setEmailStatus("Email Send");
					emailStoreRepository.save(emailres);
				} else {
					String htmlContent = "Hello Sir/Ma'am, <br>"
							+ "<br>PFA resume and details for interview scheduled on <span style='color:blue;'>"
							+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
							+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
							+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
							+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
							+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Candidate Contact No:&nbsp;"
							+ d.getContactNumber() + "<br>Communication Rating(1/10):&nbsp;"
							+ si.getCommunicationRating() + "<br>Ready To Relocate:&nbsp;" + d.getReadyToRelocate()
							+ "<br>Profile Relevance:&nbsp;" + si.getProfileRelevance() + "%"
							+ "<br>HR Interview Feedback:&nbsp;" + d.getConstructiveFeedback()
							+ "<br>Interviewer Name:&nbsp;" + d.getFullName()
							+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
							+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
							+ hr.getEmail();
					sendEmailAttachment(emailAddress, htmlContent,
							"Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
							si.getCandidateResume(), si.getIcsFile());
					emailres.setReceiver(d.getFullName());
					emailres.setReceiverEmailId(emailAddress);
					emailres.setEmailSubject(
							"Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
					emailres.setEmailText(htmlContent);
					emailres.setScheduled_id(si.getScheduledInterviewId());
					emailres.setEmailDateTime(currentDate);
					emailres.setEmailattachment(si.getCandidateResume());
					emailres.setEmailStatus("Email Send");
					emailStoreRepository.save(emailres);

				}
			} else {

				if (si.getModeNo().equals("Video")) {
					String htmlContent = "Hello Sir/Ma'am, <br>"
							+ "<br>PFA resume and details for interview scheduled on <span style='color:blue;'>"
							+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
							+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
							+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
							+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
							+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Cost to Comapany(CTC):&nbsp;"
							+ d.getCurrentSalary() + "<br>Expected Cost to Company(ECTC):&nbsp;" + d.getExpectedSalary()
							+ "<br>Candidate Contact No:&nbsp;" + d.getContactNumber()
							+ "<br>Communication Rating(1/10):&nbsp;" + si.getCommunicationRating()
							+ "<br>Ready To Relocate:&nbsp;" + d.getReadyToRelocate() + "<br>Profile Relevance:&nbsp;"
							+ si.getProfileRelevance() + "%" + "<br>Meeting Link:&nbsp;" + si.getVideoLinkDetails()
							+ "<br>HR Interview Feedback:&nbsp;" + d.getConstructiveFeedback()
							+ "<br>Interviewer Name:&nbsp;" + d.getFullName()
							+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
							+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
							+ hr.getEmail();
					sendEmailAttachment(emailAddress, htmlContent,
							"Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
							si.getCandidateResume(), si.getIcsFile());
					emailres.setReceiver(d.getFullName());
					emailres.setReceiverEmailId(emailAddress);
					emailres.setEmailSubject("Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
					emailres.setEmailText(htmlContent);
					emailres.setScheduled_id(si.getScheduledInterviewId());
					emailres.setEmailDateTime(currentDate);
					emailres.setEmailattachment(si.getCandidateResume());
					emailres.setEmailStatus("Email Send");
					emailStoreRepository.save(emailres);
				} else {
					String htmlContent = "Hello Sir/Ma'am, <br>"
							+ "<br>PFA resume and details for interview scheduled on <span style='color:blue;'>"
							+ si.getRoundDateSort() + "</span> at <span style='color:blue;'>" + si.getRoundTime()
							+ "</span>." + "<br><br>Candidate Name:&nbsp;" + si.getCandidateFullName()
							+ "<br>Profile Name:&nbsp;" + d.getProfile() + "<br>Relevant Experience:&nbsp;"
							+ d.getRelevantExperience() + "&nbsp;yrs" + "<br>Total Experience:&nbsp;"
							+ d.getTotalExperience() + "&nbsp;yrs" + "<br>Cost to Comapany(CTC):&nbsp;"
							+ d.getCurrentSalary() + "<br>Expected Cost to Company(ECTC):&nbsp;" + d.getExpectedSalary()
							+ "<br>Candidate Contact No:&nbsp;" + d.getContactNumber()
							+ "<br>Communication Rating(1/10):&nbsp;" + si.getCommunicationRating()
							+ "<br>Ready To Relocate:&nbsp;" + d.getReadyToRelocate() + "<br>Profile Relevance:&nbsp;"
							+ si.getProfileRelevance() + "%" + "<br>HR Interview Feedback:&nbsp;"
							+ d.getConstructiveFeedback() + "<br>Interviewer Name:&nbsp;" + d.getFullName()
							+ "<br><br>To view the technical feedback, Kindly login to the HR Portal."
							+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
							+ hr.getEmail();
					sendEmailAttachment(emailAddress, htmlContent,
							"Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile() + "",
							si.getCandidateResume(), si.getIcsFile());
					emailres.setReceiver(d.getFullName());
					emailres.setReceiverEmailId(emailAddress);
					emailres.setEmailSubject(
							"Interview Rescheduled on " + si.getRoundDateSort() + " for " + d.getProfile());
					emailres.setEmailText(htmlContent);
					emailres.setScheduled_id(si.getScheduledInterviewId());
					emailres.setEmailDateTime(currentDate);
					emailres.setEmailattachment(si.getCandidateResume());
					emailres.setEmailStatus("Email Send");
					emailStoreRepository.save(emailres);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// HR sends a rejection email to candidate Oct 9 2023.
	public void rejectionEmailToCandidate(ScheduledInterview si, InterviewScheduleIdDto d, InterviewerHrDto hr,
			String emailAddress) {
		try {
			EmailStore er = new EmailStore();
			String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
					+ "<br>Thank you for applying to our  <span style='color:blue;'>" + d.getProfile()
					+ "</span> role and for speaking to our team about your experience. We appreciate your interest in Enhanced Software Solutions Pvt. Ltd.<br>"
					+ "<br>We were fortunate to have a strong group of applicants, and we regret to inform you that you are not shortlisted for this profile.<br>"
					+ "<br>We will keep your resume on file and reach out if we think you’d be a good fit for other roles as they become available."
					+ "<br><br>We hope you will continue to stay connected with us and keep an eye on our career page https://ess.net.in/careers.html for future opportunities that may be a better fit."
					+ "<br><br>We truly appreciate your time and consideration of Enhanced Software Solutions Pvt. Ltd."
					+ "<br><br>All the Best,<br>" + "<br><br>In case of any query, kindly contact on "
					+ hr.getEmpPhone() + "<br>" + "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>"
					+ hr.getEmpPhone() + "<br>" + hr.getEmail();
			sendEmail(emailAddress, htmlContent, "ESSPL Interview Feedback Result");
			er.setReceiver(si.getCandidateFullName());
			er.setReceiverEmailId(emailAddress);
			er.setEmailSubject("ESSPL Interview Feedback Result");
			er.setEmailText(htmlContent);
			er.setScheduled_id(si.getScheduledInterviewId());
			er.setEmailDateTime(currentDate);
			er.setEmailStatus("Email Send");
			emailStoreRepository.save(er);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Interviewer send an email to hr for candidate rejection October 9 2023
	public void rejectedCandidateEmailToHR(ScheduledInterview si, InterviewScheduleIdDto d, InterviewerHrDto hr,
			String emailAddress) {
		try {
			EmailStore emailreject = new EmailStore();
			String htmlContent = "Hello HR,<br>" + "<br><br><b>Candidate Unique Number :&nbsp;</b>"
					+ si.getCandidateUniqueNumber() + "<br><b>Candidate :&nbsp;</b>" + si.getCandidateFullName()
					+ "<br><b>Feedback :&nbsp;</b>" + si.getConstructiveFeedback() + "<br>Status : Rejected"
					+ "<br><br>Thanks & Regards,<br>" + d.getFullName() + "<br>" + d.getEmpPhone() + "<br>"
					+ d.getEmail();
			sendEmail(emailAddress, htmlContent, "Candidate " + si.getCandidateFullName() + " - " + d.getProfile()
					+ " Feedback Result for " + d.getRoundName());
			emailreject.setReceiver(hr.getFullName());
			emailreject.setReceiverEmailId(emailAddress);
			emailreject.setEmailSubject("Candidate " + si.getCandidateFullName() + " - " + d.getProfile()
					+ " Feedback Result for " + d.getRoundName());
			emailreject.setEmailText(htmlContent);
			emailreject.setScheduled_id(si.getScheduledInterviewId());
			emailreject.setEmailDateTime(currentDate);
			emailreject.setEmailStatus("Email Send");
			emailStoreRepository.save(emailreject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Interviewer send email to hr for selected candidate changes on October 9 2023
	public void selectedCandidateEmailToHR(ScheduledInterview si, InterviewScheduleIdDto d, InterviewerHrDto hr,
			String emailAddress) {
		try {
			EmailStore emailSelect = new EmailStore();
			String htmlContent = "Hello HR,<br>" + "<br><br><b>Candidate Unique Number :&nbsp;</b>"
					+ si.getCandidateUniqueNumber() + "<br><b>Candidate :&nbsp;</b>" + si.getCandidateFullName()
					+ "<br>This candidate is Selected in final round."
					+ "<br>Status : Asked Salary Details / Shared Offer Letter / Accepted Offer Letter."
					+ "<br><br>Thanks & Regards,<br>" + d.getFullName() + "<br>" + d.getEmpPhone() + "<br>"
					+ d.getEmail();
			sendEmail(emailAddress, htmlContent, "Candidate " + si.getCandidateFullName() + " - " + d.getProfile()
					+ " Feedback Result for " + d.getRoundName());
			emailSelect.setReceiver(hr.getFullName());
			emailSelect.setReceiverEmailId(emailAddress);
			emailSelect.setEmailSubject("Candidate " + si.getCandidateFullName() + " - " + d.getProfile()
					+ " Feedback Result for " + d.getRoundName());
			emailSelect.setEmailText(htmlContent);
			emailSelect.setScheduled_id(si.getScheduledInterviewId());
			emailSelect.setEmailDateTime(currentDate);
			emailSelect.setEmailStatus("Email Send");
			emailStoreRepository.save(emailSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Interviewer send email to HR for shortlisted candidate email -Octber 9 2023
	public void shortlistedCandidateEmailToHR(ScheduledInterview si, InterviewScheduleIdDto d, InterviewerHrDto hr,
			String emailAddress) {
		try {
			EmailStore emailshortlisted = new EmailStore();
			String htmlContent = "Hello HR,<br>" + "<br><br><b>Candidate Unique Number :&nbsp;</b>"
					+ si.getCandidateUniqueNumber() + "<br><b>Candidate :&nbsp;</b>" + si.getCandidateFullName()
					+ "<br><b>Feedback :&nbsp;</b>" + si.getConstructiveFeedback()
					+ "<br>This candidate is shortlisted for " + d.getRoundName() + "&nbsp;interview."
					+ "<br><br>Thanks & Regards,<br>" + d.getFullName() + "<br>" + d.getEmpPhone() + "<br>"
					+ d.getEmail();
			sendEmail(emailAddress, htmlContent, "Candidate " + si.getCandidateFullName() + " - " + d.getProfile()
					+ " Feedback Result for " + d.getRoundName());
			emailshortlisted.setReceiver(hr.getFullName());
			emailshortlisted.setReceiverEmailId(emailAddress);
			emailshortlisted.setEmailSubject("Candidate " + si.getCandidateFullName() + " - " + d.getProfile()
					+ " Feedback Result for " + d.getRoundName());
			emailshortlisted.setEmailText(htmlContent);
			emailshortlisted.setScheduled_id(si.getScheduledInterviewId());
			emailshortlisted.setEmailDateTime(currentDate);
			emailshortlisted.setEmailStatus("Email Send");
			emailStoreRepository.save(emailshortlisted);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// On Hold candidate email to HR
	public void onHoldCandidateEmailToHR(ScheduledInterview si, InterviewerHrDto hr, InterviewScheduleIdDto d,
			String emailAddress) {
		try {
			EmailStore emailonhold = new EmailStore();
			String htmlContent = "Hello HR,<br>" + "<br><br><b>Candidate Unique Number :&nbsp;</b>"
					+ si.getCandidateUniqueNumber() + "<br><b>Candidate :&nbsp;</b>" + si.getCandidateFullName()
					+ "<br><b>Feedback :&nbsp;</b>" + si.getConstructiveFeedback()
					+ "<br>This candidate is being on hold." + "<br><br>Thanks & Regards,<br>" + d.getFullName()
					+ "<br>" + d.getEmpPhone() + "<br>" + d.getEmail();
			// To be changed
			sendEmail(emailAddress, htmlContent, "Candidate " + si.getCandidateFullName() + " - " + d.getProfile()
					+ " Feedback Result for " + d.getRoundName());
			emailonhold.setReceiver(hr.getFullName());
			emailonhold.setReceiverEmailId(emailAddress);
			emailonhold.setEmailSubject("Candidate " + si.getCandidateFullName() + " - " + d.getProfile()
					+ " Feedback Result for " + d.getRoundName());
			emailonhold.setEmailText(htmlContent);
			emailonhold.setScheduled_id(si.getScheduledInterviewId());
			emailonhold.setEmailDateTime(currentDate);
			emailonhold.setEmailStatus("Email Send");
			emailStoreRepository.save(emailonhold);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// After Selection of Candidate Email for Salary Details:
	public void emailForSalaryDetails(ScheduledInterview si, InterviewerHrDto hr, String emailAddress) {
		try {
			EmailStore emailsalarydetails = new EmailStore();
			String htmlContent = "Hello " + si.getCandidateFullName() + ",<br>"
					+ "<br>Congratulation for getting shortlisted in the interview conducted by ESS, Kindly provide below salary details as we need to discuss upon your offer:<br>"
					+ "<br>1. Last 3 months salary slips<br>"
					+ "<br>2. Hike / Offer Letter - which shows your current CTC breakup.<br>"
					+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
					+ hr.getEmail();
			sendEmail(emailAddress, htmlContent, "ESSPL - Salary Details");
			emailsalarydetails.setReceiver(si.getCandidateFullName());
			emailsalarydetails.setReceiverEmailId(emailAddress);
			emailsalarydetails.setEmailSubject("ESSPL - Salary Details");
			emailsalarydetails.setEmailText(htmlContent);
			emailsalarydetails.setScheduled_id(si.getScheduledInterviewId());
			emailsalarydetails.setEmailDateTime(currentDate);
			emailsalarydetails.setEmailStatus("Email Send");
			emailStoreRepository.save(emailsalarydetails);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// After Selection of Candidate Email for Breakup of Offer :
	public void emailForBreakupOfOffer(ScheduledInterview si, InterviewerHrDto hr, String emailAddress) {
		try {

			String htmlContent = "Dear" + si.getCandidateFullName() + ",<br>"
					+ "<br><br><b>Heartiest Congratulations on behalf of Enhanced Software Solutions Pvt. Ltd. we are happy to inform you that you have been selected "
					+ "and we are excited to have you join the company. We believe your skills and experience will make an excellent addition to the [Department] team.<br>"
					+ "<br><br><b>We are offering you early joining bonus of Rs. 15,000/-, which is applicable only if you will join within 15 days from the offer.</b><br>"
					+ "<br><br>We pledge our further support and look forward for productive cooperation."
					+ "Please send your acceptance of breakup of offer and please share your resignation acceptance letter.<br>"
					+ "<br><br>We look forward to welcoming you to the [Company Name] team. If you have any questions regarding the attached offer, please contact me directly at [Phone Number].<br>"
					+ "<br><br><b>Kindly let us know your earlier joining date.</b><br>"
					+ "<br><br>Thanks & Regards,<br>" + hr.getFullName() + "<br>" + hr.getEmpPhone() + "<br>"
					+ hr.getEmail();
			sendEmail(emailAddress, htmlContent, " Email for Breakup of Offer");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Email – Welcome to ESS Family!
	public void welcomeEmailToCandidate(ScheduledInterview si, String emailAddress) {
		try {

			String htmlContent = "Dear" + si.getCandidateFullName() + ",<br>"
					+ "<br><br>Congratulations and welcome to the team! We are excited to have you at <b>Enhanced Software Solutions Pvt. Ltd.</b> "
					+ "We know you’re going to be a valuable asset to our company and are looking forward to the positive impact you’re going to have here.<br>"
					+ "<br><br>I have looped in your Manager – (Manager's Name) and HR Head – (HR Head's Name).<br>"
					+ "<br><br>I thank you for all your patience. I hope you will be always open to communicate in case of any query and opinion in future. We are happy to have you on-boarded.<br>"
					+ "<br><br>@Manager's Name - Please find below contact details of Employee's Name:<br>"
					+ "<br><br>Email – employee's official email id." + "<br>Contact No. -  employee's No.<br>"
					+ "<br><br>Thanks & Regards,<br>HR Executive<br>";
			sendEmail(emailAddress, htmlContent, "Welcome to ESS Family!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// send email
	public void sendEmail(String to, String htmlContent, String subject) throws Exception {

		try {

		} catch (Exception ex) {

		}

		Properties pro = new Properties();
		pro.put("mail.smtp.host", emailConfig.getHost());
		pro.put("mail.smtp.port", emailConfig.getPort());
		pro.put("mail.smtp.tls.enable", "true");
		pro.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(pro, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailConfig.getUsername(),
						passwordEncryption.decrypt(emailConfig.getPassword()));
			}
		});
		try {
			MimeMessage m = new MimeMessage(session);
			m.setFrom(new InternetAddress(emailConfig.getUsername()));
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setContent(htmlContent, "text/html;charset=utf-8");
			Transport.send(m);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}

	public void sendEmailAttachment(String to, String htmlContent, String subject, String attachment, String icsFile)
			throws Exception {

		Properties pro = new Properties();
		pro.put("mail.smtp.host", emailConfig.getHost());
		pro.put("mail.smtp.port", emailConfig.getPort());
		pro.put("mail.smtp.tls.enable", "true");
		pro.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(pro, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailConfig.getUsername(),
						passwordEncryption.decrypt(emailConfig.getPassword()));
			}
		});

		try {
			MimeMessage m = new MimeMessage(session);
			m.setFrom(new InternetAddress(emailConfig.getUsername()));
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			MimeMultipart mimeMultipart = new MimeMultipart();
			MimeBodyPart textMime = new MimeBodyPart();
			MimeBodyPart fileMine = new MimeBodyPart();
			MimeBodyPart fileMine1 = new MimeBodyPart();

			if (attachment != null || icsFile != null) {
				textMime.setContent(htmlContent, "text/html");
				mimeMultipart.addBodyPart(textMime);

				if (attachment != null) {
					String attachmentPath = attachmentDocumentDrive + File.separator + attachment;
					File attachmentFile = new File(attachmentPath);
					fileMine.attachFile(attachmentFile);
					mimeMultipart.addBodyPart(fileMine);
				}

				if (icsFile != null) {
					String icsFilePath = attachmentIcsDrive + File.separator + icsFile;
					File icsFileObj = new File(icsFilePath);
					fileMine1.attachFile(icsFileObj);
					mimeMultipart.addBodyPart(fileMine1);
				}
			}

			if (icsFile != null || attachment != null) {
				m.setContent(mimeMultipart);
			}
			Transport.send(m);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// otp
	public void otpMail(String email, String otp) {
		String htmlContent = "To Reset password on Candidate Interview Scheduling Portal-<br><br><b>OTP : " + otp
				+ "</b><br><br>Thanks & Regards, <br> Human Resources";
		String subject = "OTP to reset password";
		try {
			this.sendEmail(email, htmlContent, subject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
