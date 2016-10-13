<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 24.07.2016, 14:50:24
    Author     : ДмитрийГ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.Interaction" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Emps and Depts</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="lib/w3css/w3.css">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.min.css" />

    </head>

    <body>

        <jsp:scriptlet>
            pageContext.setAttribute("employees", Interaction.getEmployees());
            pageContext.setAttribute("departments", Interaction.getDepartments());
        </jsp:scriptlet>

        <!-- MODALS -->

        <div id="add-department-modal" class="w3-modal">
            <div class="w3-modal-content w3-card-8">

                <header class="w3-container w3-blue-grey"> 
                    <span onclick="document.getElementById('add-department-modal').style.display = 'none'" 
                          class="w3-closebtn">&times;</span>
                    <h2>Add new department</h2>
                </header>

                <div class="w3-container">
                    <form id="addDepartment" class="w3-container w3-margin-16" action="DepartmentsAddingServlet" method="post">

                        <label>Enter name of new department</label>
                        <input class="w3-input w3-border" type="text" name="newName">
                        <button class="w3-btn w3-blue-grey w3-margin-16 w3-right" type="submit">Submit</button>

                    </form>
                </div>

            </div>
        </div>

        <div id="add-employee-modal" class="w3-modal">
            <div class="w3-modal-content w3-card-8">

                <header class="w3-container w3-blue-grey"> 
                    <span onclick="document.getElementById('add-employee-modal').style.display = 'none'" 
                          class="w3-closebtn">&times;</span>
                    <h2>Add new employee</h2>
                </header>

                <div class="w3-container">
                    <form id="addEmployee" class="w3-container w3-margin-16" action="EmployeesAddingServlet" method="post">

                        <label>Name</label>
                        <input class="w3-input w3-border" type="text" name="name">

                        <label>Email</label>
                        <input class="w3-input w3-border" type="email" name="email">

                        <label>Hire date</label>
                        <input class="w3-input w3-border" type="date" name="hireDate">

                        <label>Salary</label>
                        <input class="w3-input w3-border" type="text" name="salary">

                        <label>Department</label>
                        <select class="w3-select" name="department">
                            <option value="" disabled selected>Choose department...</option>
                            <c:forEach var="department" items="${departments}">
                                <option><c:out value="${department.getDepartmentName()}" /></option>
                            </c:forEach>
                        </select>

                        <button class="w3-btn w3-blue-grey w3-margin-16 w3-right" type="submit">Submit</button>

                    </form>
                </div>

            </div>
        </div>
        
        <div id="edit-department-modal" class="w3-modal">
            <div class="w3-modal-content w3-card-8">

                <header class="w3-container w3-blue-grey"> 
                    <span onclick="document.getElementById('edit-department-modal').style.display = 'none'" 
                          class="w3-closebtn">&times;</span>
                    <h2>Edit department</h2>
                </header>

                <div class="w3-container">
                    <form id="editDepartment" class="w3-container w3-margin-16" action="DepartmentsEditingServlet" method="post">

                        <div id="edit-department-message" class="w3-margin-bottom"></div>
                        <input id="edit-department-id" type="hidden" name="id">
                        
                        <label>Enter new name of department</label>
                        <input class="w3-input w3-border" type="text" name="newName">
                        <button class="w3-btn w3-blue-grey w3-margin-16 w3-right" type="submit">Submit</button>

                    </form>
                </div>

            </div>
        </div>
        
        <div id="edit-employee-modal" class="w3-modal">
            <div class="w3-modal-content w3-card-8">

                <header class="w3-container w3-blue-grey"> 
                    <span onclick="document.getElementById('edit-employee-modal').style.display = 'none'" 
                          class="w3-closebtn">&times;</span>
                    <h2>Edit employee</h2>
                </header>

                <div class="w3-container">
                    <form id="editEmployee" class="w3-container w3-margin-16" action="EmployeesEditingServlet" method="post">

                        <div id="edit-employee-message" class="w3-margin-bottom"></div>
                        <input id="edit-employee-id" type="hidden" name="id">
                        
                        <label>Name</label>
                        <input class="w3-input w3-border" type="text" name="name">

                        <label>Email</label>
                        <input class="w3-input w3-border" type="email" name="email">

                        <label>Hire date</label>
                        <input class="w3-input w3-border" type="date" name="hireDate">

                        <label>Salary</label>
                        <input class="w3-input w3-border" type="text" name="salary">

                        <label>Department</label>
                        <select class="w3-select" name="department">
                            <option value="" disabled selected>Choose department...</option>
                            <c:forEach var="department" items="${departments}">
                                <option><c:out value="${department.getDepartmentName()}" /></option>
                            </c:forEach>
                        </select>

                        <button class="w3-btn w3-blue-grey w3-margin-16 w3-right" type="submit">Submit</button>

                    </form>
                </div>

            </div>
        </div>

        <div id="delete-employee-modal" class="w3-modal">
            <div class="w3-modal-content w3-card-8">

                <header class="w3-container w3-blue-grey"> 
                    <span onclick="document.getElementById('delete-employee-modal').style.display = 'none'" 
                          class="w3-closebtn">&times;</span>
                    <h2>Warning</h2>
                </header>

                <div class="w3-container w3-padding-8">
                    <label class="w3-large w3-center">Are you sure?</label>

                    <button class="w3-btn w3-blue-grey w3-margin-16 w3-right" type="submit">No</button>
                    <button class="w3-btn w3-blue-grey w3-margin-16 w3-right" type="submit">Yes</button>
                </div>

            </div>
        </div>

        <!--PAGE CONTENT-->

        <div class="w3-container w3-white w3-border w3-center w3-margin-bottom w3-padding-jumbo header">
            Employees and Departments
        </div>

        <section class="w3-container w3-third depts-section">

            <div class="w3-xlarge">Departments</div>

            <table class="w3-table w3-border w3-striped w3-hoverable">

                <tr class="w3-blue-grey">
                    <th>ID</th>
                    <th>Name</th>
                    <th></th>
                    <th></th>
                </tr>

                <c:forEach var="department" items="${departments}">
                    <tr>
                        <td><c:out value="${department.getDepartmentId()}" /></td>
                        <td onclick="deptSelection(this)"><c:out value="${department.getDepartmentName()}" /></td>
                        <td class="w3-hover-text-orange" onclick="deptEdit(this.parentNode)"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></td>
                        <td class="w3-hover-text-red" onclick="deptDelete(this.parentNode)" ><i class="fa fa-times" aria-hidden="true"></i></td>
                    </tr>
                </c:forEach>

                <tr>
                    <td></td>
                    <td onclick="showAllDepts()">Show all..</td>
                    <td></td>
                    <td></td>
                </tr>

            </table>

            <div class="w3-margin-8 w3-center">
                <button class="w3-btn w3-blue-grey add-button" onclick="document.getElementById('add-department-modal').style.display = 'block'">Add</button>
            </div>


        </section>


        <section class="w3-twothird emps-section">

            <div class="w3-xlarge">Employees</div>

            <table class="w3-table w3-border w3-striped w3-hoverable" id="emps-table">

                <tr class="w3-blue-grey">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Hire Date</th>
                    <th>Salary</th>
                    <th>Department</th>
                    <th></th>
                    <th></th>
                </tr>

                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td><c:out value="${employee.getEmployeeId()}" /></td>
                        <td><c:out value="${employee.getEmployeeName()}" /></td>
                        <td><c:out value="${employee.getEmployeeEmail()}" /></td>
                        <td><c:out value="${employee.getEmployeeHireDate()}" /></td>
                        <td><c:out value="${employee.getEmployeeSalary()}" /></td>
                        <td><c:out value="${employee.getEmployeeDepartment()}" /></td>
                        <td class="w3-hover-text-orange" onclick="empEdit(this.parentNode)"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></td>
                        <td class="w3-hover-text-red" onclick="empDelete(this.parentNode)"><i class="fa fa-times" aria-hidden="true"></i></td>
                    </tr>
                </c:forEach>
            </table>

            <div class="w3-margin-8 w3-center">
                <button class="w3-btn w3-blue-grey add-button" onclick="document.getElementById('add-employee-modal').style.display = 'block'">Add</button>
            </div>

        </section>

        <script src="lib/jquery/jquery-2.1.3.min.js"></script>
        <script src ="js/deptsFilter.js"></script>
        <script src="js/deptsProcessing.js"></script>
        <script src="js/empsProcessing.js"></script>

    </body>
</html>
