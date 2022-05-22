package comon;

import enums.Role;
import enums.Gender;
import lombok.*;

@Data
public abstract class User {
    private int userID;
    private  String userName;
    private Gender gender;
    private String email;
    private Role role;

    public User(int userID, String userName, Gender gender, String email, Role role) {
        this.userID = userID;
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.role = role;
    }
}
