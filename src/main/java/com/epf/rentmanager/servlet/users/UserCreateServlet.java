package com.epf.rentmanager.servlet.users;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private ClientService clientService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            String lastName = request.getParameter("last_name");
            String firstName = request.getParameter("first_name");
            String email = request.getParameter("email");
            LocalDate naissance = LocalDate.parse(request.getParameter("naissance"));


            Client c = new Client(lastName, firstName, naissance, email);
            clientService.create(c);
        }
        catch (ServiceException e) {
            //catch le probleme pousser dans la requete et afficher sur le site le prb
            // utilisateur entre un mail ou un numéro invalide pour les voitchur
            throw new RuntimeException(e);
        }

//        this.doGet(request, response);
        response.sendRedirect("/rentmanager/users");
    }


}
