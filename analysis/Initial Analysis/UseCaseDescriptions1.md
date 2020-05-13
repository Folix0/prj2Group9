# Use Case Descriptions 

## Accountant


#### Review Order

**Name:** Review Order <br> 
**Actor:** Accountant <br> 
**Description:** Accountant checks the new arrived order of the customer <br>
**Pre-condition:** 1. Accountant needs to be signed in <br>
**Scenario:**  1. Accountant enters the order tab in the panel <br> 
2. System displays the order information/details such as the Customer ID, Order ID, amount, destination address, destination postcode, pickup address, delivery date and if the liquid to transport is hazardous or not <br> 
3. Accountant checks if the postcode is available in the list where the postcodes are available <br>
4. System offers option to enter the order ID <br>
5. System offers option to accept the order <br>
**Exceptions:** None <br>
**Extensions:** 3.1 The postcode indicated in the order is not yet available <br>
3.2 System offers option to type in the corresponding postcode and to add the new postcode to the list <br>
6. System offers option to send remarks/questions to the customer <br>
6.1 System offers option to type in a text <br>
6.2 System offers option to enter the email address of the corresponding cutsomer <br>
6.3 System offers opton to send the remarks to the customer 


#### Create Invoice

**Name:** Create Invoice <br>
**Actor:** Accountant <br>
**Description:** Accountant creates an invoice for the customer <br>
**Pre-condition:** 1. Accountant must be logged in <br>
2. Customer has placed an order <br>
3. Delivery is executed and received the customer <br>
**Scenario:** 1. System displays information of the order for the shipment and the customer <br>
2. System offers option to enter the customer's ID <br>
3. System offers option to enter the csutomer's name  <br> 
4. System offers option to enter the order ID <br>
5. Accountant selects if the liquid transportated in the order was hazardous or not <br>
6. System offers option to indicate the amount transportated <br>
7. Accountant selects the date of shipping <br>
8. System offers option to enter the total price for the shipment <br> 
9. System offers option to enter the customer's email address <br>
10. System offers option to send the invoice to the customer <br>
**Result:** Accountant created the invoice for the customer <br>
**Exceptions:** 1. At least one information was not indicated <br>
2. System dispalys message that all fields must be indicated <br>
3. System offers option to check the missing fields and to add the information to the field(s)
**Extensions:** None



#### Calculate Price

**Name:** Calculate a price to offer the customer <br>
**Actor:** Accountant <br>
**Description:** Accountant calcualtes a proposed price <br>
**Pre-condition:** 1. Accountant must be logged in <br>
2. Customer has placed an order <br>
**Scenario:** 1. Accountant indicates the kilometers the truck needs to drive <br>
2. Accountant indicates a price per kilometer <br>
3. Accountant selects if the liquid to be tranportated is hazardous or not <br>
4. Accountant indicates the amount in tons which shall be transportated <br>
5. System offers option to calculate the price on the basis of the information indicated <br>
6. System displays the price which it calcuated <br>
**Result:** The system calcualted a price to offer to the customer <br>
**Exceptions:** 1. At least one information was not indicated <br> 
2. System displays message that all fields must contain information and must be filled in <br> 
3. System offers option to fill out the missing fields again <br> 
**Extensions:** None



#### Create and Send Quotation
**Name:** Accountant creates and sends a quotation to the customer <br>
**Actor:** Accountant <br>
**Description:** Accoutant creates and subsequently sends the quotation to the customer <br>
**Pre-condition:** 1. Accountant is logged in <br> 
2. Customer has placed an order <br>
**Scenario:** 1. Accountant indicates the customer's name <br>
2. Accountant indicates the customer's ID <br>
3. Accountant indicates the customer's email <br>
4. Accountant indicated the Order ID <br> 
5. Accountant indicates the amount which shall be tranportated <br> 
6. Accountant indicates the pickup location <br> 
7. Accountant indicates the destination address <br> 
8. System offers option to select if the liquid is hazardous <br> 
9. Accountant types in the calcuated price <br>
10. System offers option to select if a discount will be granted <br> 
11. System offers option to type in the new total price after the discount was recognized <br> 
12. System offers option to type in the customer's email address <br> 
13. System offers option to send the quotation to the customer <br> 
**Result:** Accountant created and send the invoice to the customer <br> 
**Exceptions:** 1-9.1: At least one of the fields 1 to 9 is not indicated and filled out <br> 
1-9.2 System displays error message that fields 1-9 have to tbe indicated <br> 
1-9.3 System offers option to fill out these fields again <br> 
12. Email address not indicated <br> 
12.1 System displays message that the emai must be indicated <br> 
12.2 System offers option to fill out this field again <br>
**Extensions:** None


#### Grant discount 



## Planner



## Driver


## Owner


## Customer

#### Place an order

**Name:** Place an order <br>
**Actor:** Customer <br>
**Description:** Customer places an order <br>
**Pre-Condition:** Customer is logged in <br>
**Scenario:** 1. Customer enters his full name <br> 
2. Customer enters his Birth year <br>
3. Customer enters his phone number <br> 
4. Customer enters his address <br>
5. Customer enters the pickup location for the liquid <br> 
6. Customer enters his destination address <br>
7. Customer enters his destination postcode <br>
8. Customer enters the amount which shall be indicated in tons <br>
9. System offers option to select the Date of Shipment <br>
10. Customer indicates the delivery time <br> 
11. System offers option to sleect if the liquid is hazardous <br> 
12. System offers option to send the order <br>
**Result:** Customer placed an order <br> 
**Exceptions:** 1-11.1: At least one information was not indicated <br>
1-11.2: System offers option to enter again <br> 
**Extensions:** None


## Sign In 
