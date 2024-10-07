package com.RentBikeSystem.BikeService.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIDTO {
    private Object object;
    private String Message;
    private Map<String,String> response;
    private HttpStatus httpStatus;
    private LocalDateTime time =LocalDateTime.now();
    public APIDTO(Object object, String message) {
        this.object = object;
        this.Message = message;

    }

}
