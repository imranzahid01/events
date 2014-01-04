package com.imranzahid.events.test.web;

import com.britesnow.snow.testsupport.SnowTestSupport;
import com.britesnow.snow.testsupport.mock.RequestContextMock;
import com.britesnow.snow.util.MapUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static com.britesnow.snow.testsupport.mock.RequestContextMockFactory.RequestMethod.POST;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
public class LoginTest extends SnowTestSupport {
  @BeforeClass
  public static void initTestClass() throws Exception {
    SnowTestSupport.initWebApplication("src/main/webapp");
  }

  @Test
  public void loginTest() {
    RequestContextMock requestContext = requestContextFactory.createRequestContext(POST, "/api/login");
    requestContext.setParamMap(MapUtil.deepMapIt(
        "userName", "imranzahid@gmail.com",
        "password", "compaq"));
    webController.service(requestContext);
    Map result = requestContext.getResponseAsJson();
    Assert.assertTrue("Unable to login to the application", (Boolean) result.get("success"));
  }
}
