/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;

/**
 *
 * @author p14s
 */
public class RequestForLeave extends IModel {
   
    private String title;
    private String reason;
    private Date from;
    private Date to;
    private int status;
    private Account createdby;
    private Account processedby;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Account getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Account createdby) {
        this.createdby = createdby;
    }

    public Account getProcessedby() {
        return processedby;
    }

    public void setProcessedby(Account processedby) {
        this.processedby = processedby;
    }
    
}
