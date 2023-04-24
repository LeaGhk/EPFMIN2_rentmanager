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

@WebServlet("/users/update")
public class UserUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Autowired
    private ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    int id = 0;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("idc", id);
            request.setAttribute("client", clientService.findById(id));

            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(request, response);

        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter("last_name");
        String firstName = request.getParameter("first_name");
        String email = request.getParameter("email");
        LocalDate naissance = LocalDate.parse(request.getParameter("naissance"));

        Client c = new Client(id, lastName, firstName, naissance, email);
        clientService.update(c);

        response.sendRedirect("/rentmanager/users");
    }


}
