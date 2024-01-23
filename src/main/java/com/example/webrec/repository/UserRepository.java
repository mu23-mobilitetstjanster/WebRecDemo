package com.example.webrec.repository;

import java.sql.*;

public class UserRepository {

  private Connection conn;

  public UserRepository() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WebRec?createDatabaseIfNotExist=true", "root", "");

      conn.prepareStatement("DROP TABLE IF EXISTS users").execute();
      conn.prepareStatement("CREATE TABLE users ("
              + "id int(11) AUTO_INCREMENT PRIMARY KEY,"
              + "username varchar(32) UNIQUE,"
              + "password varchar(64))").execute();

      create("Greta", "123");
      create("Bob", "bob");
      create("Bob", "bob");

      System.out.println("Create database");
    } catch (SQLException | ClassNotFoundException e) {
      System.out.println(e.getMessage());
      throw new RuntimeException(e);
    }
  }


  public boolean create(String username, String password) {

    try {
      String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
      PreparedStatement pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, username);
      pstmt.setString(2, password);

      pstmt.execute();
    } catch(SQLIntegrityConstraintViolationException e) { // Constraint unique username was violated
      return false;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return true;
  }

  public String findPassword(String username) {
    String password;

    try {
      String sql = "SELECT password FROM users WHERE username=?";
      PreparedStatement pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, username);

      ResultSet rs = pstmt.executeQuery();

      if(rs.next()) {
        password = rs.getString("password");
      } else {
        password = null;
      }

    } catch(SQLException e) {
      throw new RuntimeException(e);
    }

    return password;
  }
}
