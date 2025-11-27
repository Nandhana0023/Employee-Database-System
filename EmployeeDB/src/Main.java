import java.util.List;
import java.util.Scanner;

public class Main {
    
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        System.out.println("════════════════════════════════════════");
        System.out.println("   EMPLOYEE DATABASE MANAGEMENT SYSTEM  ");
        System.out.println("════════════════════════════════════════");
        
        boolean running = true;
        
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    addNewEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    viewEmployeeById();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    searchByDepartment();
                    break;
                case 7:
                    System.out.println("\n👋 Thank you for using Employee Database System!");
                    running = false;
                    break;
                default:
                    System.out.println("\n⚠️ Invalid choice! Please select 1-7");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("MAIN MENU");
        System.out.println("========================================");
        System.out.println("1. ➕ Add New Employee");
        System.out.println("2. 📋 View All Employees");
        System.out.println("3. 🔍 View Employee by ID");
        System.out.println("4. ✏️  Update Employee");
        System.out.println("5. ❌ Delete Employee");
        System.out.println("6. 🔎 Search by Department");
        System.out.println("7. 🚪 Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice (1-7): ");
    }
    
    private static int getUserChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void addNewEmployee() {
        System.out.println("\n----------------------------------------");
        System.out.println("ADD NEW EMPLOYEE");
        System.out.println("----------------------------------------");
        
        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Enter Department: ");
            String department = scanner.nextLine();
            
            System.out.print("Enter Salary: ");
            double salary = Double.parseDouble(scanner.nextLine());
            
            Employee employee = new Employee(name, email, department, salary);
            boolean success = employeeDAO.addEmployee(employee);
            
            if (success) {
                System.out.println("\n🎉 Employee added successfully!");
            }
            
        } catch (Exception e) {
            System.out.println("\n❌ Error: Invalid input. Please try again.");
        }
    }
    
    private static void viewAllEmployees() {
        System.out.println("\n----------------------------------------");
        System.out.println("ALL EMPLOYEES");
        System.out.println("----------------------------------------");
        
        List<Employee> employees = employeeDAO.getAllEmployees();
        
        if (employees.isEmpty()) {
            System.out.println("📭 No employees found in database.");
        } else {
            System.out.println();
            for (Employee emp : employees) {
                System.out.println(emp);
            }
            System.out.println("\nTotal Employees: " + employees.size());
        }
    }
    
    private static void viewEmployeeById() {
        System.out.println("\n----------------------------------------");
        System.out.println("VIEW EMPLOYEE BY ID");
        System.out.println("----------------------------------------");
        
        try {
            System.out.print("Enter Employee ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Employee employee = employeeDAO.getEmployeeById(id);
            
            if (employee != null) {
                System.out.println("\n" + employee);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Error: Please enter a valid number.");
        }
    }
    
    private static void updateEmployee() {
        System.out.println("\n----------------------------------------");
        System.out.println("UPDATE EMPLOYEE");
        System.out.println("----------------------------------------");
        
        try {
            System.out.print("Enter Employee ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Employee existing = employeeDAO.getEmployeeById(id);
            
            if (existing == null) {
                return;
            }
            
            System.out.println("\nCurrent Details: " + existing);
            System.out.println("\nEnter new details (press Enter to keep current value):");
            
            System.out.print("Name [" + existing.getName() + "]: ");
            String name = scanner.nextLine();
            if (name.isEmpty()) name = existing.getName();
            
            System.out.print("Email [" + existing.getEmail() + "]: ");
            String email = scanner.nextLine();
            if (email.isEmpty()) email = existing.getEmail();
            
            System.out.print("Department [" + existing.getDepartment() + "]: ");
            String department = scanner.nextLine();
            if (department.isEmpty()) department = existing.getDepartment();
            
            System.out.print("Salary [" + existing.getSalary() + "]: ");
            String salaryStr = scanner.nextLine();
            double salary = salaryStr.isEmpty() ? existing.getSalary() : Double.parseDouble(salaryStr);
            
            Employee updated = new Employee(id, name, email, department, salary);
            boolean success = employeeDAO.updateEmployee(updated);
            
            if (success) {
                System.out.println("\n🎉 Employee updated successfully!");
            }
            
        } catch (Exception e) {
            System.out.println("\n❌ Error: Invalid input. Please try again.");
        }
    }
    
    private static void deleteEmployee() {
        System.out.println("\n----------------------------------------");
        System.out.println("DELETE EMPLOYEE");
        System.out.println("----------------------------------------");
        
        try {
            System.out.print("Enter Employee ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Are you sure you want to delete this employee? (yes/no): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("yes")) {
                boolean success = employeeDAO.deleteEmployee(id);
                
                if (success) {
                    System.out.println("\n🗑️ Employee deleted successfully!");
                }
            } else {
                System.out.println("\n❌ Deletion cancelled.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Error: Please enter a valid number.");
        }
    }
    
    private static void searchByDepartment() {
        System.out.println("\n----------------------------------------");
        System.out.println("SEARCH BY DEPARTMENT");
        System.out.println("----------------------------------------");
        
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        
        List<Employee> employees = employeeDAO.getEmployeesByDepartment(department);
        
        if (employees.isEmpty()) {
            System.out.println("\n📭 No employees found in department: " + department);
        } else {
            System.out.println();
            for (Employee emp : employees) {
                System.out.println(emp);
            }
            System.out.println("\nTotal Employees in " + department + ": " + employees.size());
        }
    }
}
