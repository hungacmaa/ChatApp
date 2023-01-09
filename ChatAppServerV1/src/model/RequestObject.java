package model;

import java.io.Serializable;

public class RequestObject implements Serializable {
    private String action;
    private Object data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
