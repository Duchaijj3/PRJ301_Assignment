/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.accesscontrol.Role;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.accesscontrol.Feature;

/**
 *
 * @author p14s
 */
public class RoleDBContext extends DBContext<Role> {

    public ArrayList<Role> getByAccount(int aid) {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT r.rid,r.rname,f.fid,f.description,f.entrypoint FROM \n"
                    + "Account a\n"
                    + "	LEFT JOIN Account_Role ar ON ar.aid = a.aid\n"
                    + "	LEFT JOIN [Role] r ON r.rid = ar.rid\n"
                    + "	LEFT JOIN Role_Feature rf ON rf.rid = r.rid\n"
                    + "	LEFT JOIN Feature f ON f.fid = rf.fid\n"
                    + "WHERE \n"
                    + "a.aid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, aid);
            ResultSet rs = stm.executeQuery();
            
            Role temp = new Role();
            temp.setId(-1);
            
            while(rs.next())
            {
                int rid = rs.getInt("rid");
                if(rid != temp.getId())
                {
                    temp = new Role();
                    temp.setId(rid);
                    temp.setName(rs.getString("rname"));
                    roles.add(temp);
                }
                
                //read new feature
                int fid = rs.getInt("fid");
                if(fid != 0)
                {
                    Feature f = new Feature();
                    f.setId(fid);
                    f.setDescription(rs.getString("description"));
                    f.setEntrypoint(rs.getString("entrypoint"));
                    temp.getFeatures().add(f);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                if(!connection.isClosed())
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return roles;
    }

    @Override
    public ArrayList<Role> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Role get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Role model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Role model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Role model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
