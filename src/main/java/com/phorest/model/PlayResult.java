package com.phorest.model;

import java.util.List;

public record PlayResult(
      List<Colours> slots,
      WinStatus winStatus,
      int currentFloat,
      int freePlaysRemaining,
      int spinCost
) {
}
