# Use Case Descriptions for Create Work Order 

### Customer

Name           | Place Order
---------------|-------------
Actor          | Customer
Description    | Customer places an order
Pre-Condition  |1. Has to be signed in 
Scenario       | 1. Customer selects option to place an order <br> 2. System offers option to indicate the amount which shall be transportated <br> 3. System offers option to choose if the liquid is hazardous or not <br> 4. System offers option to select a prefered delivery date <br> 5. System offers the option to indicate a destination address <br> 6. System offers the option to enter a postcode <br> 7. Customer confirms his placements <br> 8. System displays the indicated data again <br> 9. System offers option to finally place the order
Result         | Customer placed an order
Exceptions     | 2.1 Customer types in a negative amount <br> 2.2 System displays message that the amount to be transportated has to be posititve <br> 2.3 System offers option to enter again an amount <br> 3.1 Actor did not choose the type of liquid <br> 3.2 System dispalys message that the type of liquid must be indicated <br> 3.3 System offers option to choose the type again <br> 4.1 Customer indicated a prefered delivery date in the past <br> 4.2 System displays message that the desired delivery date cannot be in the past <br> 4.3 System offers the option to select a prefered delivery date again  <br> 5.1 Customer did not indicate a destination address <br> 5.2 System displays message that a destination address must be indicated <br> System offers option to again type in a destination address <br> 6.1 Customer enters a postcode which is not available yet in the list <br> 6.2 System displays message that the postcode is not available <br> 6.3 System offers option to write a message to an employee 
Extensions     | 7.1 System offers option to cancel the process <br> 9.1 System offers option to cancel the process

Name           | Receive Quotation
---------------|-------------
Actor          | Customer
Description    | Customer receives a quotation
Pre-Condition  |1. Customer has placed an order <br> 2. Customer is signed in 
Scenario       | 1. Customer chooses the quotation tab on the panel <br> 2. System displays the quotation <br> 3. Customer selects the quotation <br> 4. System shows the quotation 
Result         | Customer received a quotation 
Exceptions     | None
Extensions     | None


### Planner

Name           | Make work order plan
---------------|-------------
Actor          | Planner
Description    | Planner creates a work order plan 
Pre-Condition  |1. Planner is signed in <br> 2. Customer has placed an order <br> 3. The customer accepted the quotation which was send to him by the accountant
Scenario       | 1. Planner sees that a customer placed an order <br> 2. System displays the order in the order tab <br> 3. Planner selects the order <br> 4. System offers option to create a work order plan <br> 5. Planner chooses the option <br> System displays the ready work order plan with all the information from the order <br> 6. System offers option to confirm the work order plan 
Result         | The planner created a work order plan
Exceptions     | None
Extensions     | None


### Accountant

Name           | Review Order
---------------|-------------
Actor          | Accountant
Description    | The accountant reviews the order placed by the customer
Pre-Condition  |1. The accountant is signed in <br> 2. The customer placed an order
Scenario       | 1. Accountant opens the order tab <br> 2. System displays the newly placed orders <br> 3. System offers option to select one order <br> 4. System offers option to check if the in the order indicated postcode is available in the list <br> 5. System offers option to confirm the order  
Result         | The accountant reviews an order
Exceptions     | 4.1 Postcode is not available in the list <br> 4.2 System offers option to send remarks in a message to the customer <br> 4.3 Use case ends here  
Extensions     | 5.1 System offers option to send remarks if there are any ambiguities in the order 

Name           | Store Order
---------------|-------------
Actor          | Accountant
Description    | The accountant stores the accepted quotation (i.e. that the customer wants the order to take place)
Pre-Condition  |1. The accountant is signed in <br> 2. The customer has placed an order <br> 3. The accountant has send a quotation <br> 4. The quotation was accepted by the customer
Scenario       | 1. Accountant sees that a quotation was accepted <br> 2. System offers option to store the order <br> 3. Accountant chooses option 
Result         | The accountant stores the order
Exceptions     | None
Extensions     | None


### Driver

Name           | Receive Work Order
---------------|-------------
Actor          | Driver
Description    | The driver receives the work order
Pre-Condition  |1. The driver is signed in <br> 2. The customer has placed an order <br> 3. The accountant send a quotation to the customer <br> 4. The customer accepted the work order <br> 5. The planner elaborated a work order plan  
Scenario       | 1. Driver opens the work order tab <br> 2. System displays all important information such as the postcode, the delivery date, the delivery time and the destination address <br> 3. System offers option to select option that the work order will be executed next <br> 4. System offers option to indicate that the work order plan was executed successfully 
Result         | The driver received the work order plan
Exceptions     | None
Extensions     | None


### For all: Sign in

Name           |Sign in 
---------------|-------------
Actor          | Customer, Planner, Accountant, Driver
Description    | The customer, the planner, the accountant and the driver sign into the application
Pre-Condition  |None
Scenario       | 1. System offers the possibility to enter the email address <br> 2. Actor types in his email <br> 3. System offers the possibility to enter the password <br> 4. Actor types in the password <br> 5. Actor selects option to sign in 
Result         | The customer, the planner, the accountant and the driver are signed into the system 
Exceptions     | 2.1 System displays message that the email address is incorrect <br> 2.2 System offers possibility to enter the email address again <br> 4.1 System displays message that the entered password was invalid <br> 4.2 System offers possibility to enter password again
Extensions     | 5.1 System offers possibility to cancel the sign in process
