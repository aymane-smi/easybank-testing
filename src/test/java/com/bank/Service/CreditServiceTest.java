package com.bank.Service;

import com.bank.DAO.CreditDAOImpl;
import com.bank.Entity.Agency;
import com.bank.Entity.Client;
import com.bank.Entity.Credit;
import com.bank.Entity.Employee;
import com.bank.Enum.CreditStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ApplicationScoped
class CreditServiceTest {

    @Mock
    private CreditDAOImpl creditDao;
    private CreditService creditService;
    private Credit credit;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        creditService = new CreditService();
        creditService.setCreditDao(creditDao);
        credit = new Credit();
        credit.setModification_date(LocalDate.now());
        credit.setModification_time(LocalTime.MIDNIGHT);
        credit.setDuration(4);
        credit.setStatus(CreditStatus.PENDING);
        credit.setRemark("remark");
        credit.setLoanTax(Credit.TAUX);
        credit.setValue(10000);
        credit.setAgency(new Agency());
        credit.setEmployee(new Employee());
        credit.setClient(new Client());
        credit.setId(1);
    }

    @Test
    @DisplayName("simulation of a loan")
    void makeSimulation() {
        try{
            double result = creditService.makeSimulation(10000, 10);
            Assertions.assertTrue(result <= 585 );
        }catch(Exception e){
            fail("Unexcepted Exception");
        }
    }

    @Test
    @DisplayName("testing value exception during simulaion")
    void makeSimulationValueExcpetion() {
        Exception e = assertThrows(Exception.class, ()->{
            creditService.makeSimulation(0, 10);
        });
        assertEquals(e.getMessage(), "*****   LE MONTANT DOIT ETRE SUPERIEUR DE 1000   *****");
    }

    @Test
    @DisplayName("testing months exception during simulaion")
    void makeSimulationMonthExcpetion() {
        Exception e = assertThrows(Exception.class, ()->{
            creditService.makeSimulation(10000, 0);
        });
        assertEquals(e.getMessage(), "***** LE NOMBRE DE MENSUALITE DOIT ETRE SUPERIEUR DE 2   *****");
    }

    @Test
    @DisplayName("simulate the add of loan")
    void addCredit() {
        try{
            Optional<Credit> optionalCredit = Optional.of(credit);
            when(creditDao.create(credit)).thenReturn(optionalCredit);
            Credit tmp = creditService.addCredit(credit);
            assertSame(tmp, optionalCredit.get());
        }catch (Exception e){
            System.out.println(e.getMessage());
            fail("Unexcepted Exception");
        }
    }

    @Test
    @DisplayName("testing the scenario of adding empty credit")
    public void addCreditException(){
        Exception e = assertThrows(Exception.class, ()->{
            creditService.addCredit(null);
        });
        assertEquals(e.getMessage(), "***** LE CREDIT NE PEUT PAS ETRE VIDE   *****");
    }

    @Test
    @DisplayName("simulation of the delete of a loan")
    void deletecredit() {
        try {
            int result = 1;
            when(creditDao.delete(2)).thenReturn(result);
            int tmp = creditService.deletecredit(2);
            assertEquals(result, tmp);
        }catch (Exception e){
            fail("Unexcepected Exception");
        }
    }

    @Test
    @DisplayName("test the delete of a loan with invalide id")
    void deletecreditExcpetion() {
        Exception e = assertThrows(Exception.class, ()->{
            creditService.deletecredit(0);
        });
        assertEquals(e.getMessage(), "*****   ID INVALIDE   *****");
    }

    @Test
    @DisplayName("simulation of updating a loan status")
    void updateStatus() {
        try{
            credit.setStatus(CreditStatus.ACCEPTED);
            Optional<Credit> optionalCredit = Optional.of(credit);
            when(creditDao.updateStatus(2, CreditStatus.ACCEPTED)).thenReturn(optionalCredit);
            Credit tmp = creditService.updateStatus(2, CreditStatus.ACCEPTED);
            assertEquals(optionalCredit.get(), tmp);
        }catch(Exception e){
            fail("Unexcepected Exception");
        }
    }

    @Test
    @DisplayName("test of update loan status with invalid id")
    void updateStatusIdException() {
        Exception e = assertThrows(Exception.class, ()->{
            creditService.updateStatus(0, CreditStatus.ACCEPTED);
        });
        assertEquals(e.getMessage(), "*****   STATUS|ID EST INVALIDE    *****");
    }

    @Test
    @DisplayName("test of update loan status with empty status")
    void updateStatusStatusException() {
        Exception e = assertThrows(Exception.class, ()->{
            creditService.updateStatus(2, null);
        });
        assertEquals(e.getMessage(), "*****   STATUS|ID EST INVALIDE    *****");
    }

    @Test
    @DisplayName("simulation of searching loan by id")
    void findById() {
        try{
            Optional<Credit> optionalCredit = Optional.of(credit);
            when(creditDao.findById(2)).thenReturn(optionalCredit);
            Credit tmp = creditService.findById(2);
            assertEquals(tmp, optionalCredit.get());
        }catch(Exception e){
            fail("Unexcepected Exception");
        }
    }

    @Test
    @DisplayName("test searching loan by invalid id")
    void findByIdException() {
        Exception e = assertThrows(Exception.class, ()->{
            creditService.findById(0);
        });
        assertEquals(e.getMessage(), "*****   ID EST INVALIDE    *****");
    }

    @Test
    @DisplayName("simulation of searching loan(s) by date")
    void testFindByDate() {
        try{
            List<Credit> list = new ArrayList<>();
            list.add(credit);
            when(creditDao.findByDate(LocalDate.now())).thenReturn(list);
            List<Credit> tmp = creditService.findByDate(LocalDate.now());
            assertEquals(tmp, list);
        }catch(Exception e){
            fail("Unexcepected Exception");
        }
    }

    @Test
    @DisplayName("test searching loan by invalid date")
    void findByDateException() {
        Exception e = assertThrows(Exception.class, ()->{
            creditService.findByDate(null);
        });
        assertEquals(e.getMessage(), "*****   DATE EST INVALIDE    *****");
    }

    @Test
    @DisplayName("simulation of searching loan(s) by status")
    void findByStatus() {
        try{
            List<Credit> list = new ArrayList<>();
            list.add(credit);
            when(creditDao.findByStatus(CreditStatus.PENDING.name())).thenReturn(list);
            List<Credit> tmp = creditService.findByStatus(CreditStatus.PENDING.name());
            assertEquals(tmp, list);
        }catch(Exception e){
            fail("Unexcepected Exception");
        }
    }

    @Test
    @DisplayName("test searching loan by empty status")
    void findByStatusException() {
        Exception e = assertThrows(Exception.class, ()->{
            creditService.findByStatus("");
        });
        assertEquals(e.getMessage(), "*****   STATUS EST INVALIDE    *****");
    }

    @Test
    @DisplayName("simulation of searching all loans")
    void findAll() {
        try{
            List<Credit> list = new ArrayList<>();
            list.add(credit);
            when(creditDao.findAll()).thenReturn(list);
            List<Credit> tmp = creditService.findAll();
            assertEquals(tmp, list);
        }catch(Exception e){
            fail("Unexcepected Exception");
        }
    }
}