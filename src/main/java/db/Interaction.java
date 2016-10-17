package db;

import entities.Department;
import entities.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ДмитрийГ
 */
public class Interaction {

    static String driver = "org.postgresql.Driver";
    static String url = "jdbc:postgres://ufxsylltsbzpct:km7VpRxnMqaIKIdiiwz9Dsf6me@ec2-54-75-233-22.eu-west-1.compute.amazonaws.com:5432/dnk684j6s0914";

    static String user = "ufxsylltsbzpct";
    static String pass = "km7VpRxnMqaIKIdiiwz9Dsf6me";
    
    static private Connection createConnection() throws SQLException{
        
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("\nDRIVER NOT FOUND.\n");
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DataSource datasource;

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            datasource = (DataSource) envContext.lookup("jdbc/herokuDB");
        } catch (NamingException e) {
            System.out.println("\nNAMING EXCEPTION.\n");
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        
        if(datasource == null) {
            System.out.println("\nDATASOURCE PROBLEMS.\n");
            return null;
        }
        
        Connection newConnection = datasource.getConnection();
        
        return newConnection;
    }

    public static boolean employeeExist(String email) {

        boolean result = false;

        try(Connection connection = createConnection()) {

            Statement statement = connection.createStatement();
            
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM \"emps-and-depts\".\"employee\" WHERE employee_email = '" + email + "'");

            result = resultSet.next();

        } catch (SQLException e) {
            System.out.println("\nPROBLEMS WITH DATABASE CONNECTION.\n");
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, e);
        }

        return result;
    }

    public static boolean departmentExist(String name) {
        
        boolean result = false;

        try(Connection connection = createConnection()) {
         
            Statement statement;

            statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM \"emps-and-depts\".\"department\" WHERE department_name = '" + name + "'");

            result = resultSet.next();

        } catch (SQLException e) {
            System.out.println("\nPROBLEMS WITH DATABASE CONNECTION.\n");
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return result;
    }

    public static List getEmployees() throws SQLException {
        List result = null;

        try(Connection connection = createConnection()) {

            Statement statement = connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery(
                    "SELECT e.* ,d.department_name FROM \"emps-and-depts\".\"employee\" as e,\"emps-and-depts\".\"department\" as d WHERE e.employee_department_id = d.department_id;");

            result = new ArrayList<>();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setEmployeeName(resultSet.getString("employee_name"));
                employee.setEmployeeEmail(resultSet.getString("employee_email"));
                employee.setEmployeeHireDate(resultSet.getString("employee_hire_date"));
                employee.setEmployeeSalary(resultSet.getDouble("employee_salary"));
                employee.setEmployeeDepartment(resultSet.getString("department_name"));
                employee.setEmployeeDepartmentId(resultSet.getInt("employee_department_id"));
                result.add(employee);
            }

        } catch (SQLException e) {
            System.out.println("\nPROBLEMS WITH DATABASE CONNECTION.\n");
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return result;
    }

    public static Department getDepartment(String name) throws SQLException {

        Department result = null;

        try(Connection connection = createConnection()) {
            
            Statement statement;

            statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM \"emps-and-depts\".department WHERE department_name = '" + name + "'");

            result = new Department();

            while (resultSet.next()) {
                result.setDepartmentId(resultSet.getInt("department_id"));
                result.setDepartmentName(resultSet.getString("department_name"));
            }
        }
        return result;
    }

    public static List getDepartments() {
        
        List result = null;
        
        try(Connection connection = createConnection()) {

            Statement statement = connection.createStatement();
 
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"emps-and-depts\".\"department\"");

            result = new ArrayList<>();

            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                result.add(department);
            }

        } catch (SQLException e) {
            System.out.println("\nPROBLEMS WITH DATABASE CONNECTION.\n");
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, e);
        }

        return result;
    }

    public static void addEmployee(Employee employee) throws SQLException {
        
        try (Connection connection = createConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO \"emps-and-depts\".\"employee\" VALUES (DEFAULT,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, employee.getEmployeeName());
            statement.setString(2, employee.getEmployeeEmail());
            statement.setDate(3, Date.valueOf(employee.getEmployeeHireDate()));
            statement.setDouble(4, employee.getEmployeeSalary());
            statement.setInt(5, employee.getEmployeeDepartmentId());
            
            System.out.println("\n\n\nHERE IS STATEMENT:\n" + statement + "\n\n\n");

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Adding employee failed, no rows affected.");
            }
        }
    }

    public static void addDepartment(Department department) throws SQLException {

        try(Connection connection = createConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO \"emps-and-depts\".\"department\" VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);) {

            statement.setInt(1, department.getDepartmentId());
            statement.setString(2, department.getDepartmentName());
            
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Adding employee failed, no rows affected.");
            } 
        }
    }

    public static void deleteDepartment(int id) throws SQLException {
        try (Connection connection = createConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM \"emps-and-depts\".\"department\" WHERE department_id = ?", Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, id);
            
            System.out.println(statement);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting department failed, no rows affected.");
            }
        }
    }

    public static void deleteEmployee(int id) throws SQLException {

        try (Connection connection = createConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM \"emps-and-depts\".\"employee\" WHERE employee_id = ?",
                        Statement.RETURN_GENERATED_KEYS);) {

            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting employee failed, no rows affected.");
            }
        }
    }

    public static void editDepartment(Department department) throws SQLException {

        try (Connection connection = createConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE \"emps-and-depts\".\"department\" SET " + "department_name = ? " + "WHERE department_id = ?;",
                        Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, department.getDepartmentName());
            statement.setInt(2, department.getDepartmentId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Editing department failed, no rows affected.");
            }
        }
    }

    public static void editEmployee(Employee employee) throws SQLException {

        try (Connection connection = createConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE \"emps-and-depts\".\"employee\" SET " + "employee_name = ?, "
                        + "employee_email = ?, " + "employee_hire_date = ?, " + "employee_salary = ?, "
                        + "employee_department_id = ? " + "WHERE employee_id = ?;", Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, employee.getEmployeeName());
            statement.setString(2, employee.getEmployeeEmail());
            statement.setDate(3, Date.valueOf(employee.getEmployeeHireDate()));
            statement.setDouble(4, employee.getEmployeeSalary());
            statement.setInt(5, employee.getEmployeeDepartmentId());
            statement.setInt(6, employee.getEmployeeId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Editing employee failed, no rows affected.");
            }
        }
    }

}
