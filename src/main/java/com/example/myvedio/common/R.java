package com.example.myvedio.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class R<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> R<T> success(T data) {
        return R.<T>builder()
                .code(200)
                .msg("Success")
                .data(data)
                .build();
    }

    public static <T> R<T> success() {
        return R.<T>builder()
                .code(200)
                .msg("Success")
                .build();
    }

    public static <T> R<T> error(Integer code, String message) {
        return R.<T>builder()
                .code(code)
                .msg(message)
                .build();
    }
}
