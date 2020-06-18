package businessLogic;

import entities.CustomerOrder;
import entities.Driver;
import entities.Trailer;
import entities.Truck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PlannerLogic {
    /*
    public static void defineOrderStatus(ObservableList<CustomerOrder> customerOrders, Optional<Collection<CustomerOrder>> maybeList) {
        customerOrders = FXCollections.observableArrayList();


        for (CustomerOrder order : maybeList.get()) {
            if (order.getOrderStatus().equals("Accepted")) {
                customerOrders.add(order);
            }
        }
        if (!customerOrders.isEmpty()) {
            System.out.println(customerOrders);
        } else {
            System.out.println("Error could not retrieve data from backend (CustomerOrder)");
        }
    }*/



    public static boolean filterTruckTable(String typedText, Truck truck) {
        if (String.valueOf(truck.isAvailable()).contains(typedText)) {
            return true;
        }
        if (String.valueOf(truck.getMileage()).indexOf(typedText) != -1) {
            return true;
        }
        if (String.valueOf(truck.getTruckWeight()).indexOf(typedText) != -1) {
            return true;
        }
        if (truck.getTruckPickupLocation().indexOf(typedText) != -1) {
            return true;
        }
        if (truck.getTruckLicensePlate().indexOf(typedText) != -1) {
            return true;
        }
        if (String.valueOf(truck.getMaintenanceCheckdate()).indexOf(typedText) != -1) {
            return true;
        }
        return false;
    }

    public static boolean filterDriverTable(String typedText, Driver driver) {
        if (driver.getPhoneNumber().toLowerCase().contains(typedText)) {
            return true;
        }

        if (String.valueOf(driver.getEmail()).indexOf(typedText) != -1) {
            return true;
        }

        if (String.valueOf(driver.isAvailable()).indexOf(typedText) != -1) {
            return true;
        }
        if (String.valueOf(driver.isHasHazardousLicense()).indexOf(typedText) != -1) {
            return true;
        }
        return false;
    }


    public static boolean filterTrailerTable(String typedText, Trailer trailer) {

        if (String.valueOf(trailer.isCleaned()).indexOf(typedText) != -1) {
            return true;
        }
        if (String.valueOf(trailer.getCapacity()).indexOf(typedText) != -1) {
            return true;
        }
        if (trailer.getTrailerLicencePlate().indexOf(typedText) != -1) {
            return true;
        }
        if (String.valueOf(trailer.getMaintenanceCheckdate()).indexOf(typedText) != -1) {
            return true;
        }
        if (String.valueOf(trailer.getTrailerWeight()).indexOf(typedText) != -1) {
            return true;
        }
        if (String.valueOf(trailer.isAvailable()).indexOf(typedText) != -1) {
            return true;
        }
        if (String.valueOf(trailer.isHazardous()).indexOf(typedText) != -1) {
            return true;
        }
        return false;
    }
}
