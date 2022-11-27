package com.mindex.challenge.validator;

import com.mindex.challenge.exception.DateInValidException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
THis class validates the effectiveDate of compensation
 */
public class DateValidator {
    static DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("MM/dd/yyyy")
                    .withResolverStyle(ResolverStyle.SMART);

    /**
     * This method validates the effectiveDate of compensation
     * @param dateStr
     * @return
     * @throws DateInValidException
     */
    public static LocalDate validateEffectiveDate(String dateStr) throws DateInValidException {

        LocalDate date = null;
        date = LocalDate.parse(dateStr, dateFormatter);
        if(date == null)
        {
            throw new DateInValidException("Please Enter valid effective date");
        }
        return date;
    }
}
