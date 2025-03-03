package com.shivi.service;

import com.shivi.entity.CurrentSession;
import com.shivi.entity.Doctor;
import com.shivi.entity.LoginDTO;
import com.shivi.entity.LoginUUIDKey;
import com.shivi.exception.LoginException;
import com.shivi.repository.DoctorDao;
import com.shivi.repository.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class DoctorLoginServiceImpl implements DoctorLoginService{

    @Autowired
    DoctorDao doctorDao;

    @Autowired
    SessionDao sessionDao;

    @Override
    public LoginUUIDKey logIntoAccount(LoginDTO loginDTO) throws LoginException{

        LoginUUIDKey loginUUIDKey = new LoginUUIDKey();

        Doctor existingDoctor = doctorDao.findByMobileNo(loginDTO.getMobileNo());

        if (existingDoctor == null){
            throw new LoginException("Please enter valid mobile number" + loginDTO.getMobileNo());
        }

        // please do uncomment this code while using this application in postman

//		Optional<CurrentSession> validCustomerSessionOpt = sessionDao.findById(existingDoctor.getDoctorId());

//		if(validCustomerSessionOpt.isPresent()) {
//
//			throw new LoginException("Doctor already login");
//
//		}

        if(PatientServiceImpl.bCryptPasswordEncoder.matches(loginDTO.getPassword(),
                existingDoctor.getPassword())) {

            // check doctor have permission or not

            if(existingDoctor.getValidDoctor() == false) {

                throw new LoginException("You don't have permission to login. Please contact" +
                        " Admin for permission.");
            }

//		if(existingDoctor.getPassword().equals(loginDTO.getPassword())) {

            String key = generateRandomString();

            CurrentSession currentPatientSession = new CurrentSession(existingDoctor.getDoctorId(),
                    key, LocalDateTime.now());



            existingDoctor.setType("doctor");
            currentPatientSession.setUserId(existingDoctor.getDoctorId());
            currentPatientSession.setUserType("doctor");

            doctorDao.save(existingDoctor);

            sessionDao.save(currentPatientSession);

            loginUUIDKey.setMsg("Login Successful as doctor with this key");

            loginUUIDKey.setUuid(key);

            return loginUUIDKey;

        }else {

            throw new LoginException("Please enter valid password");

        }
    }



    @Override
    public String logoutFromAccount(String key) throws LoginException {



        CurrentSession currentDoctorOptional = sessionDao.findbyUuid(key);

        if(currentDoctorOptional != null) {

            sessionDao.delete(currentDoctorOptional);

            return "Logout successful";

        }else {

            throw new LoginException("Please enter valid key");

        }
    }

    @Override
    public Boolean ckeckUserLoginOrNot(String key) throws LoginException {
        return null;
    }

    @Override
    public Boolean checkUserLoginOrNot(String key) throws LoginException {

        CurrentSession currentPatientSession = sessionDao.findByUuid(key);

        if(currentPatientSession != null) {

            return true;

        }else {

            return false;
        }

    }

    public static String generateRandomString() {

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }


}
