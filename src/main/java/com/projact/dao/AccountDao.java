package com.projact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.projact.config.ConnectionUtil;
import com.projact.model.Account;

public class AccountDao implements DaoContract<Account, Integer>{

    @Override
    public List<String> findAllUsername() {
        try(Connection conn = ConnectionUtil.connect()){
            String sql = "select username from accounts order by id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<String> list = new ArrayList<>();
            while(rs.next()) {
                list.add(rs.getString("username"));
            }
            return list;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<Account> findAll() {
        try(Connection conn = ConnectionUtil.connect()){
            String sql = "select * from accounts";
            PreparedStatement ps = conn.prepareStatement(sql);
           // ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Account> list = new ArrayList<>();
            while (rs.next()) {
              list.add (new Account(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),
                  rs.getString(5), rs.getDouble(6)));
          }
            return list;
      }catch(SQLException e) {
          e.printStackTrace();
      }
      return null;
  }
//
////    @Override
////    public Account findByBoolean(boolean b) {
////        // TODO Auto-generated method stub
////        return null;
////    }

    @Override
    public Account findByString(String s) {
        try(Connection conn = ConnectionUtil.connect()){
            String sql = "select * from accounts where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getDouble(6));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //@Override
    public Account deleteByString(String s) {
        try(Connection conn = ConnectionUtil.connect()){
            String sql = "delete from accounts where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.execute();
            return null;
            
        
        }catch(SQLException e) {
            e.printStackTrace();
            
    }
        return null;

               
}

    @Override
    public Account insert(Account t) {
        try(Connection conn = ConnectionUtil.connect()){
            String sql = "insert into accounts (First_name, Last_name, username, password, balance)"
                + " values (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setInt(1, t.getId());
            ps.setString(1, t.getFirst_name());
            ps.setString(2, t.getLast_name());
            ps.setString(3, t.getUsername());
            ps.setString(4, t.getPassword());
            ps.setDouble(5, t.getBalance());
            ps.executeUpdate();
            return (findByString(t.getUsername()));
           }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    @Override
    public Account update(Account t, double amount) {
        try(Connection conn = ConnectionUtil.connect()){
            String sql = "update accounts set \"balance\" = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setInt(2, t.getId());
            ps.executeUpdate();
           }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public Account update(Account t) {
//        // TODO Auto-generated method stub
//        return null;
//    }



    @Override
    public Account findById(Integer id) {
      // TODO Auto-generated method stub
      return null;
    }


  //  public Account update(double balance) {
      // TODO Auto-generated method stub
     // return null;
   // }

//    @Override
//    public Account findByString(String s) {
//      // TODO Auto-generated method stub
//      return null;
//    }



}
