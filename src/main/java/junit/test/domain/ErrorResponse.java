package junit.test.domain;

import junit.test.interfaces.Request;
import junit.test.interfaces.Response;

/**
 * Created by Likeya on 2017/3/1.
 */
public class ErrorResponse implements Response {
    private Request originalRequest;
    private Exception originalException;

    public ErrorResponse(Request originalRequest, Exception originalException) {
        this.originalRequest = originalRequest;
        this.originalException = originalException;
    }

    public Request getOriginalRequest() {
        return originalRequest;
    }

    public Exception getOriginalException() {
        return originalException;
    }
}
