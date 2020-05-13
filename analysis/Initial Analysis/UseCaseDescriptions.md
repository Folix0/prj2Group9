# Use Case Descriptions


### Use Case Descriptions for Sign In 

Name           | Sign In
------------   | ------------
Actor          |Customer, Driver, Planner, Accountant
Description    |An actor signs into the application 
Pre-condition  |None
Scenario       |1. Actor types in his email adress <br> 2. Actor types in his password <br> 3. User signs in 
Result         |The actor is signed in  
Exceptions     |1. System messages that the email address was invalid <br> 1.1 Actor can enter email address again <br> 2. System messages that the password is invalid <br> 2.1. Actor can type in his password again 
Extensions     |None


### Use Case Descriptions for the Customer

Name           | Place Order
------------   | ------------
Actor          |Customer
Description    |Customer places an order
Pre-condition  |1. Has to be signed in 
Scenario       |1. Customer indicates the desired delivery date <br> 2. System shows the entered date <br> 3. Customer types in the amount that should be transportated <br> 4. System displays the amount <br> 5. Customer chooses if the liquid is hazardous or non hazardous <br> 6. System displays the selected option <br> 7. Customer indicates the delivery address <br> 8. System displays the delivery address <br> 9. Customer indicates the postcode of the delivery address <br> 10. System displays the postcode <br> 11. Customer selects option to create the order
Result         |Customer places an order 
Exceptions     |11. Customer selects option to cancel the process/ cancel the order <br> 11.1. Use caase ends here  
Extensions     |None

Name           | Receive Quotation
------------   | ------------
Actor          |Customer
Description    |Customer receives a quotation from Accountant
Pre-condition  |1. Has to be signed in <br> 2. Has already placed an order
Scenario       |1. 
Result         |Customer receives a quotation 
Exceptions     |
Extensions     |

Name           | Accept Quotation
------------   | ------------
Actor          |Customer
Description    |Customer accepts a quotation
Pre-condition  |1. Has to be signed in 
Scenario       |1. 
Result         |Customer accepts a quotation  
Exceptions     |
Extensions     |

Name           | Reject Quotation
------------   | ------------
Actor          |Customer
Description    |Customer rejects a quotation
Pre-condition  |1. Has to be signed in 
Scenario       |1. 
Result         |Customer rejects a quotation  
Exceptions     |
Extensions     |

Name           | Receive Invoice
------------   | ------------
Actor          |Customer
Description    |Customer receives an invoice from Accountant
Pre-condition  |1. Has to be signed in <br> 2. Has already placed an order <br> 3. The order has been delivered/ customer received the delivery
Scenario       |1. 
Result         |Customer receives an invoice 
Exceptions     |
Extensions     |


Name           | Receive remarks on Order
------------   | ------------
Actor          |Customer
Description    |Customer receives remarks on the order from Accountant
Pre-condition  |1. Has to be signed in <br> 2. Has already placed an order
Scenario       |1. 
Result         |Customer receives remarks on what needs to be changed in the order
Exceptions     |
Extensions     |


Name           | Receive Notification when Delivery is delayed
------------   | ------------
Actor          |Customer
Description    |Customer receives a notification when the order is delayed
Pre-condition  |1. Has to be signed in <br> 2. Has already placed an order <br> 3. Has received and accepeted the quotation 
Scenario       |1. 
Result         |Customer receives notification that the delivery is delayed
Exceptions     |
Extensions     |

### Use Case Descriptions for the Planner

Name           | Assign Truck to an Order 
------------   | ------------
Actor          |Planner
Description    |Planner can assign a truck to an order
Pre-condition  |1. Has to be signed in <br> 2. An order was placed by the customer <br> 3. The customer received and accepted the quotation 
Scenario       |1. 
Result         |Planner assigns a truck to an order
Exceptions     |
Extensions     |

Name           | Assign Trailer to an Order 
------------   | ------------
Actor          |Planner
Description    |Planner can assign a trailer to an order
Pre-condition  |1. Has to be signed in <br> 2. An order was placed by the customer <br> 3. The customer received and accepted the quotation 
Scenario       |1. 
Result         |Planner assigns a trailer to an order
Exceptions     |
Extensions     |

Name           | Assign (a) Driver(s) to an Order 
------------   | ------------
Actor          |Planner
Description    |Planner can assign driver(s) to an order
Pre-condition  |1. Has to be signed in <br> 2. An order was placed by the customer <br> 3. The customer received and accepted the quotation
Scenario       |1. System lists all the drivers in a table <br> 2. System lists the driver's name or the driver's ID <br> 3. System lists if the driver is available at the time <br> 4. System indicates the license of the driver (i.e. if the driver can tranport hazardous liquids or not) <br> 5. Planner selects the possiblity to assign a driver to an order  
Result         |Planner assigns driver(s) to an order/ a driver was assigned to an order
Exceptions     | 5.1 Planner selects to assign a driver to an order who is not available <br> 5.2 System gives a warning that the driver is not available <br> 5.3 Use case ends here 
Extensions     |None

Name           | View the Truck Maintenance Status
------------   | ------------
Actor          |Planner
Description    |Planner can see the maintenance status of a truck
Pre-condition  |1. Has to be signed in 
Scenario       |1. 
Result         |Planner can see the maintenance status of a truck
Exceptions     |
Extensions     |

