package com.example.webrec.service;

import com.example.webrec.exception.RegistrationException;
import com.example.webrec.repository.UserRepository;

public class AuthService {

  private UserRepository userRepository = new UserRepository();

  public boolean find(String username, String password) {

    if(username == null) {
      return false;
    } else if(password == null) {
      return false;
    }

    String userPassword = userRepository.findPassword(username);

    // enligt GDPR ska lössenrod hashas
    // istället för att matcha "rena" lössneord så matchar vi
    // hashade lössenord mot hashningen av in-lössenordet
    return userPassword.equals(password);
  }

  public boolean create(String username, String password) throws RegistrationException {

    if(!password.matches("[@!%]")) {
      throw new RegistrationException("Special character must be used", false, false);
    }

    return userRepository.create(username, password);
  }
}
