package junit.test.interfaces;

/**
 * Created by Likeya on 2017/3/1.
 */
public interface RequestHandler {
    public Response process(Request request) throws Exception;
}
