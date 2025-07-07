/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.rfl;

import controller.authentication.BaseRBACController;
import dal.RequestForLeaveDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.RequestForLeave;

/**
 *
 * @author p14s
 */
public class ListController extends BaseRBACController {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        RequestForLeaveDBContext db = new RequestForLeaveDBContext();
        ArrayList<RequestForLeave> rfls = db.list(account.getId());
        req.setAttribute("rfls", rfls);
        req.getRequestDispatcher("../view/rfl/list.jsp").forward(req, resp);
    }
    
    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }
   
    
}
//public class ListController extends BaseRBACController {
//
//    @Override
//    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
//        // Xử lý POST nếu cần (ví dụ: lọc đơn)
//    }
//
//    @Override
//    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
//        RequestForLeaveDBContext db = new RequestForLeaveDBContext();
//        int aid = account.getId();
//        req.setAttribute("rfls", db.list(aid));
//        req.getRequestDispatcher("view/rfl/list.jsp").forward(req, resp);
//    }
//}