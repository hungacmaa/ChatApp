package model;

import java.io.Serializable;

public class ResponseObject implements Serializable {
    static private final long serialVersionUID = 2L;
    private String message = "";
    private Object data = null;

    public ResponseObject() {
    }

    public ResponseObject(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
