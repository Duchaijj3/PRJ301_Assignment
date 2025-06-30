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
        Date from = Date.valueOf(req.getParameter("from"));
        Date to = Date.valueOf(req.getParameter("to"));
        RequestForLeave rfl = new RequestForLeave();
        rfl.setTitle(title);
        rfl.setReason(reason);
        rfl.setFrom(from);
        rfl.setTo(to);
        rfl.setCreatedby(account);
        
        RequestForLeaveDBContext db = new RequestForLeaveDBContext();
        db.insert(rfl);
        System.out.println("Inserted!");
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        req.getRequestDispatcher("../view/rfl/create.jsp").forward(req, resp);
    }
    
}
