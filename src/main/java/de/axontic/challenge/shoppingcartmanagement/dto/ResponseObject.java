package de.axontic.challenge.shoppingcartmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObject<T> {
    private int status;
    private String message;
    private T data;
}
