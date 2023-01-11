package model;

import java.io.Serializable;

public class OutObject implements Serializable {
    private String username;

    public OutObject(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
