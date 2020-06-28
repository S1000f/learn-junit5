package chap07.section2;

import lombok.Getter;

public class User {

    @Getter
    private String id;
    private String password;
    @Getter
    private String email;

    public User(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }
}
