package com.imranzahid.events.test.web;

import com.britesnow.snow.testsupport.SnowTestSupportNG;
import com.britesnow.snow.testsupport.mock.RequestContextMock;
import com.britesnow.snow.util.MapUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static com.britesnow.snow.testsupport.mock.RequestContextMockFactory.RequestMethod.POST;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
public class LoginTestNG extends SnowTestSupportNG {
  @BeforeClass
  public static void initTestClass() throws Exception {
    SnowTestSupportNG.initWebApplication("src/main/webapp");
  }

  @Test
  public void loginTest() {
    RequestContextMock requestContext = requestContextFactory.createRequestContext(POST, "/api/login");
    requestContext.setParamMap(MapUtil.deepMapIt(
        "userName", "imranzahid@gmail.com",
        "password", "compaq"));
    webController.service(requestContext);
    Map result = requestContext.getResponseAsJson();
    Assert.assertTrue((Boolean) result.get("success"), "Unable to login to the application");
  }
}
