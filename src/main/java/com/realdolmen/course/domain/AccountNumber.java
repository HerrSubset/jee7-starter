package com.realdolmen.course.domain;

import javax.persistence.Embeddable;

@Embeddable
public class AccountNumber {
    private Long bankCode;
    private Long accountCode;
    private Long controlCode;

    public AccountNumber() {

    }

    public AccountNumber(Long bankCode, Long accountCode, Long controlCode) {
        this.bankCode = bankCode;
        this.accountCode = accountCode;
        this.controlCode = controlCode;
    }

    public Long getBankCode() {
        return bankCode;
    }

    public void setBankCode(Long bankCode) {
        this.bankCode = bankCode;
    }

    public Long getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(Long accountCode) {
        this.accountCode = accountCode;
    }

    public Long getControlCode() {
        return controlCode;
    }

    public void setControlCode(Long controlCode) {
        this.controlCode = controlCode;
    }

    public String toString() {
        return String.format("%03d-%07d-%02d", bankCode, accountCode, controlCode);
    }
}
