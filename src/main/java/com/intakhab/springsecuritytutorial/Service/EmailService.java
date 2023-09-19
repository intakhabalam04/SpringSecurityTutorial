package com.intakhab.springsecuritytutorial.Service;

import com.intakhab.springsecuritytutorial.Model.Email;

public interface EmailService {
    String sendRegistrationMail(Email emailDetails);
}
