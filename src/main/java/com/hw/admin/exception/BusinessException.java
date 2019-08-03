package com.hw.admin.exception;

import com.hw.admin.model.enums.ResponseCodeEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 6912363249855903393L;
    private ResponseCodeEnum responseCode;
    private String message;

    /**
     * 创建异常
     *
     * @param code    异常状态码
     * @param message 提示信息
     */
    public BusinessException(final Integer code, String message) {
        super(String.valueOf(code));
        this.responseCode = ResponseCodeEnum.parse(code);
        this.message = StringUtils.isBlank(message) ? responseCode.desc() : message;
    }

    /**
     * 创建异常
     *
     * @param responseCode 异常状态码
     */
    public BusinessException(final ResponseCodeEnum responseCode) {
        super(String.valueOf(responseCode.code()));
        this.responseCode = responseCode;
        this.message = responseCode.desc();
    }

    /**
     * 创建异常
     *
     * @param responseCode 异常状态码
     * @param objects      模板格式化数据
     */
    public BusinessException(final ResponseCodeEnum responseCode, Object... objects) {
        super(String.valueOf(responseCode.code()));
        this.responseCode = responseCode;
        message = ArrayUtils.isEmpty(objects) || StringUtils.isBlank(responseCode.template())
                ? responseCode.desc()
                : String.format(this.responseCode.template(), objects);
    }

    /**
     * 创建异常
     *
     * @param message 异常信息
     */
    public BusinessException(final String message) {
        super(message);
    }

    /**
     * 创建异常
     *
     * @param message 异常信息
     * @param ex      异常根源
     */
    public BusinessException(final String message, final Throwable ex) {
        super(message, ex);
    }

    public ResponseCodeEnum getResponseCode() {
        return responseCode;
    }

    @Override
    public String getMessage() {
        return Optional.ofNullable(message).orElse(super.getMessage());
    }
}
