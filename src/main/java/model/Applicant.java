package model;

import enums.Gender;
import enums.Qualification;
import comon.User;
import enums.Role;
import lombok.*;

@Getter
@Setter

public class Applicant extends User {
    private Qualification qualification;

    public Applicant(int userID, String userName, Gender gender, String email, Role role, Qualification qualification) {
        super(userID, userName, gender, email, role);
        this.qualification = qualification;
    }

//    public Applicant(Qualification qualification) {
//        this.qualification = qualification;
//    }

    @Override
    public String toString() {
        return "Applicant{Name: " + getUserName() + ", E-mail: " + getEmail() + ", Gender: " + getGender() +
                ", qualification=" + qualification +
                '}';
    }
}
