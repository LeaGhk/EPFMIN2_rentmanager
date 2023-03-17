package epf.rentmanager;

import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epf.rentmanager.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class VehicleServiceTest{
    private VehicleService vehicleService;
    private VehicleDao vehicleDao;

    @BeforeEach
    void init(){
        vehicleDao = mock(VehicleDao.class);
        vehicleService = new VehicleService(vehicleDao);
    }

    @Test
    void findAll_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.vehicleDao.findAll()).thenThrow(DaoException.class);
        // Then
        assertThrows(ServiceException.class, () -> vehicleService.findAll());
    }
    @Test
    void findById_success() throws DaoException {
        int id = 1;
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setModele("ModelDeOuf");
        when(this.vehicleDao.findById(1)).thenReturn(vehicle);
        Vehicle newVehicle = vehicleDao.findById(1);
        assertEquals(vehicle, newVehicle);
    }

}
