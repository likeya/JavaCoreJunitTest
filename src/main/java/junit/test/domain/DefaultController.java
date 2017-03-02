package junit.test.domain;

import junit.test.interfaces.Controller;
import junit.test.interfaces.Request;
import junit.test.interfaces.RequestHandler;
import junit.test.interfaces.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Likeya on 2017/2/28.
 */
public class DefaultController implements Controller {
    private Map requestHandlers = new HashMap();

    public RequestHandler getHandler(Request request) {
        if (!this.requestHandlers.containsKey(request.getName())) {
            String message = "处理器没有找到名为：[" + request.getName() + "] 的请求";
            throw new RuntimeException(message);
        }
        return (RequestHandler) this.requestHandlers.get(request.getName());
    }


    public Response processRequest(Request request) {
        Response response;
        try {
            response = getHandler(request).process(request);
        } catch (Exception exception) {
            response = new ErrorResponse(request, exception);
        }
        return response;
    }

    public void AddHandler(Request request, RequestHandler requestHandler) {
        if (this.requestHandlers.containsKey(request.getName())) {
            throw new RuntimeException("名为：[" + request.getName() + "]的请求处理器已经被注册了");
        } else {
            this.requestHandlers.put(request.getName(), requestHandler);
        }

    }
}
