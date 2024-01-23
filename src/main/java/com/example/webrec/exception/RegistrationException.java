package com.example.webrec.exception;

public class RegistrationException extends Exception {
  private boolean hasRegistered;
  private boolean validPassword;

  public RegistrationException(String msg, boolean hasRegistered, boolean validPassword) {
    super(msg);
    this.hasRegistered = hasRegistered;
    this.validPassword = validPassword;
  }
}
