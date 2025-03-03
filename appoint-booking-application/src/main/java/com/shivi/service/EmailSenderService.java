package com.shivi.service;

import com.shivi.entity.EmailBody;
import jakarta.mail.MessagingException;

public interface EmailSenderService {

    Boolean sendAppointmentBookingMail(String toEmail, EmailBody emailBody) throws MessagingException;
}
