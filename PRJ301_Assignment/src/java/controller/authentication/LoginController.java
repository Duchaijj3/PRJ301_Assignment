/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dal.AccountDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Account;

/**
 *
 * @author p14s
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        AccountDBContext db = new AccountDBContext();
        Account account = db.get(username, password);
        if(account!=null)
        {
            HttpSession session = req.getSession();
            session.setAttribute("account", account);
            resp.getWriter().println(account.getDisplayname() + " logged successful!");
        }
        else
        {
            HttpSession session = req.getSession();
            session.setAttribute("account", null);
            resp.getWriter().println("login failed!");
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/authentication/login.jsp").forward(req, resp);
    
    }
    
}
