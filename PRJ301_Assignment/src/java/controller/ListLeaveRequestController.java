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
import java.util.List;

@WebServlet(name = "ListLeaveRequestController", urlPatterns = {"/ListLeaveRequestController"})
public class ListLeaveRequestController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        LeaveRequestDBContext db = new LeaveRequestDBContext();
        List<LeaveRequest> leaveRequests = db.listByUserOrSubordinates(user.getUserID());
        request.setAttribute("leaveRequests", leaveRequests);
        request.getRequestDispatcher("listLeaveRequest.jsp").forward(request, response);
    }
}