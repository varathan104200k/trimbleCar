package trimble.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trimble.cars.model.Car;
import trimble.cars.model.Lease;
import trimble.cars.model.User;
import trimble.cars.repository.CarRepository;
import trimble.cars.repository.LeaseRepository;
import jakarta.persistence.*;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private LeaseRepository leaseRepository;

    // Check Car Status
    public String getCarStatus(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        return car.getStatus();
    }

    // Register and enroll the car (for Car Owner)
    public Car registerCar(Car car, User owner) {
        car.setOwner(owner);
        return carRepository.save(car);
    }

    // Lease History (for Car Owner)
    public List<Lease> getLeaseHistory(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        return car.getLeases();
    }
}