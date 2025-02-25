package trimble.cars;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import trimble.cars.model.Car;
import trimble.cars.model.Status;
import trimble.cars.repository.CarRepository;
import trimble.cars.service.CarService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarsApplicationTests {

	@Mock
	private CarRepository carRepository;

	@InjectMocks
	private CarService carService;

	@Test
	void contextLoads() {
	}


}
