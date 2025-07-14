/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.rfl;

import controller.authentication.BaseRBACController;
import dal.RequestForLeaveDBContext;
import model.Account;
import model.RequestForLeave;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ManageController extends BaseRBACController {

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            RequestForLeaveDBContext db = new RequestForLeaveDBContext();
            RequestForLeave rfl = db.get(id); // Cần triển khai hàm get(int id) trong DBContext
            req.setAttribute("r", rfl);
            req.getRequestDispatcher("../view/rfl/process.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("list");
        }
    }

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String decision = req.getParameter("decision");
        String processreason = req.getParameter("processreason");

        if (idParam != null && decision != null && processreason != null) {
            int id = Integer.parseInt(idParam);
            int status = "yes".equals(decision) ? 1 : 2; // 1: Approved, 2: Rejected
            RequestForLeaveDBContext db = new RequestForLeaveDBContext();
            db.updateStatus(id, status, account.getId(), processreason); // Cần sửa hàm updateStatus để nhận processreason
        }
        resp.sendRedirect("list");
    }
}