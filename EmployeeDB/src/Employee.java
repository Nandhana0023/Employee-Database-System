public class Employee {
    
    private int id;
    private String name;
    private String email;
    private String department;
    private double salary;
    
    public Employee(String name, String email, String department, double salary) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }
    
    public Employee(int id, String name, String email, String department, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getDepartment() {
        return this.department;
    }
    
    public double getSalary() {
        return this.salary;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Employee[ID=%d, Name=%s, Email=%s, Department=%s, Salary=$%.2f]",
            id, name, email, department, salary
        );
    }
}
