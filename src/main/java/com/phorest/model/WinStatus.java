package com.phorest.model;

public enum WinStatus {
   JACKPOT("JACKPOT"),
   FULL_HOUSE("FULL_HOUSE"),
   SMALL_WIN("SMALL_WIN"),
   NO_WIN("NO_WIN"),;

   private final String status;

   WinStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return status;
   }
}
