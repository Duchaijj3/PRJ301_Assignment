/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.LeaveRequestDBContext;
import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AgendaController", urlPatterns = {"/AgendaController"})
public class AgendaController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String fromDateStr = request.getParameter("fromDate");
        String toDateStr = request.getParameter("toDate");

        if (fromDateStr != null && toDateStr != null) {
            Date fromDate = Date.valueOf(fromDateStr);
            Date toDate = Date.valueOf(toDateStr);

            UserDBContext userDB = new UserDBContext();
            LeaveRequestDBContext leaveDB = new LeaveRequestDBContext();
            List<User> departmentUsers = userDB.getUsersByDepartment(user.getDepartmentID());
            Map<Integer, List<Boolean>> agenda = leaveDB.getAgenda(user.getDepartmentID(), fromDate, toDate);

            List<String> dates = new ArrayList<>();
            // Generate date list (simplified, add logic to list dates between fromDate and toDate)

            request.setAttribute("agenda", agenda);
            request.setAttribute("dates", dates);
        }

        request.getRequestDispatcher("agenda.jsp").forward(request, response);
    }
}