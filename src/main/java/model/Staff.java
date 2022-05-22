package model;

import comon.User;
import enums.Gender;
import enums.Role;
import lombok.*;

import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Staff extends User {
    private String staffID;

    public Staff(int userID, String userName, Gender gender, String email, Role role) {
        super(userID, userName, gender, email, role);
        this.staffID = generateStaffId();
    }

    private String generateStaffId(){
        Random random = new Random();
        int randomNum = random.nextInt(200) + 100;
        String role = super.getRole() == Role.CASHIER ? "CASR" : "MANG";
        return "STA-" + role + "-" + randomNum;
    }

    @Override
    public String toString() {
        return "Staff{Name: " + getUserName() + ", E-mail: " + getEmail() + ", Gender: " + getGender() +
                "staffID: " + staffID + '\'' +
                '}';
    }
}
