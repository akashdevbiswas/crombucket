package com.crombucket.storagemanager.exceptions;

public class RegionNotFoundException extends RuntimeException{

  public RegionNotFoundException(String message) {
    super(message);
  }
}
