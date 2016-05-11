package weishihhsun.models;


import java.util.List;
import javax.sql.DataSource;


public interface UserDao {

  // Find User by UserId
  public User getUserById(String id);



} // class UserDao
