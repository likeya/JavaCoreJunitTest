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

    }

    @Before
    public void instanitate() throws Exception {
        System.out.println("这里是第一个@Before");
        controller = new DefaultController();
    }

    @After
    public void afterOrder() {
        System.out.println("这是第一个after.........");
    }

    /*针对Controller的请求处理扩展进行测试*/
    @Test
    public void AddHandlerTest() {
        Request request = new SampleRequest();
        RequestHandler requestHandler = new SampleHandler();
        controller.AddHandler(request, requestHandler);
        RequestHandler requestHandler1 = controller.getHandler(request);
        assertSame("获取到的requestHandler应该要和设置的一样", requestHandler1, requestHandler);
    }

    /*
     针对Controller的核心功能：请求处理进行测试
    1、处理器返回的Respone是否为NULL
    2、处理器在收到请求后，判断返回的Respone是否与RequestHandler返回的Respone是同一对象
    */
    @Test
    public void processRequestTest() {
        Request request = new SampleRequest();
        RequestHandler handler = new SampleHandler();
        controller.AddHandler(request, handler);
        Response response = controller.processRequest(request);
        assertNotNull("不能返回一个空的Respone对象", response);
        assertEquals("respone应该是SampleRespone的一个对象", response.getClass(), SampleRespone.class);
    }

}