package trimble.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trimble.cars.model.Car;
import trimble.cars.model.Lease;
import trimble.cars.model.User;
import trimble.cars.repository.CarRepository;
import trimble.cars.repository.LeaseRepository;
import trimble.cars.repository.UserRepository;
import trimble.cars.service.CarService;
import trimble.cars.service.LeaseService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CarService carService;

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private UserRepository userRepository;

    // Admin can perform all operations available to Car Owner and End Customer
    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/leases")
    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    @PostMapping("/cars/register")
    public Car registerCar(@RequestBody Car car, @RequestParam Long ownerId) {
        User owner = userRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User not found"));
        return carService.registerCar(car, owner);
    }

    @GetMapping("/healthCheck")
    public String getHealth(){
        return "Running";
    }
}
