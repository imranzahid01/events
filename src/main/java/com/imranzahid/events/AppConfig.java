package com.imranzahid.events;

import com.britesnow.snow.util.PackageScanner;
import com.britesnow.snow.web.auth.AuthRequest;
import com.britesnow.snow.web.binding.EntityClasses;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.imranzahid.events.entity.BaseEntity;
import com.imranzahid.events.web.AppAuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
public class AppConfig extends AbstractModule {
  private static Logger log = LoggerFactory.getLogger(AppConfig.class);

  @Override public void configure() {
    bind(AuthRequest.class).to(AppAuthRequest.class);
  }

  // Used by the Snow Hibernate helpers to inject the entity class
  // Just need to provide the @EntityClasses
  @SuppressWarnings("unchecked")
  @Provides @Singleton
  @EntityClasses
  public Class[] provideEntityClasses() {
    try {
      return new PackageScanner(BaseEntity.class.getPackage().getName())
          .findAnnotatedClasses(Entity.class);
    } catch (Throwable e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException("Cannot get all the enity class: " + e.getMessage());
    }
  }
}
