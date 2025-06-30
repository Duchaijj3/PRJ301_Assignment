/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.dummy;

import controller.authentication.BaseRBACController;
import controller.authentication.BaseRequiredAuthenticationController;
import dal.DummyDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Account;
import model.Dummy;

/**
 *
 * @author p14s
 */
public class InsertController extends BaseRBACController  {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        Dummy d = new Dummy();
        d.setName(req.getParameter("name"));
        d.setCreatedby(account);
        DummyDBContext db = new DummyDBContext();
        db.insert(d);
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp,Account account) throws ServletException, IOException {
        req.getRequestDispatcher("../view/dummy/insert.jsp").forward(req, resp);
    }
    
}
