# Candidate Interviewing Application 

A complete HR & Interview management system allowing HR, Admin, and Interviewers to manage candidates, schedule interviews, provide feedback, and track hiring status.

---

## Table of Contents

1. [Overview](#overview)
2. [Technology Stack](#technology-stack)
3. [System Features](#system-features)
4. [User Roles & Permissions](#user-roles--permissions)
5. [System Architecture](#system-architecture)
6. [Database Schema (ER Diagram Description)](#database-schema-er-diagram-description)
7. [API Design (Spring Boot)](#api-design-spring-boot)
8. [Spring Security – JWT Authentication](#spring-security--jwt-authentication)
9. [Email Notification Flow](#email-notification-flow)
10. [Frontend Architecture (Angular)](#frontend-architecture-angular)
11. [Interview Workflow](#interview-workflow)
12. [Installation & Setup](#installation--setup)
13. [Future Enhancements](#future-enhancements)

---

# 1. Overview

The **Candidate Interviewing Application** allows HR teams to manage recruitment end-to-end, from creating candidate profiles to scheduling interviews, collecting feedback, and confirming joining details.

This document describes system functionalities, architecture, API design, workflow, and setup.

---

# 2. Technology Stack

## Backend

| Component             | Version | Description                    |
| --------------------- | ------- | ------------------------------ |
| JDK                   | 1.8     | Java Development Kit           |
| Spring Boot           | 2.x     | Backend framework              |
| Spring Data JPA       | —       | ORM and DB interaction         |
| Spring Security (JWT) | —       | Authentication & Authorization |
| Spring Mail           | —       | Email notifications            |
| MySQL                 | Latest  | Database                       |

## Frontend

| Technology           | Description        |
| -------------------- | ------------------ |
| Angular 12+          | Frontend framework |
| TypeScript           | Application logic  |
| Bootstrap / Material | UI styling         |

---

# 3. System Features

## 3.1 Candidate Management

* Create and update candidate profiles.
* Assign job profiles and departments.
* Manage candidate status.

## 3.2 Interview Scheduling

* HR schedules interviews and assigns interviewers.
* Supports unlimited interview rounds.
* Rescheduling supported.
* Email notifications for schedule/reschedule.

## 3.3 Interview Feedback

* Interviewer can view assigned interviews.
* Submit feedback for each round.
* HR receives notification after submission.

## 3.4 Post-Interview HR Actions

* Final selection decision.
* If selected → set joining date.
* If not joining → capture reason.

## 3.5 Configuration Management

### Admin:

* Manage Departments
* Manage Job Profiles
* Manage User Roles
* Create Interview Rounds per Profile

### HR/Admin:

* Manage department–profile mapping
* Define interview rounds

---

# 4. User Roles & Permissions

| Role            | Permissions                                                                   |
| --------------- | ----------------------------------------------------------------------------- |
| **Admin**       | Manage departments, profiles, roles, users, interview rounds                  |
| **HR**          | Create/update candidates, schedule interviews, view feedback, joining details |
| **Interviewer** | View interviews, submit feedback                                              |

---

# 5. System Architecture

```
Frontend (Angular)
       |
       | REST API (JSON)
       v
Backend (Spring Boot + JWT Security)
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

# 6. Database Schema (ER Diagram Description)

## Tables Overview

### users

* id, name, email, password, role

### departments

* id, name

### profiles

* id, profile_name

### department_profile

* department_id, profile_id

### candidates

* id, name, email, phone, experience, profile_id, status

### interview_rounds

* id, profile_id, round_name, sequence_order

### interviews

* id, candidate_id, round_id, interviewer_id, scheduled_time, status

### feedback

* id, interview_id, interviewer_id, rating, comments, action

### joining_details

* candidate_id, joining_date, not_joining_reason

---

# 7. API Design (Spring Boot)

## Candidate APIs

| Method | Endpoint               | Description        |
| ------ | ---------------------- | ------------------ |
| POST   | `/api/candidates`      | Create candidate   |
| GET    | `/api/candidates/{id}` | Get candidate info |
| PUT    | `/api/candidates/{id}` | Update candidate   |
| GET    | `/api/candidates`      | List candidates    |

## Interview APIs

| Method | Endpoint                           | Description                    |
| ------ | ---------------------------------- | ------------------------------ |
| POST   | `/api/interviews/schedule`         | Schedule interview             |
| PUT    | `/api/interviews/reschedule/{id}`  | Reschedule                     |
| GET    | `/api/interviews/interviewer/{id}` | Get interviews for interviewer |

## Feedback APIs

| Method | Endpoint                       | Description             |
| ------ | ------------------------------ | ----------------------- |
| POST   | `/api/feedback`                | Submit feedback         |
| GET    | `/api/feedback/candidate/{id}` | View candidate feedback |

## Config APIs (Admin/HR)

* `/api/departments`
* `/api/profiles`
* `/api/rounds`
* `/api/users`

---

# 8. Spring Security – JWT Authentication

## Key Features

* JWT-based login and API authentication.
* Role-based access control (Admin, HR, Interviewer).
* Stateless API security using filters.
* Bcrypt password hashing.

## Authentication Flow

```
[User Login] → [Token Generated] → [Client Stores Token] →
[Uses Bearer Token in Each Request] → [JWT Filter Validates] → [Access Granted]
```

## Role-Based Access Example

* `/api/admin/**` → ADMIN only
* `/api/hr/**` → HR only
* `/api/interviewer/**` → INTERVIEWER only

---

# 9. Email Notification Flow

### Triggered when:

1. Interview Scheduled
2. Interview Rescheduled
3. Feedback Submitted

Example Template:

```
Subject: Interview Scheduled for {{candidateName}}
Dear {{candidateName}}, your interview for {{profile}} is on {{date}}.
```

---

# 10. Frontend Architecture (Angular)

## Modules

* Auth Module
* Candidate Module
* Interview Module
* Feedback Module
* Admin Module

## Services

* candidate.service.ts
* interview.service.ts
* feedback.service.ts
* email.service.ts

## UI Screens

* Dashboard
* Candidate List
* Interview Calendar
* Interview Feedback Form
* Admin Config Management

---

# 11. Interview Workflow

1. HR creates candidate
2. HR assigns department & profile
3. System loads rounds
4. HR schedules interview
5. Email sent
6. Interviewer submits feedback
7. HR reviews
8. If selected → joining date added
9. If not joining → reason captured

---

# 12. Installation & Setup

## Backend Setup

```
git clone <repo>
cd backend
mvn clean install
mvn spring-boot:run
```

### Environment Variables

```
DB_URL=jdbc:mysql://localhost:3306/interviewdb
DB_USERNAME=root
DB_PASSWORD=1234

SPRING_MAIL_HOST=smtp.gmail.com
SPRING_MAIL_USERNAME=xxx@gmail.com
SPRING_MAIL_PASSWORD=appPassword
```

## Frontend Setup

```
cd frontend
npm install
ng serve --open
```

---

# 13. Future Enhancements

* Video interview integrations
* AI-based resume parser
* Automated reminders
* Mobile app version
* Analytics & dashboard reports
