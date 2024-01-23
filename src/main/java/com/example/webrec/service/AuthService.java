package com.example.webrec.service;

import com.example.webrec.exception.RegistrationException;
import com.example.webrec.repository.UserRepository;

public class AuthService {

  private UserRepository userRepository = new UserRepository();

  public boolean find(String username, String password) {

    return false;
  }

  public boolean create(String username, String password) throws RegistrationException {

    if(!password.matches("[@!%]")) {
      throw new RegistrationException("Special character must be used", false, false);
    }

    return userRepository.create(username, password);
  }
}
