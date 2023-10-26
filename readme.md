# EasyBank Testing Double

## INTRO TO DOUBLE TEST

test double is a type of testing that replace the real function implementation with a temporary to prevent testing data being injecting to the production.
test double can be divided into 4 pieces:
 - *STUB*: stub is a technic used to simulate the execution of a method by mock the class thta contain this method.
 - *DUMMY*: a technic that require testing only the fields of tested method.
 - *FAKE*: a technic that give us the possiblity to test fake data/object in out method in order to see the final result in the final end.we can use **in-memory** database also any type of **Collections** to save the fake data.
 - *SPY*: a technic that allows us to record all the indirect I/O during the execution in order to test if some conditions, params... are being called during the previous execution.

## Junit

JUnit is a unit testing open-source framework for the Java programming language. Java Developers use this framework to write and execute automated tests. In Java, there are test cases that have to be re-executed every time a new code is added. This is done to make sure that nothing in the code is broken.

#### how to add junit to your project
 ```
 <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
 </dependency>
 <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
 </dependency>
 <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
 </dependency>
 ```

in the whole test classes in the project we are going to use the following annotations, methods:

- `@BeforeEach`: let us define some proccess to do before each test
- `@Test`: enable use to run the test
- `assert*`: let us do do some comparing, checking.like `assertSame`, `assertEqual`, `assertThrow`

## MOCKITO
Mockito is a java based mocking framework, used in conjunction with other testing frameworks such as JUnit and TestNG. It internally uses Java Reflection API and allows to create objects of a service. A mock object returns a dummy data and avoids external dependencies. It simplifies the development of tests by mocking external dependencies and apply the mocks into the code under test.

#### how to add mockito to your project

```
<dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>5.6.0</version>
            <scope>test</scope>
</dependency>
```

in the whole test classes in the project we are going to use the following annotations, methods:

- `@Mock`: give us the possibilty to create a mock of a nethod that we want to use
- `when(-).thenReturn(-)`: macking a simulation of the mocked attribute with a given end result

## PROJECT TESTING

in this project i'm going to use both **STUB** and **DUMMY** test.
the tested classes are: ***AgencyService***, ***CreditService***.
you can check the folder ```/src/test/java/com.bank```

## PROJECT COVERAGE
![coverage.png](coverage.png)