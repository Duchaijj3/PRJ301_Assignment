/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author p14s
 */
public class Employee extends IModel {
    private String name;
    private Account account;
    
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<RequestForLeave> myrfl = new ArrayList<>();

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<RequestForLeave> getMyrfl() {
        return myrfl;
    }

    public void setMyrfl(ArrayList<RequestForLeave> myrfl) {
        this.myrfl = myrfl;
    }
            

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
}
