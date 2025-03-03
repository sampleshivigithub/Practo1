package com.shivi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ForgetPassword {

    private String oldPassword;

    private String newPassword;

    private String confirmNewPassword;


}