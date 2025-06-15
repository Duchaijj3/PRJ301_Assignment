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

@WebServlet(name = "ProcessLeaveRequestController", urlPatterns = {"/ProcessLeaveRequestController"})
public class ProcessLeaveRequestController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int requestID = Integer.parseInt(request.getParameter("id"));
        LeaveRequestDBContext db = new LeaveRequestDBContext();
        LeaveRequest leaveRequest = db.getById(requestID);
        request.setAttribute("leaveRequest", leaveRequest);
        request.getRequestDispatcher("processLeaveRequest.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int requestID = Integer.parseInt(request.getParameter("requestID"));
        String action = request.getParameter("action");
        String processReason = request.getParameter("processReason");

        LeaveRequestDBContext db = new LeaveRequestDBContext();
        try {
            db.processRequest(requestID, user.getUserID(), action, processReason);
            response.sendRedirect("ListLeaveRequestController");
        } catch (SQLException e) {
            request.setAttribute("error", "Failed to process request");
            request.getRequestDispatcher("processLeaveRequest.jsp").forward(request, response);
        }
    }
}