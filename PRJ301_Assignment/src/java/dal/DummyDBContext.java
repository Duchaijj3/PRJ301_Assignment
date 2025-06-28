/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Dummy;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p14s
 */
public class DummyDBContext extends DBContext<Dummy> {

    @Override
    public ArrayList<Dummy> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Dummy get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Dummy model) {
        try {
            String sql = "INSERT INTO [Dummy]\n"
                    + "           ([dname]\n"
                    + "           ,[createdby])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getName());
            stm.setInt(2, model.getCreatedby().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DummyDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                if(!connection.isClosed())
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DummyDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

    }

    @Override
    public void update(Dummy model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Dummy model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
