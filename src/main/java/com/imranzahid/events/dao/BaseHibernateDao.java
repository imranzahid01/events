package com.imranzahid.events.dao;

import com.britesnow.snow.web.db.hibernate.HibernateDaoHelper;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.googlecode.gentyref.GenericTypeReflector;
import com.imranzahid.events.entity.BaseEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
public class BaseHibernateDao<E extends BaseEntity> implements BaseDao<E> {
  protected Class<E> entityClass;

  @Inject
  protected HibernateDaoHelper daoHelper;

  @SuppressWarnings("unchecked")
  public BaseHibernateDao() {
    Type persistentType = GenericTypeReflector.getTypeParameter(
        getClass(), BaseHibernateDao.class.getTypeParameters()[0]);
    if (persistentType instanceof Class) {
      this.entityClass = (Class<E>) persistentType;
    } else {
      throw new IllegalStateException("concrete class " + getClass().getName()
          + " must have a generic binding for interface "
          + BaseHibernateDao.class.getName());
    }
  }

  @Override @Nullable
  public E get(@Nonnull Long id) {
    return daoHelper.get(entityClass, id);
  }

  @Override @Nullable @SuppressWarnings("unchecked")
  public Map<Long, E> get(@Nonnull Long... ids) {
    Session session = daoHelper.getSession();
    session.getTransaction().begin();
    List<E> entities = (List<E>)session
        .createCriteria(entityClass)
        .add(Restrictions.in("id", ids))
        .list();
    session.getTransaction().commit();
    return Maps.uniqueIndex(entities, new Function<E, Long>() {
      @Override @Nonnull
      public Long apply(@Nonnull E input) {
        return input.getId();
      }
    });
  }

  @Override @Nullable @SuppressWarnings("unchecked")
  public Map<Long, E> listAll() {
    Session session = daoHelper.getSession();
    session.getTransaction().begin();
    List<E> entities = (List<E>)session
        .createCriteria(entityClass)
        .add(Restrictions.eq("active", "Y"))
        .list();
    session.getTransaction().commit();
    return Maps.uniqueIndex(entities, new Function<E, Long>() {
      @Override @Nonnull
      public Long apply(@Nonnull E input) {
        return input.getId();
      }
    });
  }

  @Override @Nullable @SuppressWarnings("unchecked")
  public E findUnique(@Nonnull String query, @Nullable Map properties) {
    Session session = daoHelper.getSession();
    session.getTransaction().begin();
    Query q = session.createQuery(query);
    if (properties != null) {
      q.setProperties(properties);
    }
    E entity = (E) q.uniqueResult();
    session.getTransaction().commit();
    return entity;
  }

  @Override @Nullable
  public E save(@Nonnull E entity) {
    return daoHelper.save(entity);
  }
}
