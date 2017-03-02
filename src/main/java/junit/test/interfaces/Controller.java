package junit.test.interfaces;

/**
 * Created by Likeya on 2017/3/1.
 */
public interface Controller {
    Response processRequest(Request request);
    //对Controller的扩展
    void AddHandler(Request request, RequestHandler requestHandler);
}
