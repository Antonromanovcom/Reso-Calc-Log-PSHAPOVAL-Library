/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SHAPPN
 */
public class Utils {

    private static long calcid = -1;

    private static final String TEMPLATE_SEND_ERROR = "%1$s\n CalcID: %2$s Error message: %3$s\n";

    /**
     * Проверка на NUll, если arg не null возвращает arg, иначе other
     *
     * @param <T>
     * @param arg обьект который хотим проверить на null
     * @param other возвратим его если arg = null
     * @return
     */
    public static <T> T orElse(T arg, T other) {
        if (arg != null) {
            return arg;
        } else {
            return other;
        }
    }

    /**
     * Возвращает "Д" если true и "Н" если false
     *
     * @param arg
     * @return
     */
    public static String bool2YN(boolean arg) {
        if (arg) {
            return "Д";
        } else {
            return "Н";
        }
    }

    /**
     * *
     *
     * @param arg
     * @return
     */
    public static boolean YN2Bool(String arg) {
        return arg != null && !arg.isEmpty() && arg.equals("Д");
    }

    /**
     * *
     * Логирование ошибок в лог сервера
     *
     * @param logLevel - уровень ошибки
     * @param message - сообщение об ошибке
     * @param e - exeption
     */
    public static void logging(Level logLevel, String message, Exception e) {
        Logger.getLogger("").log(logLevel, getExceptionFullMessage(message, e), e);
    }

    public static void logWarn(String message, Exception e) {
        logging(Level.WARNING, message, e);
    }

    public static void logWarn(Exception e) {
        logWarn("", e);
    }

    public static void logError(String message, Exception e) {
        logging(Level.SEVERE, message, e);
    }

    public static void logError(Exception e) {
        logError("", e);
    }

    public static String getExceptionFullMessage(String message, Exception ex) {
        return String.format(TEMPLATE_SEND_ERROR, message, calcid, ex.toString());
    }

    public static String getStackTrace(Exception ex) {
        String result = "";

        for (short i = 0; i < ex.getStackTrace().length; i++) {
            result += ex.getStackTrace()[i].toString() + System.lineSeparator();
        }

        return result;
    }

}
