import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    // CREATE - Add new employee
    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, email, department, salary) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getDepartment());
            pstmt.setDouble(4, employee.getSalary());
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✅ Employee added successfully!");
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error adding employee: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    // READ - Get all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees ORDER BY id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");
                
                Employee emp = new Employee(id, name, email, department, salary);
                employees.add(emp);
            }
            
            System.out.println("✅ Retrieved " + employees.size() + " employees");
            
        } catch (SQLException e) {
            System.out.println("❌ Error retrieving employees: " + e.getMessage());
            e.printStackTrace();
        }
        
        return employees;
    }
    
    // READ - Get employee by ID
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        Employee employee = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                employee = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("department"),
                    rs.getDouble("salary")
                );
                System.out.println("✅ Employee found: " + employee.getName());
            } else {
                System.out.println("⚠️ No employee found with ID: " + id);
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error finding employee: " + e.getMessage());
            e.printStackTrace();
        }
        
        return employee;
    }
    
    // UPDATE - Modify existing employee
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, email = ?, department = ?, salary = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getDepartment());
            pstmt.setDouble(4, employee.getSalary());
            pstmt.setInt(5, employee.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✅ Employee updated successfully!");
                return true;
            } else {
                System.out.println("⚠️ No employee found with ID: " + employee.getId());
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error updating employee: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    // DELETE - Remove employee
    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✅ Employee deleted successfully!");
                return true;
            } else {
                System.out.println("⚠️ No employee found with ID: " + id);
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error deleting employee: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    // SEARCH - Find employees by department
    public List<Employee> getEmployeesByDepartment(String department) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE department = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, department);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("department"),
                    rs.getDouble("salary")
                );
                employees.add(emp);
            }
            
            System.out.println("✅ Found " + employees.size() + " employees in " + department);
            
        } catch (SQLException e) {
            System.out.println("❌ Error searching employees: " + e.getMessage());
            e.printStackTrace();
        }
        
        return employees;
    }
}
