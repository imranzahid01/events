package com.imranzahid.events.dao;

import com.britesnow.snow.util.MapUtil;
import com.google.inject.Singleton;
import com.imranzahid.events.entity.Anniversary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@Singleton
public class AnniversaryDao extends BaseHibernateDao<Anniversary> {
  @Nullable
  public Anniversary findAnniversaryByName(@Nonnull String anniversaryName) {
    return findUnique("FROM Anniversary WHERE name = :anniversaryName",
        MapUtil.mapIt("anniversaryName", anniversaryName, "active", "Y"));
  }

  @Nullable
  public Anniversary findAnniversaryByFlower(@Nonnull String flowerName) {
    return findUnique("FROM Anniversary WHERE flower = :flowerName",
        MapUtil.mapIt("flowerName", flowerName, "active", "Y"));
  }
}
