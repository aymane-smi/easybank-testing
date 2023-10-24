package com.bank.Servlets;

import com.bank.Entity.Client;
import com.bank.Service.AgencyService;
import com.bank.Service.ClientService;
import com.bank.Service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.owasp.encoder.Encode;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    @Inject
    private AgencyService agencyService;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private Client client;
    @Inject
    private ClientService clientService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("agencies", agencyService.find());
        request.setAttribute("emploies", employeeService.findAll());
        request.setAttribute("clients", clientService.findAllClients());
        request.getRequestDispatcher("/client.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            client.setFirstName(
                    Encode.forHtmlContent(request.getParameter("firstName"))
            );
            client.setLastName(
                    Encode.forHtmlContent(request.getParameter("lastName"))
            );
            client.setPhone(
                    Encode.forHtmlContent(request.getParameter("phone"))
            );
            client.setAddress(
                    Encode.forHtmlContent(request.getParameter("address"))
            );
            client.setBirthDay(
                    LocalDate.parse(request.getParameter("birthDay"))
            );
            client.setAgency(
                    agencyService.findByCode(request.getParameter("agency"))
            );
            client.setEmployee(
                    employeeService.findByRegistrationNbr(
                            Integer.parseInt(request.getParameter("employee"))
                    )
            );
            boolean result = clientService.addClient(client);
            if(result == false)
                throw new Exception("wa rah khaouy");
            request.setAttribute("created", true);
            request.getRequestDispatcher("/client.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("created", false);
            request.getRequestDispatcher("/client.jsp").forward(request, response);
        }
    }
}