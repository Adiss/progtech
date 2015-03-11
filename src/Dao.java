/**
 * Created by Jakab on 2015.03.10..
 */

import java.sql.*;

public class Dao {

    private static String host;
    private static String username;
    private static String password;

    Connection conn = null;
    PreparedStatement prepStmt = null;
    ResultSet rs = null;

    Dao(String host, String username, String password){
        this.host = host;
        this.username = username;
        this.password = password;
    }

    protected Boolean checkUserByEmail(String email){
        Boolean exist = false;
        String selectStatement = "SELECT * FROM users WHERE email = ?;";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host, username, password);
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, email);
            rs = prepStmt.executeQuery();
            if(rs.next()) exist = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return exist;
    }

    protected Boolean checkUserByName(String name){
        Boolean exist = false;
        String selectStatement = "SELECT * FROM users WHERE username = ?;";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host, username, password);
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, name);
            rs = prepStmt.executeQuery();
            if(rs.next()) exist = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return exist;
    }

    protected Boolean checkUserByPassword(String pw){
        Boolean exist = false;
        String selectStatement = "SELECT * FROM users WHERE password = ?;";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host, username, password);
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, pw);
            rs = prepStmt.executeQuery();
            if(rs.next()) exist = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return exist;
    }

    protected void insertUser(String rusername, String rpassword, String remail){
        String insertStatement = "INSERT INTO users (username, password, email) VALUES(?, ?, ?);";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host, username, password);
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, rusername);
            prepStmt.setString(2, rpassword);
            prepStmt.setString(3, remail);
            prepStmt.executeUpdate();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (prepStmt != null) {
                prepStmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
