package com.phorest;

import com.phorest.properties.FruitMachineProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(FruitMachineProperties.class)
@SpringBootApplication
public class PhorestApplication {

   static void main(String[] args) {
      SpringApplication.run(PhorestApplication.class, args);
   }

}
