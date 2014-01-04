package com.imranzahid.events.hook;

import com.britesnow.snow.web.db.hibernate.HibernateSessionInViewHandler;
import com.britesnow.snow.web.hook.AppPhase;
import com.britesnow.snow.web.hook.annotation.WebApplicationHook;
import com.google.inject.Singleton;
import com.imranzahid.events.dao.UserDao;
import com.imranzahid.events.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@Singleton
public class SeedDataHooks {
  private static Logger log = LoggerFactory.getLogger(SeedDataHooks.class);
  /**
   * This will be called to see the database (for demo only)
   *
   * @param userDao will be injected by Snow with the Guice binding
   * @param inView will be injected by Snow with the Guice binding (needed to open the connection for this thread to use
   *               daoHelper)
   */
  @WebApplicationHook(phase = AppPhase.INIT)
  public void seedStore(UserDao userDao, HibernateSessionInViewHandler inView) {
    inView.openSessionInView();
    if (userDao.findUserByEmail("imranzahid@gmail.com") == null) {
      User user = userDao.save(
          new User("imranzahid@gmail.com", "compaq", "Imran", "Zahid", UUID.randomUUID().toString()));
      if (user != null) {
        log.info("A new user has been inserted with ID: " + user.getId());
      }
      else {
        log.error("Unable to insert a new user");
      }
    }
    inView.closeSessionInView();
  }

  /*
  @WebApplicationHook(phase = AppPhase.SHUTDOWN, on = On.BEFORE)
  public void shutdownDb(HibernateSessionInViewHandler inView, HibernateDaoHelper daoHelper) {
    try {
      inView.openSessionInView();
      daoHelper.getSession().doWork(new Work() {
        public void execute(Connection con) throws SQLException {
          con.prepareStatement("shutdown compact").execute();
        }
      });
      inView.closeSessionInView();
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }*/
}
