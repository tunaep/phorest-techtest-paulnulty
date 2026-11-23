package com.phorest.response;

import java.util.List;

public record PlayResponse(
      List<String> slots,
      String result,
      int currentFloat,
      int freePlaysRemaining,
      int spinCost
) {
}
