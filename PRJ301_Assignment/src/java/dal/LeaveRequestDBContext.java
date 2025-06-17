/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.LeaveRequest;

public class LeaveRequestDBContext {
    private Connection connection;

    public LeaveRequestDBContext() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=LeaveManagement;encrypt=true;trustServerCertificate=true";
            String user = "your_username";
            String password = "your_password";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(LeaveRequest leaveRequest) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection is null");
        }
        String sql = "INSERT INTO LeaveRequests (UserID, Title, FromDate, ToDate, Reason, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, leaveRequest.getUserID());
            stmt.setString(2, leaveRequest.getTitle());
            stmt.setDate(3, leaveRequest.getFromDate());
            stmt.setDate(4, leaveRequest.getToDate());
            stmt.setString(5, leaveRequest.getReason());
            stmt.setString(6, leaveRequest.getStatus());
            stmt.executeUpdate();
        }
    }

    public List<LeaveRequest> listByUserOrSubordinates(int userID) throws SQLException {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT lr.* FROM LeaveRequests lr " +
                     "WHERE lr.UserID = ? OR lr.UserID IN (SELECT UserID FROM Users WHERE ManagerID = ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, userID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LeaveRequest lr = new LeaveRequest();
                lr.setRequestID(rs.getInt("RequestID"));
                lr.setUserID(rs.getInt("UserID"));
                lr.setTitle(rs.getString("Title"));
                lr.setFromDate(rs.getDate("FromDate"));
                lr.setToDate(rs.getDate("ToDate"));
                lr.setReason(rs.getString("Reason"));
                lr.setStatus(rs.getString("Status"));
                lr.setProcessedBy(rs.getInt("ProcessedBy"));
                lr.setProcessReason(rs.getString("ProcessReason"));
                list.add(lr);
            }
        }
        return list;
    }

    public LeaveRequest getById(int requestID) throws SQLException {
        String sql = "SELECT * FROM LeaveRequests WHERE RequestID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, requestID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LeaveRequest lr = new LeaveRequest();
                lr.setRequestID(rs.getInt("RequestID"));
                lr.setUserID(rs.getInt("UserID"));
                lr.setTitle(rs.getString("Title"));
                lr.setFromDate(rs.getDate("FromDate"));
                lr.setToDate(rs.getDate("ToDate"));
                lr.setReason(rs.getString("Reason"));
                lr.setStatus(rs.getString("Status"));
                lr.setProcessedBy(rs.getInt("ProcessedBy"));
                lr.setProcessReason(rs.getString("ProcessReason"));
                return lr;
            }
        }
        return null;
    }
public void processRequest(int requestID, int processedBy, String action, String processReason) throws SQLException {
    String sql = "UPDATE LeaveRequests SET Status = ?, ProcessedBy = ?, ProcessReason = ? WHERE RequestID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, action);
        stmt.setInt(2, processedBy);
        stmt.setString(3, processReason);
        stmt.setInt(4, requestID);
        stmt.executeUpdate();
    }
}

    public Map<Integer, List<Boolean>> getAgenda(int departmentID, Date fromDate, Date toDate) throws SQLException {
        Map<Integer, List<Boolean>> agenda = new HashMap<>();
        String sql = "SELECT lr.UserID, lr.FromDate, lr.ToDate FROM LeaveRequests lr " +
                     "JOIN Users u ON lr.UserID = u.UserID " +
                     "WHERE u.DepartmentID = ? AND lr.Status = 'Approved' " +
                     "AND lr.FromDate <= ? AND lr.ToDate >= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, departmentID);
            stmt.setDate(2, toDate);
            stmt.setDate(3, fromDate);
            ResultSet rs = stmt.executeQuery();
            // Logic to build agenda map (userID -> list of working status)
            // Simplified: Add actual date range logic here
            return agenda;
        }
    }
}