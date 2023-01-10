package model;

import java.io.Serializable;

public class JoinObject implements Serializable {
    private String username;

    public JoinObject(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
