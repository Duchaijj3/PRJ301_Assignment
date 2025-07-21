/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.RequestForLeave;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author p14s
 */
public class RequestForLeaveDBContext extends DBContext<RequestForLeave> {

    public ArrayList<RequestForLeave> list(int aid) {
        ArrayList<RequestForLeave> rfls = new ArrayList<>();

        try {
            String sql = "WITH RecursiveCTE AS (\n"
                    + "    -- Anchor member: Start with the given employee\n"
                    + "    SELECT e.eid, e.ename, e.did, e.bossid\n"
                    + "    FROM Employee e INNER JOIN Account_Employee ae ON e.eid = ae.eid\n"
                    + "					INNER JOIN Account a ON a.aid = ae.aid\n"
                    + "    WHERE a.aid = ?  -- Replace @EmployeeId with the input employee ID\n"
                    + "\n"
                    + "    UNION ALL\n"
                    + "\n"
                    + "    -- Recursive member: Fetch subordinates\n"
                    + "    SELECT e.eid, e.ename, e.did, e.bossid\n"
                    + "    FROM dbo.Employee e\n"
                    + "    INNER JOIN RecursiveCTE r ON e.bossid = r.eid\n"
                    + ")\n"
                    + "SELECT rfl.rid,rfl.title,rfl.reason,rfl.[from], rfl.[to],e.eid,e.ename, a.username as [createdbyname],rfl.createdby,rfl.[status],ap.username as [processbyname],rfl.processedby  \n"
                    + "FROM RecursiveCTE e INNER JOIN Account_Employee ae ON e.eid = ae.eid\n"
                    + "					INNER JOIN Account a ON a.aid = ae.aid\n"
                    + "					INNER JOIN RequestForLeave rfl ON rfl.createdby = a.aid\n"
                    + "					LEFT JOIN Account ap ON ap.aid = rfl.processedby\n"
                    + "WHERE a.aid != ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, aid);
            stm.setInt(2, aid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                RequestForLeave rfl = new RequestForLeave();
                rfl.setId(rs.getInt("rid"));
                rfl.setTitle(rs.getString("title"));
                rfl.setReason(rs.getString("reason"));
                rfl.setFrom(rs.getDate("from"));
                rfl.setTo(rs.getDate("to"));
                rfl.setStatus(rs.getInt("status"));
                Account createdby = new Account();
                createdby.setUsername(rs.getString("createdbyname"));
                createdby.setId(rs.getInt("createdby"));
                rfl.setCreatedby(createdby);

                int processdbyid = rs.getInt("processedby");
                if (processdbyid > 0) {
                    Account processedby = new Account();
                    processedby.setUsername(rs.getString("processbyname"));
                    processedby.setId(rs.getInt("processedby"));
                    rfl.setProcessedby(processedby);
                }
                rfls.add(rfl);

                //rfl.rid,rfl.title,rfl.reason,rfl.[from], rfl.[to],e.eid,e.ename, 
                //a.username as [createdbyname],rfl.createdby,rfl.[status]
                //,ap.username as [processbyname],rfl.processedby
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestForLeaveDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RequestForLeaveDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rfls;
    }

    @Override
    public ArrayList<RequestForLeave> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public RequestForLeave get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(RequestForLeave model) {
        try {
            String sql = "INSERT INTO [RequestForLeave]\n"
                    + "           ([title]\n"
                    + "           ,[from]\n"
                    + "           ,[to]\n"
                    + "           ,[reason]\n"
                    + "           ,[status]\n"
                    + "           ,[createdby]\n"
                    + "           ,[processedby])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,0\n"
                    + "           ,?\n"
                    + "           ,NULL)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getTitle());
            stm.setDate(2, model.getFrom());
            stm.setDate(3, model.getTo());
            stm.setString(4, model.getReason());
            stm.setInt(5, model.getCreatedby().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RequestForLeaveDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RequestForLeaveDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(RequestForLeave model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(RequestForLeave model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
