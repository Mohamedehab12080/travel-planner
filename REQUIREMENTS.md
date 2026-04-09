# 📋 Travel Destination Planner - Requirements Analysis

## Document Information

| Property | Value |
|----------|-------|
| **Project Name** | Travel Destination Planner |
| **Version** | 1.0 |
| **Date** | April 2026 |
| **Author** | Mohamed Ehab |
| **Status** | Completed |

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Functional Requirements](#functional-requirements)
3. [Non-Functional Requirements](#non-functional-requirements)
4. [User Roles & Permissions](#user-roles--permissions)
5. [Technical Requirements](#technical-requirements)
6. [API Requirements](#api-requirements)
7. [Database Requirements](#database-requirements)
8. [Security Requirements](#security-requirements)
9. [External Dependencies](#external-dependencies)

---

## Project Overview

### Objective
Develop a full-stack web application for managing travel destinations, allowing administrators to curate destinations and users to explore and plan their travels.

### Scope
- Admin dashboard for destination management
- User portal for browsing and wishlist management
- Authentication and authorization system
- Integration with external REST Countries API

### Target Users
- Super Administrators (system owners)
- Administrators (content managers)
- Regular Users (travel enthusiasts)

---

## Functional Requirements

### FR-01: User Registration & Authentication

| ID | Requirement | Priority |
|----|-------------|----------|
| FR-01.1 | Users must be able to register with email, full name, and password | High |
| FR-01.2 | Password must be at least 8 characters | High |
| FR-01.3 | Email must be unique and properly formatted | High |
| FR-01.4 | Account activation email must be sent after registration | High |
| FR-01.5 | Activation link must expire after 24 hours | High |
| FR-01.6 | Users must be able to resend activation email | Medium |
| FR-01.7 | Users must be able to log in with email and password | High |
| FR-01.8 | Users must be able to reset forgotten password via email | High |
| FR-01.9 | Password reset link must expire after 24 hours | High |
| FR-01.10 | Users must be able to log out | High |

### FR-02: Role-Based Access Control

| ID | Requirement | Priority |
|----|-------------|----------|
| FR-02.1 | System must support three roles: USER, ADMIN, SUPER_ADMIN | High |
| FR-02.2 | Users can only access user-specific endpoints | High |
| FR-02.3 | Admins can access admin and user endpoints | High |
| FR-02.4 | Super Admins have full system access | High |
| FR-02.5 | JWT tokens must be used for authentication | High |

### FR-03: Super Admin Features

| ID | Requirement | Priority |
|----|-------------|----------|
| FR-03.1 | Super Admin must be able to view all admin users | High |
| FR-03.2 | Super Admin must be able to create new admin users | High |
| FR-03.3 | Super Admin must be able to update admin mobile number | High |
| FR-03.4 | Super Admin must be able to activate/deactivate admin accounts | High |
| FR-03.5 | Super Admin cannot modify or deactivate own account | High |
| FR-03.6 | Super Admin must be able to search admins by name, email, or phone | Medium |
| FR-03.7 | Super Admin must be able to filter admins by status (active/inactive) | Medium |

### FR-04: Admin Dashboard

| ID | Requirement | Priority |
|----|-------------|----------|
| FR-04.1 | Admin must see statistics (total, pending, approved, rejected destinations) | High |
| FR-04.2 | Admin must see recent destinations (last 10 entries) | High |
| FR-04.3 | Admin must be able to fetch countries from REST Countries API | High |
| FR-04.4 | Admin must be able to filter countries by region | High |
| FR-04.5 | Admin must be able to search countries by name | High |
| FR-04.6 | Admin must see which countries already exist in database | High |
| FR-04.7 | Admin must be able to bulk import selected countries | High |
| FR-04.8 | Admin must have "Auto Approve" option for bulk import | Medium |

### FR-05: Destination Management (Admin)

| ID | Requirement | Priority |
|----|-------------|----------|
| FR-05.1 | Admin must be able to view all destinations with pagination | High |
| FR-05.2 | Admin must be able to search destinations by country/capital | High |
| FR-05.3 | Admin must be able to filter destinations by status | High |
| FR-05.4 | Admin must be able to filter destinations by currency | Medium |
| FR-05.5 | Admin must be able to filter destinations by creation date range | Medium |
| FR-05.6 | Admin must be able to sort by country name, status, creation date | High |
| FR-05.7 | Admin must be able to view destination details | High |
| FR-05.8 | Admin must be able to edit destination information | High |
| FR-05.9 | Admin must be able to delete destinations | High |
| FR-05.10 | Admin must be able to approve pending destinations | High |
| FR-05.11 | Admin must be able to reject destinations with optional notes | High |
| FR-05.12 | Admin must be able to create single destination manually | Medium |

### FR-06: User Destination Browsing

| ID | Requirement | Priority |
|----|-------------|----------|
| FR-06.1 | Users must be able to view all approved destinations | High |
| FR-06.2 | Destinations must be displayed in a responsive grid layout | High |
| FR-06.3 | Each destination card must show: flag, country name, capital, region, population, currency | High |
| FR-06.4 | Users must be able to search destinations by country/capital | High |
| FR-06.5 | Users must be able to filter destinations by currency | Medium |
| FR-06.6 | Users must be able to sort by creation date, last modified, or country name | High |
| FR-06.7 | Users must be able to view detailed destination information | High |
| FR-06.8 | Destination details must show: flag, name, capital, region, subregion, population, currency, languages, timezones | High |
| FR-06.9 | Users must be able to see if a destination is already in their wishlist | High |

### FR-07: Wishlist Management

| ID | Requirement | Priority |
|----|-------------|----------|
| FR-07.1 | Users must be able to add destinations to wishlist | High |
| FR-07.2 | Users must be able to set priority (1-5) when adding to wishlist | High |
| FR-07.3 | Users must be able to add personal notes to wishlist items | Medium |
| FR-07.4 | Users must be able to view all wishlist items | High |
| FR-07.5 | Wishlist must show: country name, capital, region, status, priority, notes, rating, visited date | High |
| FR-07.6 | Users must be able to update wishlist item status (Want to Visit/Planning/Visited) | High |
| FR-07.7 | Users must be able to update priority, notes, rating, visited date | High |
| FR-07.8 | Users must be able to remove items from wishlist | High |
| FR-07.9 | Users must be able to filter wishlist by status | Medium |
| FR-07.10 | Users must be able to filter wishlist by priority range | Medium |
| FR-07.11 | Users must be able to search within wishlist | Medium |

### FR-08: Pagination & Filtering

| ID | Requirement | Priority |
|----|-------------|----------|
| FR-08.1 | Destination list must support pagination (12 items per page) | High |
| FR-08.2 | Wishlist must support pagination (12 items per page) | High |
| FR-08.3 | Admin destination list must support pagination (12 items per page) | High |
| FR-08.4 | Admin user list must support search and filtering | Medium |
| FR-08.5 | All tables must have loading states | High |
| FR-08.6 | All tables must have empty states | High |

---

## Non-Functional Requirements

### NFR-01: Performance

| ID | Requirement | Target |
|----|-------------|--------|
| NFR-01.1 | Page load time | < 3 seconds |
| NFR-01.2 | API response time | < 500ms for 90% of requests |
| NFR-01.3 | Search results display | < 1 second |
| NFR-01.4 | Pagination navigation | < 500ms |

### NFR-02: Security

| ID | Requirement | Priority |
|----|-------------|----------|
| NFR-02.1 | Passwords must be encrypted using BCrypt | High |
| NFR-02.2 | JWT tokens must expire after configured time | High |
| NFR-02.3 | All API endpoints (except public) must require authentication | High |
| NFR-02.4 | CORS must be properly configured | High |
| NFR-02.5 | Input validation on both client and server side | High |
| NFR-02.6 | SQL injection prevention using JPA/Hibernate | High |
| NFR-02.7 | XSS prevention | High |

### NFR-03: Usability

| ID | Requirement | Priority |
|----|-------------|----------|
| NFR-03.1 | Application must be responsive (mobile, tablet, desktop) | High |
| NFR-03.2 | All forms must have validation feedback | High |
| NFR-03.3 | Toast notifications for all user actions | High |
| NFR-03.4 | Loading indicators for async operations | High |
| NFR-03.5 | Dark mode support (automatic based on system preference) | Medium |
| NFR-03.6 | Consistent color scheme across all pages | High |

### NFR-04: Availability

| ID | Requirement | Target |
|----|-------------|--------|
| NFR-04.1 | Application uptime | 99.9% |
| NFR-04.2 | Graceful error handling | All errors must show user-friendly messages |
| NFR-04.3 | Database connection pooling | HikariCP with max 6 connections |

### NFR-05: Maintainability

| ID | Requirement | Priority |
|----|-------------|----------|
| NFR-05.1 | Modular code structure (feature-based modules) | High |
| NFR-05.2 | Consistent naming conventions | High |
| NFR-05.3 | Comprehensive logging | Medium |
| NFR-05.4 | Database migration tool (Liquibase) | High |
| NFR-05.5 | API documentation (Swagger/OpenAPI) | Medium |

---

## User Roles & Permissions

### Permission Matrix

| Feature | USER | ADMIN | SUPER_ADMIN |
|---------|------|-------|-------------|
| Register account | ✅ | ✅ | ✅ |
| Login | ✅ | ✅ | ✅ |
| View approved destinations | ✅ | ✅ | ✅ |
| View destination details | ✅ | ✅ | ✅ |
| Manage wishlist | ✅ | ✅ | ✅ |
| View admin dashboard | ❌ | ✅ | ✅ |
| Fetch countries from API | ❌ | ✅ | ✅ |
| Manage all destinations | ❌ | ✅ | ✅ |
| Approve/Reject destinations | ❌ | ✅ | ✅ |
| Create/Edit/Delete destinations | ❌ | ✅ | ✅ |
| View admin management | ❌ | ❌ | ✅ |
| Create admin users | ❌ | ❌ | ✅ |
| Activate/Deactivate admins | ❌ | ❌ | ✅ |
| Edit admin information | ❌ | ❌ | ✅ |

---

## Technical Requirements

### Backend Requirements

| Category | Requirement | Version |
|----------|-------------|---------|
| Framework | Spring Boot | 3.x |
| Language | Java | 17+ |
| Database | MySQL | 8.x |
| Build Tool | Maven | 3.8+ |
| ORM | Spring Data JPA | - |
| Security | Spring Security with JWT | - |
| Migration | Liquibase | - |
| Mapping | MapStruct | - |
| Email | Spring Mail (SMTP) | - |

### Frontend Requirements

| Category | Requirement | Version |
|----------|-------------|---------|
| Framework | Angular | 16+ |
| Language | TypeScript | 4.9+ |
| UI Library | Bootstrap | 5.x |
| Icons | Bootstrap Icons | - |
| HTTP Client | Angular HttpClient | - |
| Forms | Reactive Forms | - |
| Notifications | ngx-toastr | - |

### Development Environment

| Requirement | Specification |
|-------------|---------------|
| IDE | IntelliJ IDEA / VS Code |
| Version Control | Git + GitHub |
| API Testing | Postman / Swagger UI |
| Database Management | MySQL Workbench / DBeaver |

---

## API Requirements

### Authentication Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/auth/register` | Register new user | No |
| POST | `/api/auth/login` | User login | No |
| POST | `/api/auth/logout` | User logout | Yes |
| GET | `/api/auth/activate` | Activate account | No |
| POST | `/api/auth/resend-activation` | Resend activation email | No |
| POST | `/api/auth/forgot-password` | Request password reset | No |
| POST | `/api/auth/reset-password` | Reset password | No |

### Admin Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/admin/destinations` | Get all destinations | ADMIN/SUPER_ADMIN |
| POST | `/api/admin/destinations` | Create destination | ADMIN/SUPER_ADMIN |
| POST | `/api/admin/destinations/bulk` | Bulk create destinations | ADMIN/SUPER_ADMIN |
| PUT | `/api/admin/destinations/{id}` | Update destination | ADMIN/SUPER_ADMIN |
| DELETE | `/api/admin/destinations/{id}` | Delete destination | ADMIN/SUPER_ADMIN |
| POST | `/api/admin/destinations/{id}/approve` | Approve destination | ADMIN/SUPER_ADMIN |
| POST | `/api/admin/destinations/{id}/reject` | Reject destination | ADMIN/SUPER_ADMIN |
| GET | `/api/admin/destinations/summary` | Get destination statistics | ADMIN/SUPER_ADMIN |
| GET | `/api/admin/external/countries` | Fetch countries from API | ADMIN/SUPER_ADMIN |
| GET | `/api/admin/external/regions` | Get available regions | ADMIN/SUPER_ADMIN |

### User Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/users/destinations` | Get approved destinations | USER |
| GET | `/api/users/destinations/{id}` | Get destination details | USER |

### Wishlist Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/wishlist` | Get user wishlist | USER |
| POST | `/api/wishlist/{destinationId}` | Add to wishlist | USER |
| PUT | `/api/wishlist/{wishlistId}` | Update wishlist item | USER |
| DELETE | `/api/wishlist/{wishlistId}` | Remove from wishlist | USER |

### Super Admin Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/super-admin/admins` | Get all admins | SUPER_ADMIN |
| POST | `/api/super-admin/admins` | Create admin | SUPER_ADMIN |
| PUT | `/api/super-admin/admins/{id}` | Update admin | SUPER_ADMIN |
| PUT | `/api/super-admin/admins/{id}/activate` | Activate admin | SUPER_ADMIN |
| PUT | `/api/super-admin/admins/{id}/deactivate` | Deactivate admin | SUPER_ADMIN |

---

## Database Requirements

### Core Tables

| Table | Description | Key Fields                                                                                                                         |
|-------|-------------|------------------------------------------------------------------------------------------------------------------------------------|
| `users` | User accounts | id, email, password, full_name, role(`SUPER_ADMIN`,`ADMIN`,`USER`), is_active                                                      |
| `destinations` | Destination information | id, country_code, country_name, capital, region, population, currency_code, flag_url, status(`PENDING`,`APPROVED`,`REJECTED`)      |
| `wishlist` | User wishlist entries | id, user_id, destination_id, priority, notes, status(`WANT_TO_VISIT`,`VISITED`,`PLANNING`), rating                                 |
| `tokens` | tokens | id, user_id, token, expiry_date,tokenType(`ACTIVATION`,`PASSWORD_RESET`)                                                           |


### Data Validation Rules

| Field | Rule |
|-------|------|
| email | Unique, valid email format |
| password | Minimum 8 characters |
| full_name | Minimum 2 characters, maximum 100 characters |
| country_code | 2-3 uppercase letters |
| priority | Between 1 and 5 |
| rating | Between 1 and 5 (optional) |

---

## Security Requirements

### Authentication
- JWT-based authentication
- Token stored in localStorage
- Token included in Authorization header: `Bearer <token>`
- Token expiry: Configurable (default 24 hours)

### Authorization
- Role-based access control using Spring Security
- Method-level security with `@Secured` annotations
- Route guards on frontend

### Data Protection
- Passwords encrypted using BCrypt
- HTTPS for production (recommended)
- Environment-specific configuration files

### Input Validation
- Client-side validation using Angular Reactive Forms
- Server-side validation using Jakarta Validation
- Sanitize user inputs to prevent XSS

---

---

## External Dependencies

### APIs

| API | URL | Purpose |
|-----|-----|---------|
| REST Countries | `https://restcountries.com/v3.1` | Fetch country data |

### Email Service

| Provider | Configuration | Purpose |
|----------|---------------|---------|
| Gmail SMTP | smtp.gmail.com:587 | Send activation and reset emails |

### Libraries & Frameworks

| Library | Version | Purpose |
|---------|---------|---------|
| Bootstrap | 5.x | UI components |
| ngx-toastr | Latest | Toast notifications |
| RxJS | 7.x | Reactive programming |
| Liquibase | Latest | Database migrations |
| MapStruct | Latest | Object mapping |

---

## Acceptance Criteria Summary

### Must Have (High Priority)
- [x] User registration with email activation
- [x] User login with JWT authentication
- [x] Role-based access control (USER, ADMIN, SUPER_ADMIN)
- [x] Admin dashboard with statistics
- [x] Fetch countries from external API
- [x] Bulk import destinations
- [x] Approve/reject destinations with notes
- [x] View approved destinations (users)
- [x] Add/remove destinations to wishlist
- [x] Pagination on all lists
- [x] Responsive design

### Should Have (Medium Priority)
- [x] Super admin management (create/activate/deactivate admins)
- [x] Password reset functionality
- [x] Search and filter on destinations
- [x] Sorting on destinations
- [x] Wishlist update (priority, notes, status, rating)
- [x] Dark mode support
- [x] Advanced filtering (date range, currency)

### Could Have (Nice to Have)
- [x] Bulk import with auto-approve
- [x] Password strength meter
- [x] Active filters display with remove buttons
- [x] Resend activation email
- [x] Countdown timer on activation success

---

## Glossary

| Term | Definition |
|------|------------|
| JWT | JSON Web Token - used for authentication |
| DTO | Data Transfer Object |
| VTO | View Transfer Object |
| CRUD | Create, Read, Update, Delete |
| ORM | Object-Relational Mapping |
| CORS | Cross-Origin Resource Sharing |
| XSS | Cross-Site Scripting |
| SQLi | SQL Injection |

---

## Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | April 2026 | Mohamed Ehab | Initial requirements document |

---

## Approval

| Role | Name | Signature | Date |
|------|------|-----------|------|
| Developer | Mohamed Ehab | ✅ | April 2026 |
| Reviewer | - | - | - |
| Product Owner | - | - | - |

---

**Document Status: ✅ Completed**
