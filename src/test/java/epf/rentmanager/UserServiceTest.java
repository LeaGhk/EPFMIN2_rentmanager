package epf.rentmanager;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;

public class UserServiceTest{
    private ClientService clientService;
    private ClientDao clientDao;

    @BeforeEach
    void init(){
        clientDao = mock(ClientDao.class);
        clientService = new ClientService(clientDao);
    }

    @Test
    void findAll_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.clientDao.findAll()).thenThrow(DaoException.class);
        // Then
        assertThrows(ServiceException.class, () -> clientService.findAll());
    }
    @Test
    void findById_success() throws DaoException {
        int id = 1;
        Client client = new Client();
        client.setId(1);
        client.setNom("Doh");
        when(this.clientDao.findById(1)).thenReturn(client);
        Client newClient = clientDao.findById(1);
        assertEquals(client, newClient);
    }

}
