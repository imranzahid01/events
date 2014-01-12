package com.imranzahid.events.dao;

import com.britesnow.snow.util.MapUtil;
import com.google.inject.Singleton;
import com.imranzahid.events.entity.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@Singleton
public class UserDao extends BaseHibernateDao<User> {
  @Nullable
  public User findUserByEmail(@Nonnull String userEmail) {
    return findUnique("FROM User WHERE userEmail = :userEmail",
        MapUtil.deepMapIt("userEmail", userEmail, "active", "Y"));
  }
}
