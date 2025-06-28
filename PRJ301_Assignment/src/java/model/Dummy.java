/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author p14s
 */
public class Dummy extends IModel {
    private String name;
    private Account createdby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Account createdby) {
        this.createdby = createdby;
    }
    
}
