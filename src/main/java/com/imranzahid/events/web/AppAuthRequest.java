package com.imranzahid.events.web;

import com.britesnow.snow.web.RequestContext;
import com.britesnow.snow.web.auth.AuthRequest;
import com.britesnow.snow.web.auth.AuthToken;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.imranzahid.events.dao.UserDao;
import com.imranzahid.events.entity.User;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@Singleton
public class AppAuthRequest implements AuthRequest<User> {
  @Inject UserDao userDao;

  @Override
  public AuthToken<User> authRequest(RequestContext rc) {
    // Note for this sample app, we store the user in the session,
    // but for production application, use stateless authentication mechanism for
    // better scalability.

    User user = (User) rc.getReq().getSession().getAttribute(User.class.getSimpleName());
    if (user == null) { //find user by cookie
      Long id = rc.getCookie("user-id", Long.class, -1L);
      if (id > 0) {
        user = userDao.get(id);
        // add user to session
        rc.getReq().getSession().setAttribute("user", user);
      }
    }
    if (user != null) {
      AuthToken<User> authToken = new AuthToken<>();
      authToken.setUser(user);
      return authToken;
    }
    return null;
  }
}
