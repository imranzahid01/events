package com.imranzahid.events.dao;

import com.imranzahid.events.entity.BaseEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
public interface BaseDao<E extends BaseEntity> {
  public enum SortOrder {
    ASC, DESC
  }

  @Nullable E get(@Nonnull Long id);
  @Nullable Map<Long, E> get(@Nonnull Long ... ids);
  @Nullable E findUnique(@Nonnull String query, @Nullable Map properties);
  @Nullable E save(@Nonnull E entity);
}
