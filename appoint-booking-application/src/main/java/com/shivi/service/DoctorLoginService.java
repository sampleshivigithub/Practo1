package com.shivi.service;

import com.shivi.entity.LoginDTO;
import com.shivi.entity.LoginUUIDKey;
import com.shivi.exception.LoginException;

public interface DoctorLoginService {

    LoginUUIDKey logIntoAccount(LoginDTO loginDTO) throws LoginException;

    String logoutFromAccount(String key) throws LoginException;

    Boolean ckeckUserLoginOrNot(String key) throws LoginException;

    Boolean checkUserLoginOrNot(String key) throws LoginException;
}
