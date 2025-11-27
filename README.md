# Employee-Database-System

Author : Nandhana MA

Description : 
- A Java-based console application for managing employee records using JDBC and PostgreSQL.
- This application provides a complete CRUD (Create, Read, Update, Delete) system for managing employee information.
- It demonstrates the implementation of database connectivity using JDBC and follows the DAO (Data Access Object) design pattern.

## Technologies Used

- **Java** - Core programming language
- **JDBC (Java Database Connectivity)** - Database connectivity API
- **PostgreSQL** - Relational database management system
- **Postico 2** - PostgreSQL client for macOS (for database management)

## Features

-  **Add New Employee** - Insert employee records with name, email, department, and salary
-  **View All Employees** - Display complete list of all employees in the database
-  **Search Employee by ID** - Find specific employee details using their ID
-  **Update Employee** - Modify existing employee information
-  **Delete Employee** - Remove employee records from the database
-  **Search by Department** - Filter employees by their department
-  **Input Validation** - Error handling for invalid inputs
-  **Secure Database Connection** - Proper connection management and resource cleanup

## 📁 Project Structure
```
EmployeeDB/
├── src/
│   ├── DatabaseConnection.java   # Handles database connection
│   ├── Employee.java              # Employee model class
│   ├── EmployeeDAO.java           # Data Access Object (CRUD operations)
│   └── Main.java                  # Main application with user interface
├── lib/
│   └── postgresql-42.7.8.jar      # PostgreSQL JDBC driver
├── compile.sh                     # Compilation script
└── run.sh                         # Run script
```
## Sample Output
### Main Menu Options
```
========================================
MAIN MENU
========================================
1. ➕ Add New Employee
2. 📋 View All Employees
3. 🔍 View Employee by ID
4. ✏️  Update Employee
5. ❌ Delete Employee
6. 🔎 Search by Department
7. 🚪 Exit
========================================
```

### Example: Adding an Employee

1. Select option `1` from the main menu
2. Enter employee details:
   - **Name**: John Doe
   - **Email**: john.doe@company.com
   - **Department**: Engineering
   - **Salary**: 75000
3. Employee will be added to the database

### Example: Viewing All Employees

1. Select option `2` from the main menu
2. All employee records will be displayed in a formatted list


