package edu.cnm.deepdive.dominionservice.model.enums;

import java.util.Arrays;

public enum StackTypes {
  Bronze,
  Silver,
  Gold,
  Estate,
  Duchy,
  Province,
  Cellar,
  Moat,
  Village,
  Workshop,
  Smithy,
  Remodel,
  Militia,
  Market,
  Mine,
  Merchant,
  Trash;

  String[] symbols = {"Bronze", "Silver", "Gold", "Estate", "Duchy", "Province", "Cellar", "Moat",
      "Village",
      "Workshop", "Smithy", "Remodel", "Militia", "Market", "Mine", "Merchant", "Trash"};

  @Override
  public String toString() {
    return "StackTypes{" +
        "symbols=" + Arrays.toString(symbols) +
        '}';
  }
}
