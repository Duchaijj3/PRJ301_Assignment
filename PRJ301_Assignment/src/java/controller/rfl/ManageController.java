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

/**
 *
 * @author sonnt
 */
public class ManageController extends BaseRBACController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        // Không xử lý POST trực tiếp, dùng GET cho duyệt
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String decision = req.getParameter("decision");

        if (idParam != null && decision != null) {
            int id = Integer.parseInt(idParam);
            int status = "yes".equals(decision) ? 1 : 2; // 1: Approved, 2: Rejected
            RequestForLeaveDBContext db = new RequestForLeaveDBContext();
            db.updateStatus(id, status, account.getId()); // Giả định có phương thức updateStatus
            resp.sendRedirect("list"); // Quay lại danh sách sau khi duyệt
        } else {
            resp.sendRedirect("list"); // Nếu không có tham số, quay về danh sách
        }
    }
}