package com.hw.admin.model.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum ResponseCodeEnum {
    /**
     * 表示API能正常返回
     */
    SUCCESS(200, "成功"),

    // 全局错误
    SYSTEM_ERROR(99_010, "系统异常"),
    BUSINESS_ERROR(99_020, "业务异常", "[%s]业务发生异常"),
    REMOTE_SERVICE_ERROR(99_030, "远程服务异常", "调用远程服务[%s]发生异常"),
    UNKNOWN_ERROR(99_090, "未知错误"),
    NOT_IMPLEMENT_ERROR(99_999, "功能没有实现"),
    NEED_RESET_PASSWORD(99_040, "重置密码"),
    ;


    private final static Map<Integer, ResponseCodeEnum> BY_CODE_MAP =
            Arrays.stream(ResponseCodeEnum.values()).collect(Collectors.toMap(ResponseCodeEnum::code, code -> code));

    private final int code;
    private final String desc;
    private final String template;

    ResponseCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
        this.template = "";
    }

    ResponseCodeEnum(int code, String desc, String template) {
        this.code = code;
        this.desc = desc;
        this.template = template;
    }

    public int code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }

    public String template() {
        return this.template;
    }

    /**
     * @param code 代码
     * @return 转换出来的状态码
     */
    public static ResponseCodeEnum parse(Integer code) {
        return BY_CODE_MAP.getOrDefault(code, ResponseCodeEnum.SYSTEM_ERROR);
    }
    }
