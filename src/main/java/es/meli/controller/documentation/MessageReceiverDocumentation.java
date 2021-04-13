package es.meli.controller.documentation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface MessageReceiverDocumentation {

  @Tag(name = "Level2", description = "Exposes methods to support the chalenges proposed in level 2.")
  @Target(TYPE)
  @Retention(RUNTIME)
  @interface SecondLevel {

  }
  
  @Operation(summary = "This service calculate the position and message based on the input.", 
      description = "Returns X and Y cordenate and the message", 
      responses = {
          @ApiResponse(description = "Successful operation", responseCode = "200"),
          @ApiResponse(description = "Message not found", responseCode = "404"),
          })

  @Target(METHOD)
  @Retention(RUNTIME)
  @interface TopSecret {

  }
  
}
