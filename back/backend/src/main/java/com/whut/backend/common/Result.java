package com.whut.backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private static final String SUCCESS_CODE = "0";
    private static final String ERROR_CODE = "-1";

    public static Result success() {
        return new Result(SUCCESS_CODE, "success", null);
    }

    public static Result success(Object data) {
        return new Result(SUCCESS_CODE, "success", data);
    }

    public static Result error(String msg) {
        return new Result(ERROR_CODE, msg, null);
    }

    private String code;
    private String msg;
    private Object data;
}
