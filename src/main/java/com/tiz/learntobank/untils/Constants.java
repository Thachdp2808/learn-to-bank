package com.tiz.learntobank.untils;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Constants {
    public static final String AUTHENTICATION = "authentication";

    @Getter
    @AllArgsConstructor
    public enum CommonException {
        JSON_ERROR(0, "invalid_request"),
        INVALID_URL(1, "invalid_url"),
        AUTHENTICATION_ERROR(2, "invalid_request"),
        MANDATORY_FIELD_ERROR(3, "invalid_request"),
        DATA_TYPE_ERROR(4, "invalid_request"),
        METHOD_NOT_ALLOW(5, "invalid_method"),
        CLIENT_ERROR(6, "client_not_exist"),
        TRADER_NOT_EXIST(7, "trader_not_exist"),
        RECEPTION_NOT_EXIST(8, "reception_not_exist"),
        WORK_ORDER_ID_NOT_EXIST(10, "workOrderId_not_exist"),
        SYSTEM_ERROR(9, "想定外エラー");

        private final int code;
        private final String errorDescription;

        public static CommonException from(int code) {
            for (var exception : values()) {
                if (exception.getCode() == code) {
                    return exception;
                }
            }
            return null;
        }
    }
}
