package org.cacanhdaden.quanlythuoc.util.converter;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatConverter {
    public static String convertToDDMMYYYY(final String mysqlDate) {
        try {
            // Define the MySQL date format
            SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Parse the MySQL date string into a Date object
            Date date = mysqlFormat.parse(mysqlDate);

            // Define the desired output format
            SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Convert the Date object to the desired format and return
            return desiredFormat.format(date);
        } catch (ParseException e) {
            return mysqlDate;
        }
    }
}

