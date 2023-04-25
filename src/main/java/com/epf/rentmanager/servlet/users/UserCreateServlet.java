package com.epf.rentmanager.servlet.users;

import com.epf.rentmanager.exception.MailUseException;
import com.epf.rentmanager.exception.MajeurException;
import com.epf.rentmanager.exception.ServiceException;
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
            if(ClientValidator.userIs18years(c)){
                throw new MajeurException("L'utilisateur doit être majeur");
            }else if(ClientValidator.verifMailUtilise(c, clientService.findAll())) {
                throw new MailUseException("Cette adresse mail est déjà utilisée");
            }

            clientService.create(c);
            response.sendRedirect("/rentmanager/users");
        }
        catch (ServiceException e) {
            throw new RuntimeException(e);
        } catch (MajeurException e) {
            request.setAttribute("message", "L'utilisateur doit être majeur");
            request.setAttribute("nom", request.getParameter("last_name"));
            request.setAttribute("prenom", request.getParameter("first_name"));
            request.setAttribute("email", request.getParameter("email"));
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
        } catch (MailUseException e) {
            request.setAttribute("message", "Cette adresse mail est déjà utilisée");
            request.setAttribute("nom", request.getParameter("last_name"));
            request.setAttribute("prenom", request.getParameter("first_name"));
            request.setAttribute("naissance", request.getParameter("naissance"));
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
        }

    }

}
