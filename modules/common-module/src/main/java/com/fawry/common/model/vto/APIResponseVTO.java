package com.fawry.common.model.vto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseVTO<T> {

    private boolean success;
    private String message;
    private T data;
    private Integer statusCode;

    public static <T> APIResponseVTO<T> success(T data, String message) {
        return APIResponseVTO.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .statusCode(200)
                .build();
    }

    public static <T> APIResponseVTO<T> error(String message, Integer statusCode) {
        return APIResponseVTO.<T>builder()
                .success(false)
                .message(message)
                .statusCode(statusCode)
                .build();
    }
}