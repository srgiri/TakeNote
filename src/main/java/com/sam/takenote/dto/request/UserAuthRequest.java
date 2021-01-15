package com.sam.takenote.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserAuthRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
