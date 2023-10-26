# EasyBank Testing Double

## INTRO TO DOUBLE TEST

test double is a type of testing that replace the real function implementation with a temporary to prevent testing data being injecting to the production.
test double can be divided into 4 pieces:
 - *STUB*: stub is a technic used to simulate the execution of a method by mock the class thta contain this method.
 - *DUMMY*: a technic that require testing only the fields of tested method.
 - *FAKE*: a technic that give us the possiblity to test fake data/object in out method in order to see the final result in the final end.we can use **in-memory** database also any type of **Collections** to save the fake data.
 - *SPY*: a technic that allows us to record all the indirect I/O during the execution in order to test if some conditions, params... are being called during the previous execution.
## PROJECT TESTING

in this project i,m going to use both **STUB** and **DUMMY** test.
the tested classes are: ***AgencyService***, ***CreditService***.
you can check the folder ```/src/test/java/com.bank```