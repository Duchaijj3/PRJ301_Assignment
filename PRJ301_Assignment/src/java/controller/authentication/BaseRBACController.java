/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dal.RoleDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.accesscontrol.Feature;
import model.accesscontrol.Role;

/**
 *
 * @author p14s
 */
public abstract class BaseRBACController extends BaseRequiredAuthenticationController {

    private boolean isGrantedAccessControl(HttpServletRequest req, String c_entrypoint, Account account)
    {
        //first time - request to get access control
        if(account.getRoles().size() ==0)
        {
            RoleDBContext db = new RoleDBContext();
            ArrayList<Role> roles = db.getByAccount(account.getId());
            account.setRoles(roles);
            req.getSession().setAttribute("account", account);
        }
        //send time check in session only
        for (Role role : account.getRoles()) {
            for (Feature feature : role.getFeatures()) {
                if(feature.getEntrypoint().equals(c_entrypoint))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected abstract void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException;
    protected abstract void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String c_entrypoint = req.getServletPath();
        if(isGrantedAccessControl(req, c_entrypoint, account))
        {
            //granted access control
            processPost(req, resp, account);
        }
        else
        {
            resp.sendError(403, "You do not have this permission!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    String c_entrypoint = req.getServletPath();
        if(isGrantedAccessControl(req, c_entrypoint, account))
        {
            //granted access control
            processGet(req, resp, account);
        }
        else
        {
            resp.sendError(403, "You do not have this permission!");
        }
    }
    
}
