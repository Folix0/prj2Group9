# Test Scenarios 

1. Planner can lookup available trailer <br>
    a. lookup "Trailer" should show that trailer

2. Planner can lookup clean trailer <br>
    a. lookup "clean trailers" should show all clean trailers

3. Planner can lookup hazardous trailers <br>
    a. lookup "hazardous trailers" should show all hazardous trailers

4. Planner can lookup trailer capacity <br>
    a. lookup "get capacity" should show the trailer capacity

5. Planner can lookup available drivers <br>
    a. lookup "get available drivers" should show all available drivers

6. Planner can lookup drivers with hazardous license <br>
    a. lookup "get available drivers" should show all available drivers
    b. lookup "get hazardous" should show only the drivers with hazardous license

7. Adding load address <br>
    a. input "LoadingAddress" should store the address of loading

8. Unload amount <br>
    a. input "amount" should unload certain amount

9. Add cleaned and available trailer <br>
    a. input "Trailer" should store that trailer with cleaned value that equals true

10. Add trailer that matches the liquid hazardous/Non hazardous to order <br>
    a. input "Trailer" should add the trailer only if the hazardous status is equal 
       to the liquid hazardous status of an order

11. Add matching driver hazardous/Non hazardous to order <br>
    a. input "Trailer" should add the trailer only if the hazardous status is equal 
       to the drivers hazardous status

12. Trailer capacity should be more or equal to the loading amount <br>
    a. input "load 1000l " should throw an error if the trailer capacity is less than the 
       amount loaded

13. Driver can lookup trailer location <br>
    a. input "Trailer" should show trailer location

14. Driver can lookup delivery destination <br>
    a. input "order plan name" should show the destination

15. Driver can input new hazardous license acquirement <br>
    a. input "set" should renew license for the driver
    
 
 <p> 

16. Check if the amount was indicated <br> 
17. Check if the information regarding the liquid are indicated <br>
18. Check if a total amount for the transportation is indicated <br> 
19. Check if the customer number is indicated <br> 
20. Check if the customer number is available (i.e. if it exists) <br> 
21. Check if the order number was indicated <br> 
23. Check if a destination address was indicated <br> 
24. Check if the postcode is indicated  <br> 
25. Check if the postcode in the list is available 
