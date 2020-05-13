# Use Case Descriptions Create Work Order


## Customer 

Name           | Place an order
-------------- | ---------------
Actor          | Customer
Description    | Customer places an order 
Pre-Condition  | 1. Needs to be signed in 
Scenario       |1. System offers option to select a preferred delivery date <br> 2. System offers option to indicate the preferred delivery time <br> 3. Customer indicates the amount which shall be transportated <br> 4. System offers option to select the unit in which the amount information is given (i.e. tons, kilos, litres) <br> 5. Customer selects if the liquid is hazardous or not <br> 6. Customer indicates the pickup address of the liquid <br> 7. System offers option to indicate the postcode of the pickup address <br> 8. Customer indicates the delivery address for the liquid <br> 9. System offers option to indicate the postcode of the delivery address <br> Customer selects option to create the order
Result         | Customer has made and placed an order 
Exceptions     |- 
Extensions     |- 


Name           | Receive invoice
-------------- | ---------------
Actor          | Customer
Description    | Customer can see his invoices
Pre-Condition  | 1. Needs to be signed in <br> 2. Customer has placed an order <br> 3. Customer received a quotation and accepted it <br> 4. Customer has received his delivery 
Scenario       |1. System displays the order ID <br> 2. System diplays the order status (i.e. if it is in progress or already finsihed) <br> 3. System displays the date <br> 4. Customer selects option to see the invoice 
Result         | Customer can see details of invoice
Exceptions     |2.1 System displays that the order status is in progress <br> 2.2 System disables the option to see details of the invoice
Extensions     |- 

Name           |See order status
-------------- | ---------------
Actor          | Customer
Description    | Customer can see the status of the order
Pre-Condition  | 1. Needs to be signed in <br> 2. Customer has placed an order <br> 3. Customer received a quotation and accepted it <br> 4. Customer has received his delivery 
Scenario       |1. System displays the order ID <br> 2. System diplays the order status (i.e. if it is in progress or already finsihed) <br> 3. System displays the date 
Result         | Customer can see the order status
Exceptions     |- 
Extensions     |- 

Name           |Receive Quotation 
-------------- | ---------------
Actor          | Customer
Description    | Customer receives and sees a quotation 
Pre-Condition  |1. Customer is signed in <br> 2. Customer has placed an order <br> 3. Accountant send a quotation  
Scenario       | 1. System displays the order ID <br> 2. System displays the items (i.e. if it is a (non) hazardous liquid) <br> 3. System displays the amount which shall be transportated <br> 4. System displays the target destination <br> 5. System displays the proposed price for the transportation <br> 6. System displays the deadline when the transportation shall be made <br> 7. System offers option to either decline or accept the quotation 
Result         | Customer can see the quotation 
Exceptions     |- 
Extensions     |7.1 Customer rejects the quotation (declines the quotation) <br> 7.2 Use case ends here


## Driver 

Name           |See work order plan 
-------------- | ---------------
Actor          | Driver 
Description    | Driver sees the details form the work order plan 
Pre-Condition  |1. Customer placed a quotation <br> 2. Accountant send a quotation to the customer <br> 3. Customer accepted the quotation 
Scenario       |1. System displays the work order ID <br> 2. System displays the type of liquid (i.e. if it is hazardous or not) <br> 3. System displays the amount which is to be transportated <br> 4. System displays the truck which shall be used for executing the delivery <br> 5. System displays the trailer which shall be used for executing the delivery  
Result         | Driver can see the results from the work order plan 
Exceptions     |- 
Extensions     |- 

Name           |See the route 
-------------- | ---------------
Actor          | Driver 
Description    | Driver sees the details of the route 
Pre-Condition  |1. Driver needs to be signed in <br> 2. Planner created a work order plan
Scenario       |1. System displays the trailer load destintation <br> 2. System displays the target destination of teh delivery <br> 3. System displays the postcode of the target destination <br> 4. System displays the amount to unload <br> 5. System displays the date when the delivery shall be executed <br> 6. System displays the time when the delivery shall arrive the customer 
Result         | Driver can see the route he needs to take to finsih the delivery
Exceptions     |- 
Extensions     |- 

Name           |Send a message to employees 
-------------- | ---------------
Actor          | Driver 
Description    | Driver can send a message to the employees
Pre-Condition  |1. Driver is signed in 
Scenario       |1. Driver indicates the email address(es) of the employee <br> 2. System offers field to type in the message <br> 3. System offers option to send the message
Result         | Driver can see the results from the work order plan 
Exceptions     |3.1 System displays message that no email address was indicated <br> 3.2 System offers option to enter an address 
Extensions     |- 
