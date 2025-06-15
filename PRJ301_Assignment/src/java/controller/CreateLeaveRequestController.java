/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.LeaveRequestDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LeaveRequest;
import model.User;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "CreateLeaveRequestController", urlPatterns = {"/CreateLeaveRequestController"})
public class CreateLeaveRequestController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String title = request.getParameter("title");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String reason = request.getParameter("reason");

        try {
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setUserID(user.getUserID());
            leaveRequest.setTitle(title);
            leaveRequest.setFromDate(Date.valueOf(fromDate));
            leaveRequest.setToDate(Date.valueOf(toDate));
            leaveRequest.setReason(reason);
            leaveRequest.setStatus("Inprogress");

            LeaveRequestDBContext db = new LeaveRequestDBContext();
            db.insert(leaveRequest);
            response.sendRedirect("ListLeaveRequestController");
        } catch (Exception e) {
            request.setAttribute("error", "Invalid data");
            request.getRequestDispatcher("createLeaveRequest.jsp").forward(request, response);
        }
    }
}