package com.bank.Service;

import com.bank.DAO.AgencyDAOImpl;
import com.bank.Entity.Agency;
import com.bank.Entity.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AgencyService {
    @Inject
    private AgencyDAOImpl agencyDao;
    public void setAgencyDao(AgencyDAOImpl agencyDao) {
        this.agencyDao = agencyDao;
    }

    public Optional<Agency> create(Agency agency) throws Exception{
            if(agency == null)
                throw new Exception("*****   L'AGENCE NE PAS ETRE NULL   *****");
            Optional<Agency> optionalAgency = agencyDao.create(agency);
            return optionalAgency;
    }

    public boolean delete(String code) throws Exception{
            if(code == "")
                throw new Exception("*****   LE CODE D'AGENCE NE PAS ETRE VIDE   *****");
            if(agencyDao.delete(code) == 1)
                return  true;
        return false;
    }

    public Optional<Agency> update(Agency agency) throws Exception{
            if(agency == null)
                throw new Exception("*****   L'AGENCE NE PAS ETRE NULL   *****");
            Optional<Agency> optionalAgency = agencyDao.update(agency);
            return optionalAgency;
    }

    public List<Agency> find(){
        Optional<List<Agency>> optionalAgency = agencyDao.find();
        System.out.println("==>");
        System.out.println(optionalAgency.get().size());
        System.out.println("==>");
        return optionalAgency.orElse(new ArrayList<Agency>());
    }

    public List<Agency> findByAddress(String address) throws Exception {
        if(address == "")
            throw new Exception("*****   L'ADRESSE D'AGENCE NE PAS ETRE VIDE   *****");
        return agencyDao.findByAddress(address).get();
    }

    public Agency findByCode(String code) throws Exception {
        if(code == "")
            throw new Exception("*****   CODE D'AGENCE NE PAS ETRE VIDE   *****");
        return agencyDao.findByCode(code).orElse(null);
    }
}
