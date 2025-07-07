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
public class DetailsController extends BaseRBACController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        // Không xử lý POST
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            RequestForLeaveDBContext db = new RequestForLeaveDBContext();
            // Giả định get trả về một RequestForLeave
            req.setAttribute("rfl", db.get(id)); // Cần triển khai get trong DBContext
            req.getRequestDispatcher("view/rfl/details.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("list");
        }
    }
}