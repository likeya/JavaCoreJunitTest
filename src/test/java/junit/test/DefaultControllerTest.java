package junit.test;

import junit.test.domain.DefaultController;
import junit.test.interfaces.Request;
import junit.test.interfaces.RequestHandler;
import junit.test.interfaces.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Created by Likeya on 2017/2/28.
 */
public class DefaultControllerTest {
    private DefaultController controller;
    private Request request;
    private RequestHandler handler;

    private class SampleRequest implements Request {
        public String getName() {
            return "Test";
        }
    }

    private class SampleHandler implements RequestHandler {
        public Response process(Request request) throws Exception {
            return new SampleRespone();
        }
    }

    private class SampleRespone implements Response {
        private static final String NAME = "Test";

        public boolean equals(Object obj) {
            Boolean result = false;
            if (obj instanceof SampleRespone) {
                result = ((SampleRespone) obj).getName().equals(getName());
            }
            return result;
        }

        public int hashCode() {
            return NAME.hashCode();
        }

        public String getName() {
            return NAME;
        }
    }

    /*
    * 对在AddHandlerTest()和ProcessRequest()方法中重复使用的实例化代码进行重构，放到@Before中
    */
    @Before
    public void instanitate() throws Exception {
        System.out.println("这里是第一个@Before");
        controller = new DefaultController();
        request = new SampleRequest();
        handler = new SampleHandler();
        controller.AddHandler(request, handler);
    }

    @After
    public void afterOrder() {
        System.out.println("这是第一个after.........");
    }

    /*针对Controller的请求处理扩展进行测试*//*
    @Test
    public void AddHandlerTest() {
        Request request = new SampleRequest();
        RequestHandler requestHandler = new SampleHandler();
        controller.AddHandler(request, requestHandler);
        RequestHandler requestHandler1 = controller.getHandler(request);
        assertSame("获取到的requestHandler应该要和设置的一样", requestHandler1, requestHandler);
    }

    *//*
     针对Controller的核心功能：请求处理进行测试
    1、处理器返回的Respone是否为NULL
    2、处理器在收到请求后，判断返回的Respone是否与RequestHandler返回的Respone是同一对象
    *//*
    @Test
    public void processRequestTest() {
        Request request = new SampleRequest();
        RequestHandler handler = new SampleHandler();
        controller.AddHandler(request, handler);
        Response response = controller.processRequest(request);
        assertNotNull("不能返回一个空的Respone对象", response);
        assertEquals("respone应该是SampleRespone的一个对象", response.getClass(), SampleRespone.class);
    }*/

    @Test
    public void AddHandlerTest() {
        RequestHandler handler2 = controller.getHandler(request);
        assertSame(handler2, handler);
    }

    @Test
    public void ProcessRequest() {
        Response response = controller.processRequest(request);
        assertNotNull(response);
        assertEquals(SampleRespone.class, response.getClass());
    }

    @Test
    public void AddHanderAndProcessRequestTest() {
        Response response = controller.processRequest(request);
        assertNotNull(response);
        assertEquals(new SampleRespone(), response);
    }
}