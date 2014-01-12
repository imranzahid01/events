package com.imranzahid.events.test.dao;

import com.britesnow.snow.testsupport.SnowTestSupportNG;
import com.britesnow.snow.util.MapUtil;
import com.britesnow.snow.web.db.hibernate.HibernateSessionInViewHandler;
import com.britesnow.snow.web.db.hibernate.SnowHibernateException;
import com.imranzahid.events.dao.CategoryDao;
import com.imranzahid.events.entity.Category;
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
public class CategoryDaoTestNG extends SnowTestSupportNG {
  private static HibernateSessionInViewHandler inView;
  private static CategoryDao categoryDao;
  private static final String categoryName;
  static {
    StringBuilder randomNumber = new StringBuilder(13).append("Category");
    Random random = new Random();
    while (randomNumber.length() < 10) {
      randomNumber.append(random.nextInt());
    }
    categoryName = randomNumber.toString();
  }

  @BeforeClass
  public static void initTestClass() throws Exception {
    // Here we override one property to use the test DB
    Map props = MapUtil.mapIt("hibernate.connection.url", "jdbc:mysql://localhost:3306/event_test");
    SnowTestSupportNG.initWebApplication("src/main/webapp", props);

    inView = appInjector.getInstance(HibernateSessionInViewHandler.class);
    categoryDao = appInjector.getInstance(CategoryDao.class);
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

  @Test(groups = {"category", "fail"},
      expectedExceptions = SnowHibernateException.class)
  public void trySaveEmptyCategory() {
    Category newCategory = new Category();
    categoryDao.save(newCategory);
  }

  @Test(groups = {"category", "fail"},
      dependsOnMethods = "trySaveEmptyCategory",
      expectedExceptions = SnowHibernateException.class)
  public void trySaveCategoryWithoutName() {
    Category newCategory = new Category();
    newCategory.setActive(true);
    categoryDao.save(newCategory);
  }

  @Test(groups = {"category"},
      dependsOnMethods = "trySaveCategoryWithoutName")
  public void saveCategory() {
    Category newCategory = new Category();
    newCategory.setName(categoryName);
    newCategory.setActive(true);
    categoryDao.save(newCategory);
    Assert.assertNotNull(newCategory.getId(), "ID should not have been null");
  }

  @Test(groups = {"category"},
      dependsOnMethods = "saveCategory")
  public void findByName() {
    Category mainCategory = categoryDao.findCategoryByName(categoryName);
    Assert.assertNotNull(mainCategory, "There should be a category with name'" + categoryName + "'");
  }

  @Test(groups = {"category"},
      dependsOnMethods = "findByName")
  public void findById() {
    Category mainCategory = categoryDao.get(1L);
    Assert.assertNotNull(mainCategory, "There should be a category with ID #1");
  }

  @Test(groups = {"category"},
      dependsOnMethods = "findByName")
  public void listAll() {
    Map<Long, Category> all = categoryDao.listAll();
    Assert.assertNotNull(all, "Map should not have been null");
    Assert.assertTrue(all.size() > 0, "Size of the map should be greater than one");
  }
}
