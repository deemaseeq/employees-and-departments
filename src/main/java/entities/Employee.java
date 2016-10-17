/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ДмитрийГ
 */
public class Employee {
    int employeeId;
    String employeeName;
    String employeeEmail;
    String employeeHireDate;
    double employeeSalary;
    String employeeDepartment;
    int employeeDepartmentId;

    public Employee() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public String getEmployeeHireDate() {
        return employeeHireDate;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public void setEmployeeHireDate(String employeeHireDate) {
        this.employeeHireDate = employeeHireDate;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public int getEmployeeDepartmentId() {
        return employeeDepartmentId;
    }

    public void setEmployeeDepartmentId(int employeeDepartmentId) {
        this.employeeDepartmentId = employeeDepartmentId;
    }
    
    

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", employeeName=" +
                employeeName + ", employeeEmail=" + employeeEmail +
                ", employee_hire_date=" + employeeHireDate + ", employee_salary=" +
                employeeSalary + ", employeeDepartment=" + employeeDepartment + '}';
    }

    


}
