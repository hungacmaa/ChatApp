package model;

import java.io.Serializable;

public class ResponseObject implements Serializable {
    private String status;
    private String action;
    private Object data;

    public ResponseObject(String status, String action, Object data) {
        this.status = status;
        this.action = action;
        this.data = data;
    }
}
