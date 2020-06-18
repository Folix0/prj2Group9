package businessLogic;

import entities.CustomerOrder;
import jdk.jfr.Percentage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerLogic {

    private CustomerOrder customerOrder;
    private String regex = "^[a-zA-Z]+$";
    private LocalDate currentDate;


    public CustomerLogic(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    /**
     * @return checks if the lastname is inputted correctly, i.e. if it fulfills conditions
     * check if not empty
     * check if first character is uppercase
     * check if it matches the given regex
     */
    public boolean lastnameCheck() {
        String customerLastname = customerOrder.getLastName();
        return !Character.isLowerCase(customerLastname.charAt(0)) && customerLastname.matches(regex);

        //matcher is used to find matches of the pattern in texts
        //matches method from matcher class is used to test whether the pattern matches the text
    }

    public boolean firstnameCheck() {
        String customerFirstname = customerOrder.getFirstName();

        if (Character.isLowerCase(customerFirstname.charAt(0))) {
            return false;
        }

        //matcher is used to find matches of the pattern in texts
        //matches method from matcher class is used to test whether the pattern matches the text



        if (customerFirstname.length() < 2 || !customerFirstname.matches(regex)) {
            return false;
        }

        return true;
    }
    /**
     * @return checks that the birthdate is not in the past
     */
    public boolean birthdateCheck() {
        LocalDate customerBirthdate = customerOrder.getBirthDate();
        currentDate = LocalDate.now();


        if (!customerBirthdate.isBefore(currentDate) && customerBirthdate.equals(currentDate)) {
            return false;
        }

        //checkAge();
        //checkPeriod();
        return !false;
    }

    public boolean checkPhonenumber() {
        String phoneNumber = customerOrder.getPhoneNumber();

        if (!phoneNumber.matches("[0-9]") && !phoneNumber.startsWith("+")) {
            return false;
        }
        return true;
    }

    /**
     * @return check that the delivery date is in two weeks time at leat
     * check that dd is not before the current date
     */
    public boolean checkDeliveryDate() {
        LocalDate deliveryDate = customerOrder.getDeliveryDate();

        if (deliveryDate.isBefore(currentDate)) {
            return false;
        }

        //check that delivery date is in two weeks time
        long period = ChronoUnit.DAYS.between(currentDate, deliveryDate);
        return period >= 14;
    }

    /**
     * @return check that the amount is in the specified range
     */
    public boolean checkAmount() {
        double amount = customerOrder.getAmount();

        if (amount < 20000.00 || amount > 50000.00) {
            return false;
        }
        return true;
    }
}
