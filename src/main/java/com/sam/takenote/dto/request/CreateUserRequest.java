package com.sam.takenote.dto.request;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CreateUserRequest implements Serializable {

    @NotNull
    private String userName;

    @NotNull
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            message = "Invalid email format")
    private String email;

    @NotNull
    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;
}
