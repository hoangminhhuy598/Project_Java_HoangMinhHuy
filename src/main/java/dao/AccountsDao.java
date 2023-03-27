package dao;

import connection.MyConnection;
import model.Accounts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountsDao {
    // Dinh nghia: xem, them, sua, xoa

    public Accounts getByacc(String user, String password){
        Accounts acc= null;
        String sql=String.format("SELECT * FROM accounts WHERE user_name='%s' and pass_word='%s'",user,password);
        try{
            Connection conn= MyConnection.getConnection();
            // String sql=String.format("SELECT * FROM account WHERE user='%s' and password='%s'",user,password);
            Statement stm= conn.createStatement();
            ResultSet rs=stm.executeQuery(sql);

            if (rs.next()) {
                acc = new Accounts();
                acc.setUser_id(rs.getInt("user_id"));
                acc.setUser_name(rs.getString("user_name"));
                acc.setPass_word(rs.getString("pass_word"));

            }
            rs.close();
            stm.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return acc;
    }

    // Co the lam them phan sua xoa nhe....

}