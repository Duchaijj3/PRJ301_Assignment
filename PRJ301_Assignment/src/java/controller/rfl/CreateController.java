/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.rfl;

import controller.authentication.BaseRBACController;
import dal.RequestForLeaveDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import model.Account;
import java.sql.Date;

import model.RequestForLeave;

/**
 *
 * @author sonnt
 */
public class CreateController extends BaseRBACController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String title = req.getParameter("title");
        String reason = req.getParameter("reason");
        String fromStr = req.getParameter("from");
        String toStr = req.getParameter("to");

        // Kiểm tra đầu vào
        if (title == null || reason == null || fromStr == null || toStr == null ||
            title.trim().isEmpty() || reason.trim().isEmpty() || fromStr.trim().isEmpty() || toStr.trim().isEmpty()) {
            req.setAttribute("error", "All fields are required!");
            req.getRequestDispatcher("../view/rfl/create.jsp").forward(req, resp);
            return;
        }

        Date from = null;
        Date to = null;
        try {
            from = Date.valueOf(fromStr);
            to = Date.valueOf(toStr);

            // Kiểm tra ngày hợp lệ (from <= to)
            if (from.after(to)) {
                req.setAttribute("error", "From date must be before To date!");
                req.getRequestDispatcher("../view/rfl/create.jsp").forward(req, resp);
                return;
            }
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", "Invalid date format! Use YYYY-MM-DD.");
            req.getRequestDispatcher("../view/rfl/create.jsp").forward(req, resp);
            return;
        }

        RequestForLeave rfl = new RequestForLeave();
        rfl.setTitle(title);
        rfl.setReason(reason);
        rfl.setFrom(from);
        rfl.setTo(to);
        rfl.setCreatedby(account);

        RequestForLeaveDBContext db = new RequestForLeaveDBContext();
        db.insert(rfl);
        System.out.println("Inserted!");
        System.out.println("From: " + from + ", To: " + to + ", Title: " + title + ", Reason: " + reason);
        resp.sendRedirect(req.getContextPath() + "/home"); // Chuyển hướng ve home sau khi thành công
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        req.getRequestDispatcher("../view/rfl/create.jsp").forward(req, resp);
    }
    
}
