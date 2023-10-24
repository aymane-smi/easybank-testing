package com.bank.Service;

import com.bank.DAO.CreditDAOImpl;
import com.bank.Entity.Credit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ApplicationScoped
class CreditServiceTest {
    private CreditService creditService = new CreditService();

    @Test
    void findByDate() {
        try{
            List<Credit> list = creditService.findByDate(LocalDate.of(2023, 11, 11));
            Assertions.assertEquals(list.size(), 1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void makeSimulation() {
        try{
            creditService.setCreditDao(new CreditDAOImpl());
            double result = creditService.makeSimulation(10000, 10);
            Assertions.assertTrue(result <= 585 );
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}