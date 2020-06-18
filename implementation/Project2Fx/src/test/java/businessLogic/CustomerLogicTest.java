package businessLogic;

import entities.CustomerOrder;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerLogicTest {

    CustomerLogic customerLogic;

    @ParameterizedTest
    @CsvSource({
            //      customerOrderID,   fName,       lName,        birthYear,  email,                phoneNR,       address,      picUpAdd,              desAdd,                postCode,   amount,       deliveryDate,  hazardous, proposedPrice,  Status,
            //      0                  1            2             3           4                     5              6             7                      8                      9           10            11             12         13              14
            "       1,                'Dorothee+(**>',  'Schilli;++ng',  '2000-01-01', 'dorothee@gmail.com', '499888777',  'myAddress',  'picUpAddress',        'destinationAddress',  '4444KD',    999.5,     '2000-07-01',    'true'   ,  999.67,        'Pending'",
            "       2,                'laura',     'baus',       '2000-02-02', 'laura@gmail.com',    '+499863241',  'herAddress', 'anotherPicUpAddress', 'destinationAddress2', '3333OJ',    37000.8,     '2020-07-10',    'true'   ,  999.67,        'Approved'",
            "       3,                'M',     'b',       '2000-03-03', 'marek@gmail.com',    '+4209853170', 'hisAddress', 'thirdPicUpAddress',   'destinationAddress3', '5555PV',    33000.8,     '2020-09-05',    'false'  ,  999.67,        'Accepted'",
            "       4,                'Zali',      'Masodi',     '2000-08-03', 'zali@gmail.com',     '+4219886094', 'ourAddress', 'fourthPicUpAddress',  'destinationAddress4', '6666BF',    22000.9,     '2020-11-08',   'false'  ,  999.67,        'Rejected'"
    })
    public void CustomerLogicTest(ArgumentsAccessor args) {
        //customerLogic = new CustomerLogic();
        int customerID = args.getInteger(0);
        String firstName = args.getString(1);
        String lastName = args.getString(2);
        LocalDate birthDate = args.get(3, LocalDate.class);
        String email = args.getString(4);
        String phoneNumber = args.getString(5);
        String address = args.getString(6);
        String pickUpAddress = args.getString(7);
        String destinationAddress = args.getString(8);
        String postCode = args.getString(9);
        double amount = args.getDouble(10);
        LocalDate deliveryDate = args.get(11, LocalDate.class);
        boolean customerOrderHazardous = args.getBoolean(12);
        double proposedPrice = args.getDouble(13);
        String orderStatus = args.getString(14);

        if (firstName.equals("Zali")) {
            birthDate = LocalDate.now();
        }
        CustomerOrder customerOrder = new CustomerOrder(customerID, firstName, lastName, birthDate,
                email, phoneNumber, address, pickUpAddress, destinationAddress,
                postCode, amount, deliveryDate, customerOrderHazardous,
                proposedPrice, orderStatus);

        customerLogic = new CustomerLogic(customerOrder);


        String regex = "^[a-zA-Z]+$";

        if (customerOrder.getFirstName().length() > 2) {
            if (customerOrder.getFirstName().matches(regex)) {
                if (Character.isUpperCase(customerOrder.getFirstName().charAt(0))) {
                    assertThat(customerLogic.firstnameCheck()).isEqualTo(true);
                } else {
                    assertThat(customerLogic.firstnameCheck()).isEqualTo(false);
                }
            }
        } else {
            assertThat(customerLogic.firstnameCheck()).isEqualTo(false);
        }


        if (customerOrder.getLastName().length() > 2) {
            if (customerOrder.getFirstName().matches(regex)) {
                if (Character.isUpperCase(customerOrder.getLastName().charAt(0))) {
                    assertThat(customerLogic.lastnameCheck()).isEqualTo(true);
                } else {
                    assertThat(customerLogic.lastnameCheck()).isEqualTo(false);
                }
            }
        } else {
            assertThat(customerLogic.lastnameCheck()).isEqualTo(false);
        }


        if (!customerOrder.getBirthDate().isBefore(LocalDate.now()) && customerOrder.getBirthDate().equals(LocalDate.now())) {
            assertThat(customerLogic.birthdateCheck()).isFalse();
        } else {
            assertThat(customerLogic.birthdateCheck()).isTrue();
        }


        if (!customerOrder.getPhoneNumber().matches("[0-9]") && !customerOrder.getPhoneNumber().startsWith("+")) {
            assertThat(customerLogic.checkPhonenumber()).isFalse();

        } else {
            assertThat(customerLogic.checkPhonenumber()).isTrue();
        }


        LocalDate currentDate = LocalDate.now();
        long period = ChronoUnit.DAYS.between(currentDate, deliveryDate);

        if (deliveryDate.isBefore(currentDate) && period < 14) {
            assertThat(customerLogic.checkDeliveryDate()).isFalse();
        } else {
            assertThat(customerLogic.checkDeliveryDate()).isTrue();
        }

        if (amount < 20000.00 || amount > 50000.00) {
            assertThat(customerLogic.checkAmount()).isFalse();
        } else{
            assertThat(customerLogic.checkAmount()).isTrue();
        }































    }

   /* @Test
    public void acceptCustomer(){
        customerLogic.acceptCustomer();
        String actual = customerOrder.getOrderStatus();
        String exp = "Accepted";
        String exp1 = "qwer";
        assertThat(actual).isNotEmpty();
        assertThat(actual).isEqualTo(exp);
        assertThat(actual).isNotEqualTo(exp1);
    }*/
}