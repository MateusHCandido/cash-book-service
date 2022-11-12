package com.mtzz.services.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class FormatData {

    protected String numberFormat(String number) {
        return number.replaceAll("[^0-9]+", "");
    }

    protected String nameFormat(String name){
        name = name.trim();
        name = name.toUpperCase();
        return name;
    }

    protected Date dateFormat() throws ParseException {
        Date dataCadastro = new Date();
        String formatDate;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatDate = dateFormat.format(dataCadastro);
        dataCadastro = dateFormat.parse(formatDate);
        return dataCadastro;
    }
}