Name           | View Truck and Trailer Information 
------------   | ------------
Actor          |Planner
Description    |Planner can see the necessary information about trucks and trailers
Pre-condition  |1. Has to be signed in 
Scenario       |1. 
Result         |Planner can see the most important information regarding the trucks and trailers
Exceptions     |
Extensions     |

Name           | Create the Work Order
------------   | ------------
Actor          |Planner
Description    |Planner ccreates the work oder plan 
Pre-condition  |1. Has to be signed in <br> 2. Order has been placed by the customer <br> 3. The quotation send by the accountant has been accepted by the customer
Scenario       |1. 
Result         |Planner creates the work order plan 
Extensions     |
Exceptions     |

### Use Case Descriptions for the Driver

Name           | Receive Cargo Information 
------------   | ------------
Actor          |Driver
Description    |Driver can see information about the cargo in the truck/trailer
Pre-condition  |1. Has to be signed in <br> 2. Order has been placed by the customer <br> 3. Quotation send by the accountant has been accepted by the customer 
Scenario       |1. 
Result         |Driver sees information regarding the cargo  
Extensions     |
Exceptions     |


Name           | See assigned Truck and Trailer
------------   | ------------
Actor          |Driver
Description    |Driver can see which truck he is assigned to 
Pre-condition  |1. Has to be signed in <br> 2. An order has been placed <br> 3. A quotation was accepted by the customer <br> 4. The planner assigned a truck and a trailer <br> 5. Driver opened the Orders Tab in the application
Scenario       |1. System shows the truck and trailer identification <br> 2. Driver reads the information
Result         |Driver sees the truck which was assigned to him to execute the delivery
Extensions     |
Exceptions     |

Name           | View Target Destination 
------------   | ------------
Actor          |Driver
Description    |Driver can see to which destination(s) he has to drive 
Pre-condition  |1. Has to be signed in <br> 2. An offer was placed by the customer <br> 3. The customer received and accepted the quotation
Scenario       |1. 
Result         |Driver sees which destinations he has to drive to
Extensions     |
Exceptions     |

Name           | Receive work order 
------------   | ------------
Actor          |Driver
Description    |Driver receives the work order
Pre-condition  |1. Has to be signed in <br> 2. The customer placed an order <br> 3. The customer received and accepted the quotation
Scenario       |1. 
Result         |Driver receives the work order
Extensions     |
Exceptions     |

Name           | Send Incident Message 
------------   | ------------
Actor          |Driver
Description    |Driver sends a message that an incident occured 
Pre-condition  |1. Has to be signed in 
Scenario       |1. Driver types in the email address(es) of the employee(s) <br> 2. System displays the email address(es) <br> 3. Driver types in a messsage <br> 4. System displays the message <br> 5. Driver selects to send the message 
Result         |Driver sends an incident message
Extensions     |None
Exceptions     |None

### Use Case Descriptions for the Accountant 

Name           | See Order History
------------   | ------------
Actor          |Accountant
Description    |Accountant sees the order history of customers 
Pre-Conditon   |1. Has to be signed in <br> 2. The customer has already placed an order
Scenario       |1. 
Result         |Accountant sees the order history of past orders from teh customers 
Extensions     |
Exceptions     |

Name           | Add Locations to the List of available Destinations
------------   | ------------
Actor          |Accountant
Description    |Accountant can update the list of available destinations
Pre-Condition  |1. Has to be signed in 
Scenario       |1. The accountant opens the locations menu <br> 2. The system shows the already available locations and postcodes <br> 3. The accountant types in the new location and the corresponding postcode <br> 4. The accountant confirms his changes <br> 5. The system adds the newly entered data to the list 
Result         |Accountant adds destinations to the list where available destinations are stored 
Extensions     |
Exceptions     |

Name           | Receive Incident Message 
------------   | ------------
Actor          |Accountant
Description    |Accountant receives an incident message 
Pre-Condition  | 1. Has to be signed in 
Scenario       |1. 
Result         |Accountant receives an incident message
Extensions     |
Exceptions     |

Name           | Create Invoice
------------   | ------------
Actor          |Accountant
Description    |Accountant creates an invoice 
Pre-Condition  |1. Has to be signed in <br> 2. An offer was placed by the customer <br> 3. The customer received and accepted the quotation <br> 4. The customer received the delivery 
Scenario       |1. Accountant types in the customer's name <br> 2. Accountant types in the customer's email address <br> 3. Accountant types in the order ID <br> 4. System offers option to select if the correspoding liquid is hazardous <br> 5. Accountant indicates the amount tranportated <br> 6. System offers option to select the date of shipping <br> 7. Accountant indicates the total price <br> 8. Accountant indiactes the email address of the customer <br> 9. System offers option to send the invoice 
Result         |Accountant receives an incident message
Extensions     |None
Exceptions     |8.1 No email was indicated <br> 8.2 System informs the accountant that an email address must be indicated <br> 8.3 System offers option to enter email again

Name           | Review Order
------------   | ------------
Actor          |Accountant
Description    |Accountant reviews an order 
Pre-Condition  |1. Has to be signed in <br> 2. Order has been placed by the customer
Scenario       |1. 
Result         |Accountant reviews an order
Extensions     |
Exceptions     |
