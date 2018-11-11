package xyz.crearts.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
@Builder
public class Response<T> {
    private long code;
    private String message;
    private T data;

    public static Response failed(Throwable throwable) {
        return  Response.builder()
                .code(1)
                .message(throwable.getMessage())
                .build();
    }

    public static <T> Response success(T data) {
        return Response.builder()
                .code(0)
                .data(data)
                .build();
    }
}
