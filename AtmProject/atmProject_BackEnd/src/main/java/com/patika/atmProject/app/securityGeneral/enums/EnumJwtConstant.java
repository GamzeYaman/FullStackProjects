package com.patika.atmProject.app.securityGeneral.enums;

public enum EnumJwtConstant {
    BEARER("Bearer "),
    ;

    private String constant;
    EnumJwtConstant(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
