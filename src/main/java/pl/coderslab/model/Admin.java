package pl.coderslab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Admin {
    int id;
    String firstName;
    String lastName;
    String email;
    String password;
    int superadmin = 0;
    int enable =1;

    public Admin(int id, String firstName, String lastName, String email, String password, int superadmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
