/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author p14s
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RequestForLeave;
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
                    + "    SELECT e.eid, e.ename, e.did, e.bossid\n"
                    + "    FROM Employee e INNER JOIN Account_Employee ae ON e.eid = ae.eid\n"
                    + "                   INNER JOIN Account a ON a.aid = ae.aid\n"
                    + "    WHERE a.aid = ?\n"
                    + "    UNION ALL\n"
                    + "    SELECT e.eid, e.ename, e.did, e.bossid\n"
                    + "    FROM Employee e\n"
                    + "    INNER JOIN RecursiveCTE r ON e.bossid = r.eid\n"
                    + ")\n"
                    + "SELECT rfl.rid, rfl.title, rfl.reason, rfl.[from], rfl.[to], e.eid, e.ename, "
                    + "a.username AS [createdbyname], rfl.createdby, rfl.[status], "
                    + "ap.username AS [processbyname], rfl.processedby\n"
                    + "FROM RecursiveCTE e INNER JOIN Account_Employee ae ON e.eid = ae.eid\n"
                    + "                   INNER JOIN Account a ON a.aid = ae.aid\n"
                    + "                   INNER JOIN RequestForLeave rfl ON rfl.createdby = a.aid\n"
                    + "                   LEFT JOIN Account ap ON ap.aid = rfl.processedby\n"
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

                int processedById = rs.getInt("processedby");
                if (processedById > 0) {
                    Account processedby = new Account();
                    processedby.setUsername(rs.getString("processbyname"));
                    processedby.setId(processedById);
                    rfl.setProcessedby(processedby);
                }
                rfls.add(rfl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestForLeaveDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RequestForLeave get(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(RequestForLeave model) {
        try {
            String sql = "INSERT INTO RequestForLeave (title, [from], [to], reason, status, createdby) VALUES (?, ?, ?, ?, 0, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getTitle());
            stm.setDate(2, model.getFrom());
            stm.setDate(3, model.getTo());
            stm.setString(4, model.getReason());
            stm.setInt(5, model.getCreatedby().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RequestForLeaveDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateStatus(int id, int status, int processedBy) {
    try {
        String sql = "UPDATE RequestForLeave SET status = ?, processedby = ? WHERE rid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, status);
        stm.setInt(2, processedBy);
        stm.setInt(3, id);
        stm.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(RequestForLeaveDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @Override
    public void update(RequestForLeave model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(RequestForLeave model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}