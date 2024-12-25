package com.daiweij.myvedio.common.utils;

public class Constant {
    /**
     * 登录类型
     */
    public enum LoginType {
        /**
         * 用户名
         */
        USERNAME(0),
        /**
         * 手机号
         */
        PHONENUMBER(1);

        private final int value;

        LoginType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static LoginType getTypeByValue(int value) {
            for (LoginType type : LoginType.values()) {
                if (type.getValue() == value) {
                    return type;
                }
            }
            return null;
        }
    }
}
