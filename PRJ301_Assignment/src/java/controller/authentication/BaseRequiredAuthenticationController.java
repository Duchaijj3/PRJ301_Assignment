/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

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
public abstract class BaseRequiredAuthenticationController
extends HttpServlet
{
    private Account getAuthenticated(HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        Account account = (Account)session.getAttribute("account");
        return account;
    }

    
    protected abstract void doPost(HttpServletRequest req, 
            HttpServletResponse resp, Account account) throws ServletException, IOException;
    protected abstract void doGet(HttpServletRequest req, 
            HttpServletResponse resp, Account account) throws ServletException, IOException;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //check authenticated
        Account account = getAuthenticated(req);
        if(account != null)
        {
            //do business logic
            doPost(req, resp, account);
        }
        else
        {
            resp.sendError(403, "You do not have right to access this feature!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //check authenticated
         Account account = getAuthenticated(req);
        if(account != null)
        {
            //do business logic
            doGet(req, resp, account);
        }
        else
        {
            resp.sendError(403, "You do not have right to access this feature!");
        }
    }
    
}
