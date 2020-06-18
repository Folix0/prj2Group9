package businessLogic;

import entities.Calculator;
import entities.CustomerOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;


import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AccountantLogicTest {

    AccountantLogic accountantLogic;

    @ParameterizedTest
    @CsvSource({
            //      customerOrderID,   fName,       lName,        birthYear,  email,                phoneNR,       address,      picUpAdd,              desAdd,                postCode,   amount,       deliveryDate,  hazardous, proposedPrice,  Status,     pricePKm,   calculatorHazardous,   kms
            //      0                  1            2             3           4                     5              6             7                      8                      9           10            11             12         13              14          15          16                     17
            "       1,                'Dorothee',  'Schilling',  '2000-01-01', 'dorothee@gmail.com', '+499888777',  'myAddress',  'picUpAddress',        'destinationAddress',  '4444KD',    25000.5,     '2020-07-01',    'true'   ,  999.67,        'Pending',   2,         'true',                 100 ",
            "       2,                'Laura',     'Baus',       '2000-02-02', 'laura@gmail.com',    '+499863241',  'herAddress', 'anotherPicUpAddress', 'destinationAddress2', '3333OJ',    37000.8,     '2020-08-09',    'true'   ,  999.67,        'Approved',  2,         'true',                 100 ",
            "       3,                'Marek',     'Brož',       '2000-03-03', 'marek@gmail.com',    '+4209853170', 'hisAddress', 'thirdPicUpAddress',   'destinationAddress3', '5555PV',    33000.8,     '2020-09-05',    'false'  ,  999.67,        'Accepted',  2,         'false',                100 ",
            "       4,                'Zali',      'Masodi',     '2000-08-03', 'zali@gmail.com',     '+4219886094', 'ourAddress', 'fourthPicUpAddress',  'destinationAddress4', '6666BF',    22000.9,     '2020-11-08',   'false'  ,  999.67,        'Rejected',  2,         'false',                100 "
    })
     void AccountantLogicTest(ArgumentsAccessor args){
        double pricePerKm = args.getDouble(15);
        boolean calculatorHazardous = args.getBoolean(16);
        int kilometers = args.getInteger(17);

        accountantLogic = new AccountantLogic();

        Calculator calculator = new Calculator(pricePerKm, calculatorHazardous, kilometers);


        double calculation = accountantLogic.calculatePrice(calculator);
        assertThat(accountantLogic.calculatePrice(calculator)).isEqualTo(calculation);

    }

    @ParameterizedTest
    @CsvSource({
            //      customerOrderID,   fName,       lName,        birthYear,  email,                phoneNR,       address,      picUpAdd,              desAdd,                postCode,   amount,       deliveryDate,  hazardous, proposedPrice,  Status,     pricePKm,   calculatorHazardous,   kms
            //      0                  1            2             3           4                     5              6             7                      8                      9           10            11             12         13              14          15          16                     17
            "       1,                'Dorothee',  'Schilling',  '2000-01-01', 'dorothee@gmail.com', '+499888777',  'myAddress',  'picUpAddress',        'destinationAddress',  '4444KD',    25000.5,     '2020-07-01',    'true'   ,  999.67,        'Pending',   2,         'true',                 100 ",
            "       2,                'Laura',     'Baus',       '2000-02-02', 'laura@gmail.com',    '+499863241',  'herAddress', 'anotherPicUpAddress', 'destinationAddress2', '3333OJ',    37000.8,     '2020-08-09',    'true'   ,  999.67,        'Approved',  2,         'true',                 100 ",
            "       3,                'Marek',     'Brož',       '2000-03-03', 'marek@gmail.com',    '+4209853170', 'hisAddress', 'thirdPicUpAddress',   'destinationAddress3', '5555PV',    33000.8,     '2020-09-05',    'false'  ,  999.67,        'Accepted',  2,         'false',                100 ",
            "       4,                'Zali',      'Masodi',     '2000-08-03', 'zali@gmail.com',     '+4219886094', 'ourAddress', 'fourthPicUpAddress',  'destinationAddress4', '6666BF',    22000.9,     '2020-11-08',   'false'  ,  999.67,        'Rejected',  2,         'false',                100 "
    })
    public void RejectedStatusTest(ArgumentsAccessor args){
        int customerID = args.getInteger(0);
        String firstName = args.getString(1);
        String lastName = args.getString(2);
        LocalDate birthDate = args.get(3, LocalDate.class);
        String email = args.getString(4);
        String phoneNumber= args.getString(5);
        String address = args.getString(6);
        String pickUpAddress = args.getString(7);
        String destinationAddress = args.getString(8);
        String postCode = args.getString(9);
        double amount = args.getDouble(10);
        LocalDate deliveryDate = args.get(11, LocalDate.class);
        boolean customerOrderHazardous = args.getBoolean(12);
        double proposedPrice = args.getDouble(13);
        String orderStatus = args.getString(14);
        double pricePerKm = args.getDouble(15);
        boolean calculatorHazardous = args.getBoolean(16);
        int kilometers = args.getInteger(17);

        accountantLogic = new AccountantLogic();

        Calculator calculator = new Calculator(pricePerKm, calculatorHazardous, kilometers);
        CustomerOrder customerOrder = new CustomerOrder(customerID, firstName, lastName, birthDate,
                email, phoneNumber, address, pickUpAddress, destinationAddress,
                postCode, amount, deliveryDate, customerOrderHazardous,
                proposedPrice, orderStatus);


        if(customerOrder.getOrderStatus().equals("Pending")){
            accountantLogic.reject(customerOrder);
            assertThat(customerOrder.getOrderStatus()).isEqualTo("Rejected");
        }

    }

    @ParameterizedTest
    @CsvSource({
            //      customerOrderID,   fName,       lName,        birthYear,  email,                phoneNR,       address,      picUpAdd,              desAdd,                postCode,   amount,       deliveryDate,  hazardous, proposedPrice,  Status,     pricePKm,   calculatorHazardous,   kms
            //      0                  1            2             3           4                     5              6             7                      8                      9           10            11             12         13              14          15          16                     17
            "       1,                'Dorothee',  'Schilling',  '2000-01-01', 'dorothee@gmail.com', '+499888777',  'myAddress',  'picUpAddress',        'destinationAddress',  '4444KD',    25000.5,     '2020-07-01',    'true'   ,  999.67,        'Pending',   2,         'true',                 100 ",
            "       2,                'Laura',     'Baus',       '2000-02-02', 'laura@gmail.com',    '+499863241',  'herAddress', 'anotherPicUpAddress', 'destinationAddress2', '3333OJ',    37000.8,     '2020-08-09',    'true'   ,  999.67,        'Approved',  2,         'true',                 100 ",
            "       3,                'Marek',     'Brož',       '2000-03-03', 'marek@gmail.com',    '+4209853170', 'hisAddress', 'thirdPicUpAddress',   'destinationAddress3', '5555PV',    33000.8,     '2020-09-05',    'false'  ,  999.67,        'Accepted',  2,         'false',                100 ",
            "       4,                'Zali',      'Masodi',     '2000-08-03', 'zali@gmail.com',     '+4219886094', 'ourAddress', 'fourthPicUpAddress',  'destinationAddress4', '6666BF',    22000.9,     '2020-11-08',   'false'  ,  999.67,        'Rejected',  2,         'false',                100 "
    })
    public void ApprovedStatusTest(ArgumentsAccessor args){
        int customerID = args.getInteger(0);
        String firstName = args.getString(1);
        String lastName = args.getString(2);
        LocalDate birthDate = args.get(3, LocalDate.class);
        String email = args.getString(4);
        String phoneNumber= args.getString(5);
        String address = args.getString(6);
        String pickUpAddress = args.getString(7);
        String destinationAddress = args.getString(8);
        String postCode = args.getString(9);
        double amount = args.getDouble(10);
        LocalDate deliveryDate = args.get(11, LocalDate.class);
        boolean customerOrderHazardous = args.getBoolean(12);
        double proposedPrice = args.getDouble(13);
        String orderStatus = args.getString(14);
        double pricePerKm = args.getDouble(15);
        boolean calculatorHazardous = args.getBoolean(16);
        int kilometers = args.getInteger(17);

        accountantLogic = new AccountantLogic();

        Calculator calculator = new Calculator(pricePerKm, calculatorHazardous, kilometers);
        CustomerOrder customerOrder = new CustomerOrder(customerID, firstName, lastName, birthDate,
                email, phoneNumber, address, pickUpAddress, destinationAddress,
                postCode, amount, deliveryDate, customerOrderHazardous,
                proposedPrice, orderStatus);


        if(customerOrder.getOrderStatus().equals("Pending")){
            accountantLogic.approved(customerOrder);
            assertThat(customerOrder.getOrderStatus()).isEqualTo("Approved");
        }

    }

}