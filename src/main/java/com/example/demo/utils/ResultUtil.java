package com.example.demo.utils;

import com.example.demo.entity.Result;

public class ResultUtil {
    /**
     * 成功返回含有object的值
     *
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    /**
     * 成功不反悔含有object的值
     *
     * @return
     */
    public static Result success() {

        return success(null);
    }

    /**
     * 失败返回信息
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
