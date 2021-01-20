package pl.coderslab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Plan {

    private int id;
    private String name;
    private String description;
    private String created;
    private int adminId;


    public Plan(String name, String description, int adminId) {
        this.name = name;
        this.description = description;
        this.adminId = adminId;
    }
    public Plan(int id, String name, String description, int adminId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.adminId = adminId;
    }
}
