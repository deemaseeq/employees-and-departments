package server.employees;

import db.Interaction;
import entities.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author deemaseeque
 */
public class EmployeesEditingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmployeeEditingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeEditingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void tryEditEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");

        try {

            int employeeDeptId
                    = Interaction.getDepartment(request.getParameter("department")).getDepartmentId();

            Employee editableEmployee = new Employee();
            editableEmployee.setEmployeeId(Integer.parseInt(request.getParameter("id")));
            editableEmployee.setEmployeeEmail(request.getParameter("email"));
            editableEmployee.setEmployeeName(request.getParameter("name"));
            editableEmployee.setEmployeeDepartmentId(employeeDeptId);
            editableEmployee.setEmployeeDepartment(request.getParameter("department"));
            editableEmployee.setEmployeeHireDate(Date.valueOf(request.getParameter("hireDate")));
            editableEmployee.setEmployeeSalary(Double.parseDouble(request.getParameter("salary")));

            Interaction.editEmployee(editableEmployee);

            try (PrintWriter out = response.getWriter()) {
                out.println("Succesful editing");
            }

        } catch (SQLException ex) {
            try (PrintWriter out = response.getWriter()) {
                out.println("Failed to access database. Please try again later.");
            }
            Logger.getLogger(EmployeesEditingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        tryEditEmployee(request, response);
    }

}
