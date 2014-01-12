package com.imranzahid.events.test.dao;

import com.britesnow.snow.testsupport.SnowTestSupportNG;
import com.britesnow.snow.util.MapUtil;
import com.britesnow.snow.web.db.hibernate.HibernateSessionInViewHandler;
import com.britesnow.snow.web.db.hibernate.SnowHibernateException;
import com.imranzahid.events.dao.RelationshipDao;
import com.imranzahid.events.entity.Relationship;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Random;

/**
 * @author Imran Zahid
 *         date 1/5/14
 */
public class RelationshipDaoTestNG extends SnowTestSupportNG {
  private static HibernateSessionInViewHandler inView;
  private static RelationshipDao relationshipDao;
  private static final String relationshipName;
  static {
    StringBuilder randomNumber = new StringBuilder(13).append("Relationship");
    Random random = new Random();
    while (randomNumber.length() < 10) {
      randomNumber.append(random.nextInt());
    }
    relationshipName = randomNumber.toString();
  }

  @BeforeClass
  public static void initTestClass() throws Exception {
    // Here we override one property to use the test DB
    Map props = MapUtil.mapIt("hibernate.connection.url", "jdbc:mysql://localhost:3306/event_test");
    SnowTestSupportNG.initWebApplication("src/main/webapp", props);

    inView = appInjector.getInstance(HibernateSessionInViewHandler.class);
    relationshipDao = appInjector.getInstance(RelationshipDao.class);
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

  @Test(groups = {"relationship", "fail"},
      expectedExceptions = SnowHibernateException.class)
  public void trySaveEmptyRelationship() {
    Relationship newRelationship = new Relationship();
    relationshipDao.save(newRelationship);
  }

  @Test(groups = {"relationship", "fail"},
      dependsOnMethods = "trySaveEmptyRelationship",
      expectedExceptions = SnowHibernateException.class)
  public void trySaveRelationshipWithoutName() {
    Relationship newRelationship = new Relationship();
    newRelationship.setActive(true);
    relationshipDao.save(newRelationship);
  }

  @Test(groups = {"relationship"},
      dependsOnMethods = "trySaveRelationshipWithoutName")
  public void saveRelationship() {
    Relationship newRelationship = new Relationship();
    newRelationship.setName(relationshipName);
    newRelationship.setActive(true);
    relationshipDao.save(newRelationship);
    Assert.assertNotNull(newRelationship.getId(), "ID should not have been null");
  }

  @Test(groups = {"relationship"},
      dependsOnMethods = "saveRelationship")
  public void findByName() {
    Relationship mainRelationship = relationshipDao.findRelationshipByName(relationshipName);
    Assert.assertNotNull(mainRelationship, "There should be a relationship with name'" + relationshipName + "'");
  }

  @Test(groups = {"relationship"},
      dependsOnMethods = "findByName")
  public void findById() {
    Relationship mainRelationship = relationshipDao.get(1L);
    Assert.assertNotNull(mainRelationship, "There should be a relationship with ID #1");
  }

  @Test(groups = {"relationship"},
      dependsOnMethods = "findByName")
  public void listAll() {
    Map<Long, Relationship> all = relationshipDao.listAll();
    Assert.assertNotNull(all, "Map should not have been null");
    Assert.assertTrue(all.size() > 0, "Size of the map should be greater than one");
  }
}
