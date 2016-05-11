package weishihhsun.controllers;

import weishihhsun.models.UserInput;
import weishihhsun.models.User;
import weishihhsun.models.UserDao;
import weishihhsun.models.UserJDBCTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  protected final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private UserJDBCTemplate userMgr;


  @RequestMapping(value = "/user/hasUserId", method=RequestMethod.GET)
  @ResponseBody
  public String hasUserId(String uid)
  {
    log.info("Get hasUserId");
    if(uid.length() == 0 ) return "{\"status\":400, \"message\": uid is null }";

    try {
      User user = userMgr.getUserById(uid);
      if(user != null)
        return "{\"status\":200, \"userId\": " + uid + "}";
      else
        return "{\"status\":404, \"userId\": " + uid + ", \"message\": UserId not exist }";
    }
    catch (Exception ex) {
      return "{\"status\":500, \"message\": login server error "+ ex.toString() +") }";
    }
  }


  @RequestMapping(value = "/user/twitterLogin", method=RequestMethod.POST, headers = {"Content-type=application/json"})
  @ResponseBody
  public String twitterLogin(@RequestBody UserInput data)
  {
    log.info("Get bindTwitter tid:" + data.getTwitterId() + " udid:" + data.getDeviceId() );

    return data.getDeviceId();

  }



	
  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  @Autowired
  private UserDao userDao;
  
} // class UserController
