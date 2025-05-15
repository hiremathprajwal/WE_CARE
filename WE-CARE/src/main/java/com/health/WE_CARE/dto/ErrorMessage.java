//package com.health.WE_CARE.dto;
//
//public class ErrorMessage {
//}
package com.health.WE_CARE.dto;

public class ErrorMessage {

    private String message;

    // Constructor
    public ErrorMessage(String message) {
        this.message = message;
    }

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
