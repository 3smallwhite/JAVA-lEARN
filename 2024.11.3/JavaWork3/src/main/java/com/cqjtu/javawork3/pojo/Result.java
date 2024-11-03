package com.cqjtu.javawork3.pojo;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result error(String message) {
        return new Result(0, message, null);
    }

    public static Result success() {
        return new Result(1, "操作成功", null);
    }

    public static <E> Result<E> success(E data) {
        return new Result(1, "操作成功", data);
    }
}
