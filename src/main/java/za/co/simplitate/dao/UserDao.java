package za.co.simplitate.dao;

import za.co.simplitate.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

  public int saveUser(User user) {
    int rows = 0;
    try {
      Connection connection = DBConnection.getConnection();
      PreparedStatement statement = null;
      statement = connection.prepareStatement("insert into user values(?,?,?)");
      statement.setInt(1, user.getId());
      statement.setString(1, user.getName());
      statement.setString(1, user.getEmail());
      rows = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rows;
  }
}
