package org.cacanhdaden.quanlythuoc.util.validator;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

@AllArgsConstructor
public class DateValidatorImp implements ValidatorInterface {
    private final  String date;

    private static void checkValidDate(String dateStr) throws InvalidInformationException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidInformationException("Định dạng thời gian không hợp lệ. Định dạng hợp lệ sẽ là ngày/tháng/năm. Với ngày và tháng bắt buộc là 2 chữ số, còn năm là 4 chữ số");
        }
    }
    @Override
    public void checkValid() throws InvalidInformationException {
        checkValidDate(this.date);
    }
}
