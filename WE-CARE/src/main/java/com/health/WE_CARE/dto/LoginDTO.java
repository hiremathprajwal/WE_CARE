//package com.health.WE_CARE.dto;
//
//public class LoginDTO {
//}
package com.health.WE_CARE.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {

    @NotBlank(message = "ID must not be blank")
    private String id;

    @NotBlank(message = "Password must not be blank")
    private String password;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
