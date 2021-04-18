package es.meli.controller.documentation;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import es.meli.model.SatelliteNameEnum;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface MessageReceiverDocumentation {

  @OpenAPIDefinition(
      servers = { 
          @Server(
              url = "http://localhost:8080", 
              description = "For local tests."),
          @Server(
              url = "https://test-7oyxf34xma-uc.a.run.app", 
              description = "For production tests.") 
          }
      )
  
  @Tag(name = "Level2", description = "Exposes the methods to support the challenges proposed in level 2.")
  @Target(TYPE)
  @Retention(RUNTIME)
  @interface SecondLevel {

  }
  
  @Tag(name = "Level3", description = "Exposes the methods to support the challenges proposed in level 3.")
  @Target(TYPE)
  @Retention(RUNTIME)
  @interface ThirdLevel {

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
  
  @Operation(summary = "This service calculate the position and message based on the stored information.", 
      description = "Returns X and Y cordenate and the message", 
      responses = {
          @ApiResponse(description = "Successful operation", responseCode = "200"),
          @ApiResponse(description = "Message not found", responseCode = "404"),
          })

  @Target(METHOD)
  @Retention(RUNTIME)
  @interface SegmentedTopSecret {

  }
  
  @Operation(summary = "This service save the position and message for specific satellite.", 
      description = "Returns X and Y cordenate and the message", 
      parameters = {
              @Parameter(
                      in = PATH, 
                      name = "satelliteName", 
                      description = "Satellite name", 
                      required = true,
                      example = "sato", 
                      schema = @Schema(implementation = SatelliteNameEnum.class))
      },
      responses = {
          @ApiResponse(description = "Successful operation", responseCode = "200"),
          @ApiResponse(description = "Message not found", responseCode = "404"),
          })

  @Target(METHOD)
  @Retention(RUNTIME)
  @interface SegmentedSaveSatellite {

  }
  
  @Operation(summary = "This service delete the position and message for specific satellite.", 
      description = "Returns X and Y cordenate and the message", 
      parameters = {
              @Parameter(
                      in = PATH, 
                      name = "satelliteName", 
                      description = "Satellite name", 
                      required = true,
                      example = "sato", 
                      schema = @Schema(implementation = SatelliteNameEnum.class))
      },
      responses = {
          @ApiResponse(description = "Successful operation", responseCode = "200"),
          @ApiResponse(description = "Message not found", responseCode = "404"),
          })

  @Target(METHOD)
  @Retention(RUNTIME)
  @interface SegmentedDeleteSatellite {

  }
  
}
