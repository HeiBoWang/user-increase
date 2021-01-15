
package com.jd.user.increase.enums;
public enum ErrorCode {
    SUCCESS(10000,"请求成功")
    ;

    private Integer code;
    private String desc;

    ErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static ErrorCode fromIndex(Integer type){
        for(ErrorCode ErrorCode : ErrorCode.values()){
            if(ErrorCode.getCode() == type){
                return ErrorCode;
            }
        }
        return null;
    }

    public static String getDescfromType(Integer type){
        for(ErrorCode ErrorCode : ErrorCode.values()){
            if(ErrorCode.getCode() == type){
                return ErrorCode.getDesc();
            }
        }
        return null;
    }

}
