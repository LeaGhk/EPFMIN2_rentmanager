package com.epf.rentmanager.servlet.users;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.exception.UserIs18yearsException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String lastName = request.getParameter("last_name");
            String firstName = request.getParameter("first_name");
            String email = request.getParameter("email");
            LocalDate naissance = LocalDate.parse(request.getParameter("naissance"));

            Client c = new Client(id, lastName, firstName, naissance, email);
            if(ClientValidator.userIs18years(c)){
                throw new UserIs18yearsException("L'utilisateur doit être majeur");
            }
            clientService.update(c);

            response.sendRedirect("/rentmanager/users");
        } catch (UserIs18yearsException e) {
            response.sendRedirect("/rentmanager/users?message=Modification échouée : L'utilisateur doit être majeur");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }


}
