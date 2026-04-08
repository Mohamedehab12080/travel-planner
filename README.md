markdown
# ✈️ Travel Destination Planner

A full-stack web application for managing travel destinations, allowing administrators to curate destinations and users to explore and plan their travels.

---

## 📋 Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Complete Setup & Running Guide](#complete-setup--running-guide)
- [Default Users](#default-users)
- [API Endpoints](#api-endpoints)
- [License](#license)
- [Author](#author)

---

## ✨ Features

### Admin Dashboard
- **Authentication & Authorization** - Secure login for admins with JWT tokens
- **Fetch Countries from External API** - Import country data from REST Countries API
- **Destination Management** - Create, read, update, and delete destinations
- **Bulk Import** - Add multiple destinations at once with auto-approve option
- **Approval Workflow** - Approve or reject pending destinations with rejection notes
- **Advanced Filtering** - Filter by status, currency, date range, and search terms
- **Pagination & Sorting** - Sort by country name, status, creation date

### User Features
- **User Registration & Activation** - Email verification required (24-hour token expiry)
- **Password Recovery** - Forgot password with email reset link
- **View Approved Destinations** - Browse all approved destinations with details
- **Search & Filter** - Search by country name, currency, and date range
- **Wishlist Management** - Mark destinations as "Want to Visit"
- **Wishlist Customization** - Set priority, notes, rating, and visited date
- **Pagination** - Browse through paginated destination lists

### Bonus Features
- **Super Admin Management** - Create, activate/deactivate admin users
- **Rejection Notes** - Provide feedback when rejecting destinations
- **Email Notifications** - Activation and password reset emails

---

## 🛠 Technologies

### Backend

| Technology | Version | Purpose |
|------------|---------|---------|
| Spring Boot | 3.x | Application framework |
| Java | 17+ | Programming language |
| MySQL | 8.x | Database |
| Spring Security | 3.x | Authentication & authorization |
| JWT | - | Token-based authentication |
| Spring Data JPA | 3.x | ORM and data access |
| Liquibase | - | Database migration |
| MapStruct | - | Map between different objects |
| Spring Mail | - | Email service |
| Maven | - | Dependency management |

### Frontend

| Technology | Version | Purpose |
|------------|---------|---------|
| Angular | 16+ | Frontend framework |
| TypeScript | 4.9+ | Programming language |
| Bootstrap 5 | - | UI components |
| Bootstrap Icons | - | Icon library |
| RxJS | 7.x | Reactive programming |
| ngx-toastr | - | Toast notifications |

### External APIs
- **REST Countries API** - `https://restcountries.com/v3.1` - Country data source

---

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

| Prerequisite | Minimum Version | Verification Command |
|--------------|----------------|---------------------|
| Java | 17+ | `java --version` |
| Node.js | 18+ | `node --version` |
| npm | Latest | `npm --version` |
| MySQL | 8.x | `mysql --version` |
| Maven | 3.8+ | `mvn --version` |
| Git | Latest | `git --version` |

---

## 📁 Project Structure

### 1. Backend Structure

```
    travel-planner/
    ├── src/main/java/com/fawry/
    │   ├── common/              # Shared utilities, models, exceptions
    │   ├── destination/         # Destination module (CRUD, approval)
    │   ├── external/            # External API integration (REST Countries)
    │   ├── user/                # User management, authentication, activation
    │   ├── wishlist/            # Wishlist functionality
    │   └── travel/planner/      # Main application entry point
    ├── src/main/resources/
    │   ├── application-local.properties  # Local configuration
    │   ├── application.properties        # Base configuration
    │   └── templates/                    # Email templates (HTML)
    ├── service/travel_liquibase/         # Database migration scripts
    ├── ApplicationRunner/                # Executable runner
    │   └── travel.exe                    # Backend executable
    └── Database/                         # SQL scripts
    └── travel_planner_db.sql         # Database creation script
```

### 2. Frontend Structure

```
    travel-planner-front/
├── src/app/
│   ├── core/               # Core services, guards, interceptors
│   │   ├── services/       # Auth, Destination, Wishlist, SuperAdmin services
│   │   ├── guards/         # AuthGuard, RoleGuard
│   │   └── interceptors/   # AuthInterceptor (JWT token)
│   ├── modules/            # Feature modules
│   │   ├── admin/          # Admin components
│   │   ├── auth/           # Authentication components
│   │   └── user/           # User components
│   ├── shared/             # Shared components
│   └── environments/       # Environment configuration
├── start-frontend.bat      # One-click frontend launcher
├── angular.json
├── package.json
└── tsconfig.json
```

---

## 🚀 Complete Setup & Running Guide

### Step 1: Clone Repositories

# Clone backend repository
```bash
    git clone https://github.com/Mohamedehab12080/travel-planner.git
    
    # Clone frontend repository
    git clone https://github.com/Mohamedehab12080/travel-planner-front.git
```
### Step 2: Backend Setup

#### Option 1: Automatic Setup (Recommended)

**2.1 Configure MySQL Database**

Open MySQL Workbench and create a new user:

```sql
CREATE USER 'fawry_user'@'localhost' IDENTIFIED BY '25251436';
GRANT ALL PRIVILEGES ON *.* TO 'fawry_user'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;
```

**2.2 Create Database**

Run the SQL script located at:
```
{your clone path}/Database/travel_planner_db.sql
```

**2.3 Run Backend Executable**

Double-click the executable file:
```
{your clone path}/travel-planner/ApplicationRunner/travel.exe
```

The backend will start at: `http://localhost:7002`

#### Option 2: Manual Setup

**2.1 Configure Database**

Open `src/main/resources/application-local.properties` and update:

```properties
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

**2.2 Configure Liquibase**

Open `service/travel/_liquibase/config/liquibase-local.properties`:

```properties
username=YOUR_MYSQL_USERNAME
password=YOUR_MYSQL_PASSWORD
```

**2.3 Run Liquibase Migration**

```bash
service/travel/_liquibase/execute.bat
```

**2.4 Build and Run**

```bash
cd travel-planner
mvn clean install
mvn spring-boot:run
```

Or run from IDE:
```
src/main/java/com/fawry/travel/planner/FawryTravelPlannerManagement.java
```

> **📌 Note:** API documentation available at: `http://localhost:7002/swagger-ui/index.html`

### Step 3: Frontend Setup

#### Option 1: Automatic Setup (Recommended)

**Prerequisites (One Time Only)**

Install **Node.js** from: https://nodejs.org/ (LTS version 18.x or 20.x)

**One-Click Run**

1. Navigate to the frontend folder
2. **Double-click `start-frontend.bat`**
3. First-time setup takes 2-5 minutes
4. Application opens automatically at `http://localhost:4200`

> 💡 **Note:** The batch file automatically handles:
> - Node.js verification
> - Angular CLI installation
> - npm dependencies installation
> - Server startup

#### Option 2: Manual Setup

```bash
cd travel-planner-front
npm install
ng serve
```

**Frontend running at:** `http://localhost:4200`

### Step 4: Access the Application

Open your browser and navigate to:
```
http://localhost:4200
```

---

## 🔐 Default Users

### Super Admin User

| Field | Value |
|-------|-------|
| **Email** | mohamedehab12080@gmail.com |
| **Password** | 25251436Mh% |
| **Role** | ROLE_SUPER_ADMIN |
| **Permissions** | Full system access, can create/manage other admins |

### Admin User

| Field | Value |
|-------|-------|
| **Email** | m.ehab.rabea@gmail.com |
| **Password** | 25251436Mh% |
| **Role** | ROLE_ADMIN |
| **Permissions** | Manage destinations, approve/reject, fetch from external API |

### Regular User

| Field | Value |
|-------|-------|
| **Email** | demo@travelplanner.com |
| **Password** | Demo@123 |
| **Role** | ROLE_USER |
| **Permissions** | Browse destinations, manage wishlist |

### Creating New Admin Users

1. Log in as **Super Admin** (`mohamedehab12080@gmail.com`)
2. Navigate to **Admin Management** section
3. Click **"Create New Admin"**
4. Fill in admin details
5. Click **"Create Admin"**

### User Registration

1. Go to `http://localhost:4200/auth/register`
2. Fill in registration form
3. Check email for activation link (valid 24 hours)
4. Click activation link to verify account
5. Log in with your credentials

---

## 📡 API Endpoints

> **Base URL:** `http://localhost:7002/api`

| Category | Endpoint | Description |
|----------|----------|-------------|
| Authentication | `/auth/login` | User login |
| Authentication | `/auth/register` | User registration |
| Authentication | `/auth/activate` | Account activation |
| Authentication | `/auth/forgot-password` | Password reset request |
| Authentication | `/auth/reset-password` | Reset password |
| Admin | `/admin/destinations` | Manage destinations |
| Admin | `/admin/external/countries` | Fetch countries from API |
| User | `/users/destinations` | View approved destinations |
| User | `/wishlist` | Manage wishlist |
| Super Admin | `/super-admin/admins` | Manage admin users |

> **📌 Complete API docs:** `http://localhost:7002/swagger-ui/index.html`

---

## 🐛 Troubleshooting

### Backend Issues

| Issue | Solution |
|-------|----------|
| Port 7002 already in use | Kill process using port 7002 or change port in properties |
| Database connection failed | Verify MySQL is running and credentials are correct |
| Liquibase migration fails | Check database user permissions |

### Frontend Issues

| Issue | Solution |
|-------|----------|
| Node.js not found | Download and install Node.js from https://nodejs.org/ |
| Batch file closes immediately | Run as Administrator or extract folder first |
| npm install fails | Delete `node_modules` folder and try again |
| Cannot connect to backend | Ensure backend is running on `http://localhost:7002` |

---

## 📝 License

This project is developed for **Fawry Associate Software Engineer** selection process.

---

## 👨‍💻 Author

**Mohamed Ehab**

- GitHub: [@Mohamedehab12080](https://github.com/Mohamedehab12080)
- Email: m.ehab.rabea@gmail.com
- Phone: 01069911181
- LinkedIn: [mohaamed-ehab](https://www.linkedin.com/in/mohaamed-ehab/)

---

## ✅ Summary

| Component | URL | Status |
|-----------|-----|--------|
| Backend API | http://localhost:7002 | ✅ Running |
| Swagger UI | http://localhost:7002/swagger-ui/index.html | ✅ Available |
| Frontend App | http://localhost:4200 | ✅ Running |

---

**Happy Travel Planning! ✈️**
```