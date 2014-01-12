package com.imranzahid.events.dao;

import com.britesnow.snow.util.MapUtil;
import com.google.inject.Singleton;
import com.imranzahid.events.entity.Category;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@Singleton
public class CategoryDao extends BaseHibernateDao<Category> {
  @Nullable
  public Category findCategoryByName(@Nonnull String categoryName) {
    return findUnique("FROM Category WHERE name = :categoryName",
        MapUtil.mapIt("categoryName", categoryName, "active", "Y"));
  }
}
