package com.imranzahid.events.test.dao;

import com.britesnow.snow.testsupport.SnowTestSupportNG;
import com.britesnow.snow.util.MapUtil;
import com.britesnow.snow.web.db.hibernate.HibernateSessionInViewHandler;
import com.britesnow.snow.web.db.hibernate.SnowHibernateException;
import com.imranzahid.events.dao.UserDao;
import com.imranzahid.events.entity.User;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@Test
public class UserDaoTestNG extends SnowTestSupportNG {
  private static HibernateSessionInViewHandler inView;
  private static UserDao userDao;
  private static final String testEmail;
  static {
    StringBuilder randomNumber = new StringBuilder(13).append("test");
    Random random = new Random();
    while (randomNumber.length() < 10) {
      randomNumber.append(random.nextInt());
    }
    testEmail = randomNumber.append("@test.com").toString();
  }

  @BeforeClass
  public static void initTestClass() throws Exception {
    // Here we override one property to use the test DB
    Map props = MapUtil.mapIt("hibernate.connection.url", "jdbc:mysql://localhost:3306/event_test");
    SnowTestSupportNG.initWebApplication("src/main/webapp", props);

    inView = appInjector.getInstance(HibernateSessionInViewHandler.class);
    userDao = appInjector.getInstance(UserDao.class);
  }

  @AfterClass
  public static void destroyTestClass() {
    inView.afterActionProcessing();
    inView.closeSessionInView();
    inView = null;
  }

  @BeforeMethod
  public void cleanHibernateSession() {
    if (inView != null) {
      inView.openSessionInView();
    }
  }

  @Test(groups = {"user", "fail"},
      expectedExceptions = SnowHibernateException.class)
  public void trySaveEmptyUser() {
    User newUser = new User();
    userDao.save(newUser);
  }


  @Test(groups = {"user", "fail"},
      dependsOnMethods = "trySaveEmptyUser",
      expectedExceptions = SnowHibernateException.class/*,
      expectedExceptionsMessageRegExp = "Column 'api' cannot be null"*/
  )
  public void trySaveUserWithoutApi() {
    User newUser = new User();
    newUser.setUserEmail(testEmail);
    newUser.setPassword("123");
    userDao.save(newUser);
  }

  @Test(groups = {"user", "fail"},
      dependsOnMethods = "trySaveUserWithoutApi",
      expectedExceptions = SnowHibernateException.class/*,
      expectedExceptionsMessageRegExp = "Column 'useremail' cannot be null"*/
  )
  public void trySaveUserWithoutEmail() {
    User newUser = new User();
    newUser.setApi(UUID.randomUUID().toString());
    newUser.setPassword("123");
    userDao.save(newUser);
  }

  @Test(groups = {"user", "fail"},
      dependsOnMethods = "trySaveUserWithoutEmail",
      expectedExceptions = SnowHibernateException.class/*,
      expectedExceptionsMessageRegExp = "Column 'password' cannot be null"*/
  )
  public void trySaveUserWithoutPassword() {
    User newUser = new User();
    newUser.setUserEmail(testEmail);
    newUser.setApi(UUID.randomUUID().toString());
    userDao.save(newUser);
  }

  @Test(groups = {"user"},
      dependsOnMethods = "trySaveUserWithoutPassword")
  public void saveUser() {
    User newUser = new User();
    newUser.setUserEmail(testEmail);
    newUser.setApi(UUID.randomUUID().toString());
    newUser.setPassword("123");
    userDao.save(newUser);
    Assert.assertNotNull(newUser.getId(), "ID should not have been null");
  }

  @Test(groups = {"user"},
      dependsOnMethods = "saveUser")
  public void findByEmail() {
    String email = "imranzahid@gmail.com";
    User mainUser = userDao.findUserByEmail(email);
    Assert.assertNotNull(mainUser, "There should be a user with email '" + email + "'");
  }

  @Test(groups = {"user"},
      dependsOnMethods = "findByEmail")
  public void findById() {
    User mainUser = userDao.get(1L);
    Assert.assertNotNull(mainUser, "There should be a user with ID #1");
  }
}
