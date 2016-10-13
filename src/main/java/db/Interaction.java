/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.postgresql.Driver;

/**
 *
 * @author ДмитрийГ
 */
public class Interaction {

    static String driver = "org.postgresql.Driver";
    static String url = "jdbc:postgres://qmgiiroiiidkfi:1mtj67kIU11g2cPQFGxfTW7T3K@ec2-54-243-190-100.compute-1.amazonaws.com:5432/d81oo7vklfduaf";

    static String user = "qmgiiroiiidkfi";
    static String pass = "1mtj67kIU11g2cPQFGxfTW7T3K";

    public static boolean employeeExist(String email) {
        Connection connection = null;
        boolean result = false;

        try {

            Class.forName(driver);
            // System.out.println("Драйвер подключен");

            connection = DriverManager.getConnection(url, user, pass);
            // System.out.println("Соединение установлено");

            Statement statement;

            statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM employee WHERE employee_email = '" + email + "'");

            result = resultSet.next();

        } catch (Exception ex) {
            // выводим наиболее значимые сообщения
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return result;
    }

    public static boolean departmentExist(String name) {
        Connection connection = null;
        boolean result = false;

        try {

            Class.forName(driver);
            // System.out.println("Драйвер подключен");

            connection = DriverManager.getConnection(url, user, pass);
            // System.out.println("Соединение установлено");

            Statement statement;

            statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM department WHERE department_name = '" + name + "'");

            result = resultSet.next();

        } catch (Exception ex) {
            // выводим наиболее значимые сообщения
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return result;
    }

    public static List getEmployees() {
        Connection connection = null;
        List result = null;

        try {

            Class.forName(driver);
            // System.out.println("Драйвер подключен");

            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource datasource = (DataSource) envContext.lookup("jdbc/herokuDB");

            connection = datasource.getConnection();

//			connection = DriverManager.getConnection(url, user, pass);
            // System.out.println("Соединение установлено");
            Statement statement;

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT e.* ,d.department_name FROM employee as e,department as d WHERE e.employee_department_id = d.department_id;");

            result = new ArrayList<>();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setEmployeeName(resultSet.getString("employee_name"));
                employee.setEmployeeEmail(resultSet.getString("employee_email"));
                employee.setEmployeeHireDate(resultSet.getDate("employee_hire_date"));
                employee.setEmployeeSalary(resultSet.getDouble("employee_salary"));
                employee.setEmployeeDepartment(resultSet.getString("department_name"));
                employee.setEmployeeDepartmentId(resultSet.getInt("employee_department_id"));
                result.add(employee);
            }

        } catch (Exception ex) {
            // выводим наиболее значимые сообщения
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return result;
    }

    public static Department getDepartment(String name) {
        
        Connection connection = null;
        Department result = null;

        try {

            Class.forName(driver);
            // System.out.println("Драйвер подключен");
            
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource datasource = (DataSource) envContext.lookup("jdbc/herokuDB");

            connection = datasource.getConnection();

//            connection = DriverManager.getConnection(url, user, pass);
            // System.out.println("Соединение установлено");

            Statement statement;

            statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM department WHERE department_name = '" + name + "'");

            result = new Department();

            while (resultSet.next()) {
                result.setDepartmentId(resultSet.getInt("department_id"));
                result.setDepartmentName(resultSet.getString("department_name"));
            }

        } catch (Exception ex) {
            // выводим наиболее значимые сообщения
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return result;
    }

    public static List getDepartments() {
        Connection connection = null;
        List result = null;

        try {

            Class.forName(driver);
            // System.out.println("Драйвер подключен");

            connection = DriverManager.getConnection(url, user, pass);
            // System.out.println("Соединение установлено");

            Statement statement;

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM department");

            result = new ArrayList<>();

            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                result.add(department);
            }

        } catch (Exception ex) {
            // выводим наиболее значимые сообщения
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return result;
    }

    public static void addEmployee(Employee employee) {
        try (Connection connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO employee VALUES (DEFAULT,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, employee.getEmployeeName());
            statement.setString(2, employee.getEmployeeEmail());
            statement.setString(3, employee.getEmployeeHireDate().toString());
            statement.setString(4, Double.toString(employee.getEmployeeSalary()));
            statement.setString(5, Integer.toString(employee.getEmployeeDepartmentId()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

        } catch (Exception ex) {
            // выводим наиболее значимые сообщения
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addDepartment(Department department) {
        Connection connection = null;

        try {

            Class.forName(driver);
            System.out.println("Драйвер подключен");

            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Соединение установлено");

            Statement statement;

            String query = "INSERT INTO department VALUES (" + department.getDepartmentId() + ", '"
                    + department.getDepartmentName() + "');";

            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception ex) {
            // выводим наиболее значимые сообщения
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void deleteDepartment(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM department WHERE department_id = ?", Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting department failed, no rows affected.");
            }

        } catch (Exception ex) {
            // выводим наиболее значимые сообщения
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteEmployee(String id) {

        try (Connection connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE employee_id = ?",
                        Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting employee failed, no rows affected.");
            }

        } catch (Exception ex) {
            // выводим наиболее значимые сообщения
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editDepartment(Department department) {

        try (Connection connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE department SET " + "department_name = ? " + "WHERE department_id = ?;",
                        Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, department.getDepartmentName());
            statement.setString(2, Integer.toString(department.getDepartmentId()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Editing department failed, no rows affected.");
            }

        } catch (Exception ex) {
            // выводим наиболее значимые сообщения
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editEmployee(Employee employee) {

        try (Connection connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement statement = connection.prepareStatement("UPDATE employee SET " + "employee_name = ?, "
                        + "employee_email = ?, " + "employee_hire_date = ?, " + "employee_salary = ?, "
                        + "employee_department_id = ? " + "WHERE employee_id = ?;", Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, employee.getEmployeeName());
            statement.setString(2, employee.getEmployeeEmail());
            statement.setString(3, employee.getEmployeeHireDate().toString());
            statement.setString(4, Double.toString(employee.getEmployeeSalary()));
            statement.setString(5, Integer.toString(employee.getEmployeeDepartmentId()));
            statement.setString(6, Integer.toString(employee.getEmployeeId()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Editing employee failed, no rows affected.");
            }

        } catch (Exception ex) {
            // выводим наиболее значимые сообщения
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
