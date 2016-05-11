package weishihhsun.models;

import java.util.Random;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Repository
public class UserJDBCTemplate implements UserDao {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected JdbcTemplate jdbc;

    public User getUserById(String id) {
        try{
            return jdbc.queryForObject("SELECT * FROM user WHERE user_id=? LIMIT 1", userMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final RowMapper<User> userMapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserId(rs.getString("user_id"));
            user.setDeviceId(rs.getString("device_id"));
            user.setTwitterId(rs.getString("twitter_id"));
            return user;
        }
    };


    public String createUser(String userid, String deviceid, String twitterid)
    {
        String resp = "";
       try {
            String SQL = "INSERT INTO user (user_id, device_id, twitter_id) values (?, ?, ?)";

            int succ = jdbc.update(SQL, userid, deviceid, twitterid);
            if(succ == 1 ) resp = userid;
            return resp;

        }catch (DataAccessException ex) {
           log.error("Get createUser error :" + ex);
           return resp;
       }
    }

    public boolean updateUserId(String userid, long id)
    {
        try {
            String SQL = "UPDATE user set user_id=? WHERE id=?";

            int succ = jdbc.update(SQL, userid, id);
            if(succ == 1 ) {
                log.info("updateUserId OK userid = " + userid + " id = " + id );
                return true;
            }
            log.info("updateUserId Error userid = " + userid + " id = " + id );
            return false;

        }catch (DataAccessException ex) {
            log.error("Get updateUserId exception :" + ex);
            return false;
        }
    }
}
