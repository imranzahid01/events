package com.imranzahid.events.test.dao;

import com.britesnow.snow.testsupport.SnowTestSupport;
import com.britesnow.snow.util.MapUtil;
import com.britesnow.snow.web.db.hibernate.HibernateSessionInViewHandler;
import com.britesnow.snow.web.db.hibernate.SnowHibernateException;
import com.imranzahid.events.dao.UserDao;
import com.imranzahid.events.entity.User;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest extends SnowTestSupport {
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
    SnowTestSupport.initWebApplication("src/main/webapp", props);

    inView = appInjector.getInstance(HibernateSessionInViewHandler.class);
    inView.openSessionInView();
    userDao = appInjector.getInstance(UserDao.class);
  }

  @AfterClass
  public static void destroyTestClass() {
    inView.afterActionProcessing();
    inView.closeSessionInView();
    inView = null;
  }

  @Test(expected = SnowHibernateException.class)
  public void trySaveEmptyUser() {
    User newUser = new User();
    userDao.save(newUser);
  }

  @Rule public ExpectedException exception = ExpectedException.none();

  @Test
  public void trySaveUserWithoutApi() {
    User newUser = new User();
    newUser.setUserEmail(testEmail);
    newUser.setPassword("123");
    exception.expect(SnowHibernateException.class);
    exception.expectMessage("Column 'api' cannot be null");
    userDao.save(newUser);
  }

  @Test
  public void trySaveUserWithoutEmail() {
    User newUser = new User();
    newUser.setApi(UUID.randomUUID().toString());
    newUser.setPassword("123");
    exception.expect(SnowHibernateException.class);
    exception.expectMessage("Column 'useremail' cannot be null");
    userDao.save(newUser);
  }

  @Test
  public void trySaveUserWithoutPassword() {
    User newUser = new User();
    newUser.setUserEmail(testEmail);
    newUser.setApi(UUID.randomUUID().toString());
    exception.expect(SnowHibernateException.class);
    exception.expectMessage("Column 'password' cannot be null");
    userDao.save(newUser);
  }

  @Test
  public void saveUser() {
    User newUser = new User();
    newUser.setUserEmail(testEmail);
    newUser.setApi(UUID.randomUUID().toString());
    newUser.setPassword("123");
    userDao.save(newUser);
    Assert.assertNotNull("ID should not have been null", newUser.getId());
  }

  @Test
  public void findByEmail() {
    String email = "imranzahid@gmail.com";
    User mainUser = userDao.findUserByEmail(email);
    Assert.assertNotNull("There should be a user with email '" + email + "'", mainUser);
  }

  @Test
  public void findById() {
    User mainUser = userDao.get(1L);
    Assert.assertNotNull("There should be a user with ID #1", mainUser);
  }
}
