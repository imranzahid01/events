package com.imranzahid.events.web;

import com.britesnow.snow.web.param.annotation.PathVar;
import com.britesnow.snow.web.rest.annotation.WebGet;
import com.google.common.collect.Lists;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Imran Zahid
 *         date 12/29/13
 */
@Singleton
public class EventsWebHandler {
  @SuppressWarnings("UnusedDeclaration")
  private static Logger log = LoggerFactory.getLogger(EventsWebHandler.class);

  @WebGet("/api/events/{id}/list")
  public WebResponse showSettings(@PathVar("id") String id) {
    log.info("id = " + id);
    List<String> events = Lists.newArrayList("Test1", "Test2", "Test3");
    return WebResponse.success(events);
  }
}
