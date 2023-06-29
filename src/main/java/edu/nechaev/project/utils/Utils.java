package edu.nechaev.project.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Utils {
    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }

    public static boolean isCurrentUser(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName().equals(email);
        }
        return false;
    }

    public static boolean validateCreditCard(String cardNumber, String expirationDate, String cardholderName, String cvc) {
        // Проверка номера карты
        if (cardNumber == null || !Pattern.matches("^\\d{16}$", cardNumber)) {
            return false;
        }

        // Проверка срока действия
        if (expirationDate == null || !Pattern.matches("^(0[1-9]|1[0-2])/\\d{2}$", expirationDate)) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
        Date currentDate = new Date();

        try {
            Date expiration = dateFormat.parse(expirationDate);
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(currentDate);

            Calendar expirationCalendar = Calendar.getInstance();
            expirationCalendar.setTime(expiration);

            // Сравнение текущей даты с датой срока действия карты
            if (currentCalendar.after(expirationCalendar)) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        // Проверка имени и фамилии владельца карты
        if (cardholderName == null || !Pattern.matches("^[a-zA-Z]+\\s[a-zA-Z]+$", cardholderName)) {
            return false;
        }

        // Проверка CVC
        return cvc != null && Pattern.matches("^\\d{3}$", cvc);

        // Все проверки пройдены, данные карты соответствуют формату
    }
}
