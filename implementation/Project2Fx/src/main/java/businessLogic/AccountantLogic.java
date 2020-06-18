package businessLogic;

import entities.Calculator;
import entities.CustomerOrder;
import restDao.CustomerOrderRestDao;


public class AccountantLogic {


    /**
     * @param calculator
     * @return double calculated price
     *
     * calcualtes the proposed price
     * hazaroud is calculated with a plus percentage of 1.5%
     * simply multiplies the number of kilometers with a price per kilometer
     * the ppk can change, the accountant has to decide to the ppk
     * round the price to 2 places after the comma
     *
     */
    public double calculatePrice(Calculator calculator) {
        if (calculator.getHazardousPercentage()) {
            double km = calculator.getKms();
            double pricePKm = calculator.getPricePKm();
            double price = km * pricePKm * 1.5;
            price = Math.round(100.0 * price) / 100.0;
            return price;
        } else {
            double km = calculator.getKms();
            double ppk = calculator.getPricePKm();
            double price = km * ppk * 1;
            price = Math.round(100.0 * price) / 100.0;
            return price;
        }
    }

    /**
     * @param customerOrder
     * set the order status on rejected
     */
    public void reject(CustomerOrder customerOrder){
        if(customerOrder.getOrderStatus().equals("Pending")) {
            customerOrder.setOrderStatus("Rejected");

            System.out.println(customerOrder.getOrderStatus());

            CustomerOrderRestDao customerOrderRestDao = new CustomerOrderRestDao();
            customerOrderRestDao.update(customerOrder);
        } else {

        }
    }

    /**
     * @param customerOrder
     * sets the order status on pending
     */
    public void approved(CustomerOrder customerOrder){
        if(customerOrder.getOrderStatus().equals("Pending")) {
            customerOrder.setOrderStatus("Approved");

            CustomerOrderRestDao customerOrderRestDao = new CustomerOrderRestDao();
            customerOrderRestDao.update(customerOrder);
        } else {

        }
    }





}
