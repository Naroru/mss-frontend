package com.example.smsfrontend.enums;

import lombok.Getter;

@Getter
public enum NdsPositions {

  ZERO(0),
  TEN(10),
  FIFTEEN(15),
  TWENTY(20);

  private final int value;

  NdsPositions(int value) {
    this.value = value;
  }
}
