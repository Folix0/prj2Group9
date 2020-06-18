package businessLogic;

import entities.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Driver;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.doesNotHave;
import static org.junit.jupiter.api.Assertions.*;

public class PlannerLogicTest {

    private PlannerLogic plannerLogic = new PlannerLogic();


/*
    @ParameterizedTest
    @CsvSource({
            "      1,1,'45-54-PP','TT-78-OP','Venlo',456.4,true,true,'Address','4561','2020-11-08'",
            "      2,2,'45-OO-66','II-78-OP','Nijmegen',789.45,false,true,'Address1','1531','2022-11-08'",
            "      3,3,'78-MM-66','ZZ-99-PP','Amsterdam',481.1,true,false,'Address2','4895','2025-11-08'",
            "      4,4,'78-34-ZZ','AA-99-TT','Den Haag',123.4,false,false,'Address3','7894','2044-11-08'"

    })
    public void PlannerLogicWorkOrderPlan(ArgumentsAccessor args){

        int orderID = args.getInteger(0);
        int driverID = args.getInteger(1);
        String trailerLicencePlate = args.getString(2);
        String truckLicencePlate = args.getString(3);
        String trailerPickupLocation = args.getString(4);
        double amount = args.getDouble(5);
        boolean hazardous = args.getBoolean(6);
        boolean cleaned = args.getBoolean(7);
        String address = args.getString(8);
        String postcode = args.getString(9);
        LocalDate deliveryDate = args.get(10, LocalDate.class);
    }*/

    @ParameterizedTest
    @CsvSource({
            "       1,'WE-RR-15',true,234.4,2,'Street',2000-12-12,'qwer','tr','2','4.4','Str','WE-','12'",
            "       2,'AA-TT-55',false,4568.56,23,'Street1',2000-11-12,'asd','false','2','45','Str','AA-','12'",
            "       3,'CC-NN-44',true,1230.4,44,'Street2',2000-10-12,'xcvhh','tr','4','123','Str','NN-','12'",
            "       4,'ZZ-RR-22',false,7851.4,12,'Street3',2002-12-12,'edfg','fa','2','851','Str','22','12'"
    })

    public void TruckTest(ArgumentsAccessor args){

        int id = args.getInteger(0);
        String truckLicencePlate = args.getString(1);
        boolean available = args.getBoolean(2);
        double truckWeight = args.getDouble(3);
        int mileage = args.getInteger(4);
        String pickupLocation = args.getString(5);
        LocalDate checkDate = args.get(6, LocalDate.class);

        String wrong = args.getString(7);
        String right = args.getString(8);
        String right1 = args.getString(9);
        String right2 = args.getString(10);
        String right3 = args.getString(11);
        String right4 = args.getString(12);
        String right5 = args.getString(13);


        Truck truck = new Truck(id,truckLicencePlate,available,truckWeight,mileage,pickupLocation,checkDate);

        assertThat(plannerLogic.filterTruckTable(wrong,truck)).isFalse();
        assertThat(plannerLogic.filterTruckTable(right,truck)).isTrue();
        assertThat(plannerLogic.filterTruckTable(right1,truck)).isTrue();
        assertThat(plannerLogic.filterTruckTable(right2,truck)).isTrue();
        assertThat(plannerLogic.filterTruckTable(right3,truck)).isTrue();
        assertThat(plannerLogic.filterTruckTable(right4,truck)).isTrue();
        assertThat(plannerLogic.filterTruckTable(right5,truck)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "       1,'XX-XX-XX',true,true,true,456.45,'Home',2000-12-12,486,'qwer','tr','6.4','XX','2-1','48','tr','true'",
            "       2,'XX-XX-AA',true,false,false,123.45,'Home1',2001-12-12,245,'asd','tr','3.4','XX','1-1','45','false','fa'",
            "       3,'XX-XX-CC',false,true,true,4568.45,'Home2',2002-11-12,1245,'ertrgtg','fal','8.4','XX','2-1','45','tr','true'",
            "       4,'XX-XX-UU',true,true,false,4585.5,'Home3',2005-12-12,4864,'ergfd','tr','45','XX','12-1','48','tr','false'",
    })
    public void TrailerTest(ArgumentsAccessor args){

        int id = args.getInteger(0);
        String truckLicencePlate = args.getString(1);
        boolean cleaned = args.getBoolean(2);
        boolean available = args.getBoolean(3);
        boolean hazardous = args.getBoolean(4);
        double capacity = args.getDouble(5);
        String pickupLocation = args.getString(6);
        LocalDate checkDate = args.get(7, LocalDate.class);
        double weight = args.getDouble(8);


        String wrong = args.getString(9);
        String right = args.getString(10);
        String right1 = args.getString(11);
        String right2 = args.getString(12);
        String right3 = args.getString(13);
        String right4 = args.getString(14);
        String right5 = args.getString(15);
        String right6 = args.getString(16);

        Trailer trailer = new Trailer(id,truckLicencePlate,cleaned,available,hazardous,capacity,pickupLocation,checkDate,weight);


        assertThat(plannerLogic.filterTrailerTable(wrong,trailer)).isFalse();
        assertThat(plannerLogic.filterTrailerTable(right,trailer)).isTrue();
        assertThat(plannerLogic.filterTrailerTable(right1,trailer)).isTrue();
        assertThat(plannerLogic.filterTrailerTable(right2,trailer)).isTrue();
        assertThat(plannerLogic.filterTrailerTable(right3,trailer)).isTrue();
        assertThat(plannerLogic.filterTrailerTable(right4,trailer)).isTrue();
        assertThat(plannerLogic.filterTrailerTable(right5,trailer)).isTrue();
        assertThat(plannerLogic.filterTrailerTable(right6,trailer)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "     1,true,true,'Dorothee','Sch',2002-12-12,'@Web','1234567','Street','´qwerr','456','@We','true','tr'",
            "     2,true,true,'Doro','Schall',2008-12-12,'1@Internet','153127','Street1','sdfvfgrtg','531','@Int','true','tr'",
            "     3,false,true,'Anna','Schaa',2010-12-12,'2@Comp','12134567','Street2','sdfg','345','omp','true','tr'",
            "     4,false,true,'Lena','Schll',2012-12-12,'3@Laptop','14511167','Street3','wertgh','167','top','true','tr'"

    })
    public void DriverTest(ArgumentsAccessor args){

        int id = args.getInteger(0);
        boolean available = args.getBoolean(1);
        boolean hazardous = args.getBoolean(2);
        String firstName = args.getString(3);
        String lastName = args.getString(4);
        LocalDate birthDate = args.get(5, LocalDate.class);
        String email = args.getString(6);
        String phoneNumber = args.getString(7);
        String address = args.getString(8);

        String wrong = args.getString(9);
        String right = args.getString(10);
        String right2 = args.getString(11);
        String right3 = args.getString(12);
        String right4 = args.getString(13);

        entities.Driver driver = new entities.Driver(id,available,hazardous,firstName,
                lastName,birthDate,email,phoneNumber,address);
        System.out.println(driver);

        assertThat(plannerLogic.filterDriverTable(wrong,driver)).isFalse();
        assertThat(plannerLogic.filterDriverTable(right,driver)).isTrue();
        assertThat(plannerLogic.filterDriverTable(right2,driver)).isTrue();
        assertThat(plannerLogic.filterDriverTable(right3,driver)).isTrue();
        assertThat(plannerLogic.filterDriverTable(right4,driver)).isTrue();



    }
}