package com.phorest.mapper;

import com.phorest.response.PlayResponse;
import org.springframework.stereotype.Component;

@Component
public class PlayResponseMapper {

   public PlayResponse fromPlayResult(com.phorest.model.PlayResult playResult) {
      return new PlayResponse(
            playResult.slots().stream().map(Enum::name).toList() ,
            playResult.winStatus().getStatus()
      );
   }

}
