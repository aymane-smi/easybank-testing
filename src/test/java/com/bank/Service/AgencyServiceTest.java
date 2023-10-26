package com.bank.Service;

import com.bank.DAO.AgencyDAOImpl;
import com.bank.DAO.CreditDAO;
import com.bank.DAO.CreditDAOImpl;
import com.bank.Entity.Agency;
import com.bank.Entity.Client;
import com.bank.Entity.Credit;
import com.bank.Entity.Employee;
import com.bank.Enum.CreditStatus;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AgencyServiceTest {
    @Mock
    private AgencyDAOImpl agencyDAO;
    private AgencyService agencyService;
    private Agency agency;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        agencyService = new AgencyService();
        agencyService.setAgencyDao(agencyDAO);
        agency = new Agency();
        agency.setCode("AGENCY1");
        agency.setAddress("address");
        agency.setName("name");
        agency.setPhone("123456789");
    }

    @Test
    @DisplayName("simulate the creation of new Agency")
    void create() {
        try{
            Optional<Agency> optionalAgency = Optional.of(agency);
            when(agencyDAO.create(agency)).thenReturn(optionalAgency);
            Optional<Agency> agency1 = agencyService.create(agency);
            assertSame(agency, agency1.get());
            //assertSame(agency, agency2);
        }catch(Exception e){
            fail(e.getMessage());
            fail("Unexcepted exception");
        }
    }

    @Test
    @DisplayName("simulation of creating empty agency")
    public void createException(){
        Exception e = assertThrows(Exception.class, ()->{
            agencyService.create(null);
        });
        assertEquals(e.getMessage(), "*****   L'AGENCE NE PAS ETRE NULL   *****");
    }

    @Test
    @DisplayName("simulate the delete of an agency")
    void delete() {
        try{
            int result = 1;
            when(agencyDAO.delete(agency.getCode())).thenReturn(result);
            boolean tmp = agencyService.delete(agency.getCode());
            assertSame(result == 1, tmp);
        }catch(Exception e){
            System.out.println(e.getMessage());
            fail("Unexcepted Exception");
        }
    }

    @Test
    @DisplayName("simulate the delete of an agency that doesn't existe")
    void deleteFlase() {
        try{
            int result = 0;
            when(agencyDAO.delete("AGENCY2")).thenReturn(result);
            boolean tmp = agencyService.delete("AGENCY2");
            assertEquals(false, tmp);
        }catch(Exception e){
            System.out.println(e.getMessage());
            fail("Unexcepted Exception");
        }
    }

    @Test
    @DisplayName("simulation of updating empty agency")
    void deleteNonValideCode(){
        Exception e = assertThrows(Exception.class, ()->{
            agencyService.delete("");
        });
        assertEquals(e.getMessage(), "*****   LE CODE D'AGENCE NE PAS ETRE VIDE   *****");
    }


    @Test
    @DisplayName("simulate the update of an agency")
    void update() {
        try{
            agency.setName("name*");
            Optional<Agency> tmp = Optional.of(agency);
            when(agencyDAO.update(agency)).thenReturn(tmp);
            Optional<Agency> tmp1 = agencyService.update(agency);
            assertSame(tmp.get(), agency);
        }catch(Exception e){
            System.out.println(e.getMessage());
            fail("Unexcepted Excpetion");
        }
    }

    @Test
    @DisplayName("simulate the update of an empty agency")
    void updateEmpty(){
        Exception e = assertThrows(Exception.class, ()->{
            agencyService.update(null);
        });
        assertEquals(e.getMessage(), "*****   L'AGENCE NE PAS ETRE NULL   *****");
    }

    @Test
    @DisplayName("simulate the search of all agency")
    void find() {
        try{
            List<Agency> list = new ArrayList<>();
            list.add(agency);
            Optional<List<Agency>> lists = Optional.of(list);
            when(agencyDAO.find()).thenReturn(lists);
            List<Agency> agencies = agencyService.find();
            assertEquals(agencies.size(), lists.get().size());
        }catch(Exception e){
            System.out.println(e.getMessage());
            fail("Unexcepted exception");
        }
    }

    @Test
    @DisplayName("simulate the search by address")
    void findByAddress() {
        try{
            List<Agency> list = new ArrayList<>();
            list.add(agency);
            Optional<List<Agency>> lists = Optional.of(list);
            when(agencyDAO.findByAddress(agency.getAddress())).thenReturn(lists);
            List<Agency> agencies = agencyService.findByAddress(agency.getAddress());
            assertEquals(agencies.size(), lists.get().size());
        }catch(Exception e){
            System.out.println(e.getMessage());
            fail("Unexcepted exception");
        }
    }

    @Test
    void findByCode() {
        try{
            Optional<Agency> optionalAgency = Optional.of(agency);
            when(agencyDAO.findByCode(agency.getCode())).thenReturn(optionalAgency);
            Agency tmp = agencyService.findByCode(agency.getCode());
            assertSame(tmp, optionalAgency.get());
        }catch(Exception e){
            System.out.println(e.getMessage());
            fail("Unexcepted exception");
        }
    }
}