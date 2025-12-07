# **Candidate Interviewing Application – Detailed Documentation**

A complete HR & Interview management system allowing HR, Admin, and Interviewers to manage candidates, schedule interviews, provide feedback, and track hiring status.



## Table of Contents

1. [Overview](#overview)
2. [Technology Stack](#technology-stack)
3. [System Features](#system-features)
4. [User Roles & Permissions](#user-roles--permissions)
5. [System Architecture](#system-architecture)
6. [Database Schema (ER Diagram Description)](#database-schema-er-diagram-description)
7. [API Design (Spring Boot)](#api-design-spring-boot)
8. [Email Notification Flow](#email-notification-flow)
9. [Frontend Architecture (Angular)](#frontend-architecture-angular)
10. [Interview Workflow](#interview-workflow)
11. [Installation & Setup](#installation--setup)
12. [Future Enhancements](#future-enhancements)

---

# **1. Overview**

The **Candidate Interviewing Application** allows HR teams to manage recruitment end-to-end, from creating candidate profiles to scheduling interviews, collecting feedback, and confirming joining details.

This document describes system functionalities, architecture, API, workflow, and setup.

---

# **2. Technology Stack**

## **Backend**

| Component          | Version | Description                |
| ------------------ | ------- | -------------------------- |
| JDK                | 1.8     | Java Development Kit       |
| Spring Boot        | 2.x     | Backend framework          |
| Spring Data JPA    |         | ORM + database interaction |
| Spring Security    |         | Login & role-based access  |
| Spring Mail        |         | Email sending              |
| MySQL / PostgreSQL | Latest  | Database                   |

---

## **Frontend**

| Technology           | Description          |
| -------------------- | -------------------- |
| Angular 12+          | Frontend framework   |
| TypeScript           | Programming language |
| Bootstrap / Material | UI styling           |

---

# **3. System Features**

## **3.1 Candidate Management**

* HR/Admin can create new candidate profiles.
* View and update candidate information.
* Assign multiple interview rounds.

---

## **3.2 Interview Scheduling**

* HR schedules interviews and assigns interviewers.
* Supports multiple rounds (Technical, HR, Managerial, etc.).
* Rescheduling allowed by HR.
* Email notification to candidate + interviewer.

---

## **3.3 Interview Feedback**

* Each interviewer sees:

  * Candidate details
  * Previous round feedback (if allowed)
* Interviewer submits feedback:

  * Shortlisted
  * Selected
  * On Hold
  * Rejected
* HR receives feedback via email.

---

## **3.4 Post-Interview HR Actions**

* HR updates final decision.
* If candidate selected:

  * Add joining date.
* If candidate declines:

  * Enter reason for not joining.

---

## **3.5 Configuration Management**

### Admin can:

* Create/modify Departments
* Create/modify Job Profiles
* Manage Department–Profile mapping
* Manage Users (HR, Interviewers)
* Assign Roles

### HR/Admin can:

* Define interview rounds for each profile.

---

# **4. User Roles & Permissions**

| Role                           | Permissions                                                                                 |
| ------------------------------ | ------------------------------------------------------------------------------------------- |
| **Admin**                      | Manage departments, profiles, roles, employees, interview rounds                            |
| **HR**                         | Create candidates, schedule/reschedule interviews, update joining details, receive feedback |
| **Interviewer (Technical/HR)** | View assigned interviews, view candidate info, submit feedback                              |

---

# **5. System Architecture**

```
Frontend (Angular)
       |
       | REST API (JSON)
       v
Backend (Spring Boot)
       |
       | JPA/Hibernate
       v
Database (MySQL/PostgreSQL)
       |
       | SMTP
       v
Email Notifications
```

---

# **6. Database Schema (ER Diagram Description)**

### **Tables**

1. **users**

   * id, name, email, password, role (HR/Interviewer/Admin)

2. **departments**

   * id, name

3. **profiles**

   * id, profile_name

4. **department_profile**

   * id, department_id, profile_id

5. **candidates**

   * id, name, email, phone, experience, profile_id, status

6. **interview_rounds**

   * id, profile_id, round_name, sequence_order

7. **interviews**

   * id, candidate_id, round_id, interviewer_id, scheduled_time, status

8. **feedback**

   * id, interview_id, interviewer_id, rating, comments, action (Selected/Rejected/etc.)

9. **joining_details**

   * id, candidate_id, joining_date, not_joining_reason

---

# **7. API Design (Spring Boot)**

### **Candidate APIs**

| Method | Endpoint               | Description        |
| ------ | ---------------------- | ------------------ |
| POST   | `/api/candidates`      | Create candidate   |
| GET    | `/api/candidates/{id}` | Get candidate info |
| PUT    | `/api/candidates/{id}` | Update candidate   |
| GET    | `/api/candidates`      | List candidates    |

---

### **Interview APIs**

| Method | Endpoint                           | Description             |
| ------ | ---------------------------------- | ----------------------- |
| POST   | `/api/interviews/schedule`         | Schedule interview      |
| PUT    | `/api/interviews/reschedule/{id}`  | Reschedule              |
| GET    | `/api/interviews/interviewer/{id}` | Get assigned interviews |

---

### **Feedback APIs**

| Method | Endpoint                       | Description     |
| ------ | ------------------------------ | --------------- |
| POST   | `/api/feedback`                | Submit feedback |
| GET    | `/api/feedback/candidate/{id}` | View feedback   |

---

### **Config APIs (Admin/HR)**

* `/api/departments`
* `/api/profiles`
* `/api/rounds`
* `/api/users`

---

# **8. Email Notification Flow**

### **Triggered When:**

1. **Interview Scheduled**

   * Email → Candidate
   * Email → Interviewer

2. **Interview Rescheduled**

   * Email → Candidate
   * Email → Interviewer

3. **Feedback Submitted**

   * Email → HR

### Email Template Example (Spring Mail)

```
Subject: Interview Scheduled for {{candidateName}}

Dear {{candidateName}},
Your interview for the position of {{profile}} has been scheduled on {{date}}.
```

---

# **9. Frontend Architecture (Angular)**

### **Modules**

* `auth` → login & role handling
* `candidate` → create/view candidates
* `interview` → schedule/reschedule
* `feedback` → interviewer screens
* `admin` → departments, profiles, employee roles

### **Services**

* `candidate.service.ts`
* `interview.service.ts`
* `feedback.service.ts`
* `email.service.ts`

### **UI Layout**

* Dashboard (role-based)
* Candidate List
* Interview Calendar
* Feedback Form
* Admin Settings

---

# **10. Interview Workflow**

1. **HR/Admin** creates candidate.
2. **HR** assigns department & profile.
3. System loads **rounds** for selected profile.
4. HR schedules interview.
5. Email notification sent.
6. **Interviewer** sees interview in dashboard.
7. Interviewer submits feedback.
8. HR reviews feedback.
9. If selected → HR sets joining date.
10. If not joining → HR adds reason.

---

# **11. Installation & Setup**

## **Backend Setup**

```
git clone <repository>
cd backend
mvn clean install
```

### **Run**

```
mvn spring-boot:run
```

### **Environment Variables**

```
DB_URL=jdbc:mysql://localhost:3306/interviewdb
DB_USERNAME=root
DB_PASSWORD=1234

SPRING_MAIL_HOST=smtp.gmail.com
SPRING_MAIL_USERNAME=xxxx@gmail.com
SPRING_MAIL_PASSWORD=appPassword
```

---

## **Frontend Setup**

```
cd frontend
npm install
ng serve --open
```

---

# **12. Future Enhancements**

* JWT-based authentication
* Video interview integration
* Resume parser using AI
* Automated reminder emails
* Mobile App (Flutter/React Native)

---
