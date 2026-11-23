package com.phorest.model;

public enum WinStatus {
   JACKPOT("jackpot"),
   SMALL_WIN("small win"),
   NO_WIN("no win");

   private final String status;

   WinStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return status;
   }
}
