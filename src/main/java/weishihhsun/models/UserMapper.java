package weishihhsun.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        if(!rs.next()) return null;

        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserId(rs.getString("user_id"));
        user.setDeviceId(rs.getString("device_id"));
        user.setTwitterId(rs.getString("twitter_id"));

        return user;
    }
}