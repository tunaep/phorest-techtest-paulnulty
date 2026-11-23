package com.phorest.response;

public record ErrorResponse(
      String timestamp,
      int status,
      String message
) {
}
