package com.imranzahid.events.dao;

import com.britesnow.snow.util.MapUtil;
import com.google.inject.Singleton;
import com.imranzahid.events.entity.Relationship;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@Singleton
public class RelationshipDao extends BaseHibernateDao<Relationship> {
  @Nullable
  public Relationship findRelationshipByName(@Nonnull String relationshipName) {
    return findUnique("FROM Relationship WHERE name = :relationshipName",
        MapUtil.deepMapIt("relationshipName", relationshipName, "active", "Y"));
  }
}
