package com.imranzahid.events.web;

import com.britesnow.snow.web.RequestContext;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.britesnow.snow.web.rest.annotation.WebGet;
import com.britesnow.snow.web.rest.annotation.WebPost;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.imranzahid.events.dao.UserDao;
import com.imranzahid.events.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@Singleton
public class LoginWebHandler {
  @SuppressWarnings("UnusedDeclaration")
  private static Logger log = LoggerFactory.getLogger(LoginWebHandler.class);
  static final String COOKIE_NAME = "user-id";
  @Inject private UserDao userDao;

  @WebPost("/api/login")
  public WebResponse login(@WebParam("userEmail") String userEmail,
                           @WebParam("userPass") String password,
                           @WebParam("userRemember") String userRemember,
                           RequestContext requestContext) {
    User user = userDao.findUserByEmail(userEmail);
    if (user != null && Strings.nullToEmpty(password).equals(user.getPassword())) {
      requestContext.getReq().getSession().setAttribute(User.class.getSimpleName(), user);
      if ("remember-me".equals(userRemember)) {
        requestContext.setCookie(COOKIE_NAME, user.getId(), true);
      }
      return WebResponse.success(user.getApi());
    }
    return WebResponse.fail("Wrong username or password");
  }

  @WebGet("/api/logoff")
  public WebResponse logoff(RequestContext requestContext) {
    requestContext.getReq().getSession().removeAttribute(User.class.getSimpleName());
    requestContext.removeCookie(COOKIE_NAME);
    return WebResponse.success();
  }
}
