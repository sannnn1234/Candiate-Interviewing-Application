/*
 * Author:Sandeep Gupta
 * Date:28 December 2022
 * Version:0.0.1
 */
package com.hr.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hr.model.ScheduledInterview;
import com.hr.model.candidate;

@Component
public class FileUploadHelper {

	@Value("${attachmentImageDrive}")
	String attachmentImageDrive;

	@Value("${attachmentDocumentDrive}")
	String attachmentDocumentDrive;

	@Value("${attachmentIcsDrive}")
	String attachmentIcsDrive;


	@SuppressWarnings("unused")
	public boolean uploadFile(MultipartFile file, candidate c) {
		boolean f = false;
		try {
			String candidateName = c.getCandidateFullName().trim().replace(" ", "_");
			String candidateId = c.getCandidateUniqueNumber();
			String originalfile = file.getOriginalFilename();
			String fileName = candidateName + "_" + candidateId;
			String fullFileName = attachmentImageDrive + File.separator + fileName;
			Path p = new File(fullFileName).toPath();
			Files.copy(file.getInputStream(), p, StandardCopyOption.REPLACE_EXISTING);
			f = true;
//			c.setCandidateImage(attachmentImageDBUrl + File.separator + fileName);
			c.setCandidateImage(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	
	public boolean uploadFile1(MultipartFile resumefile, candidate c) {
		boolean f = false;
		try {
			String candidateName = c.getCandidateFullName().trim().replace(" ", "_");
			String candidateId = c.getCandidateUniqueNumber();
			String originalfile = resumefile.getOriginalFilename();
			String fileName = candidateName + "_Resume_" + candidateId + ".pdf";
			String fullFileName = attachmentDocumentDrive + File.separator + fileName;
			Path p = new File(fullFileName).toPath();
			Files.copy(resumefile.getInputStream(), p, StandardCopyOption.REPLACE_EXISTING);
			f = true;
			c.setCandidateResume(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public boolean uploadIcsFile(MultipartFile file, ScheduledInterview s) {
		boolean f = false;
		try {
			String fileName = file.getOriginalFilename();
			String fullFileName = attachmentIcsDrive + File.separator + fileName;
			Path p = new File(fullFileName).toPath();
			Files.copy(file.getInputStream(), p, StandardCopyOption.REPLACE_EXISTING);
			f = true;
			s.setIcsFile(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
