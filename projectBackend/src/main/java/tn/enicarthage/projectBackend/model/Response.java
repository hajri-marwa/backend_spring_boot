package tn.enicarthage.projectBackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

//returned to user for either success or fail
@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)//include only values not null handy when some feature is null(reason,developerMessage..)
public class Response {
    protected LocalDateTime timeStamp;
    protected  int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String developerMessage;//to send message to frontend and more
    protected Map<?,?> data;//anything to send to the user as a response

}
