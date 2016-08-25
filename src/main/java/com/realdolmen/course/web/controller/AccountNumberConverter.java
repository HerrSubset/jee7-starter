package com.realdolmen.course.web.controller;

import com.realdolmen.course.domain.AccountNumber;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = AccountNumber.class)
public class AccountNumberConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.trim().isEmpty())
            return null;

        String[] numbers = value.split("-");
        AccountNumber res = null;

        if (numbers.length == 3) {
            res = new AccountNumber();

            res.setBankCode(Long.parseLong(numbers[0]));
            res.setAccountCode(Long.parseLong(numbers[1]));
            res.setControlCode(Long.parseLong(numbers[2]));
        } else {
            FacesMessage msg = new FacesMessage("Invalid account number");
            throw new ConverterException(msg);
        }

        return res;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }
}
