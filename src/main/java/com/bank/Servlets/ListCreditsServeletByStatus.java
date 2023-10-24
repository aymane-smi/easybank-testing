package com.bank.Servlets;

import com.bank.Enum.CreditStatus;
import com.bank.Service.CreditService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/find-status")
public class ListCreditsServeletByStatus extends HttpServlet {
    @Inject
    private CreditService creditService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(request.getParameter("status").isEmpty())
                response.sendRedirect("/ListCredits");
            request.setAttribute("credits", creditService.findByStatus((request.getParameter("status"))));
            request.getRequestDispatcher("/ListCreditsByStatus.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            creditService.updateStatus(
                    Integer.parseInt(req.getParameter("id")),
                    CreditStatus.valueOf(
                            req.getParameter("status")
                    )
            );
            resp.sendRedirect("/ListCredits");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}