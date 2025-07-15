/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.agenda;

import controller.authentication.BaseRBACController;
import dal.RequestForLeaveDBContext;
import model.Account;
import model.RequestForLeave;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AgendaController extends BaseRBACController {
@Override
protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    RequestForLeaveDBContext db = new RequestForLeaveDBContext();
    ArrayList<RequestForLeave> rfls = db.listByDepartmentOfAccount(account.getId());
    req.setAttribute("rfls", rfls);
    req.getRequestDispatcher("../view/agenda/list.jsp").forward(req, resp);
}
    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processGet(req, resp, account); // Thường chỉ cần GET để xem lịch
    }
}