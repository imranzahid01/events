package com.imranzahid.events.web;

import com.britesnow.snow.web.handler.annotation.WebModelHandler;
import com.britesnow.snow.web.param.annotation.WebModel;
import com.britesnow.snow.web.param.annotation.WebUser;
import com.google.inject.Singleton;
import com.imranzahid.events.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@Singleton
public class MultiPageWebHandler {
  @SuppressWarnings("UnusedDeclaration")
  private static Logger log = LoggerFactory.getLogger(MultiPageWebHandler.class);

  @WebModelHandler(startsWith = "/")
  public void allPages(@WebModel Map<String, Object> map, @WebUser User user) {
    map.put("version", "1.0.0");
    map.put("api", user.getApi());
  }
}
