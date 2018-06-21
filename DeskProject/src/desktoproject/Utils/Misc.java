/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author noda
 */
public abstract class Misc {
    public static void setOnlyNumbersWithComma(TextField t) {
        Pattern pattern = Pattern.compile("\\d*|\\d+\\,\\d*");
        TextFormatter<TextFormatter> formatter = new TextFormatter<>((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        t.setTextFormatter(formatter);
    }
    
    public static void setOnlyNumbers(TextField t) {
        Pattern pattern = Pattern.compile("\\d*");
        TextFormatter<TextFormatter> formatter = new TextFormatter<>((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        t.setTextFormatter(formatter);
    }
    
    public static void setOnlyNumbersWithDot(TextField t){
        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter<TextFormatter> formatter = new TextFormatter<>((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        t.setTextFormatter(formatter);
    }
    
    public static String changeToDot(String num){
        return num.replace(",",".");
    }
    
    public static String changeToComma(String num) {
        return num.replace(".", ",");
    }
    
    public static Date localToDate(LocalDate local){
        Instant instant = Instant.from(local.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }
    
    public static LocalDate dateToLocal(Date date){
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public static String dateToString(Date date){
//        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        return DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.getDefault()).format(date);
    }
}
