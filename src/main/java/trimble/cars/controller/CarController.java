package trimble.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trimble.cars.model.Car;
import trimble.cars.model.Lease;
import trimble.cars.model.User;
import trimble.cars.repository.UserRepository;
import trimble.cars.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}/status")
    public String getCarStatus(@PathVariable Long id) {
        return carService.getCarStatus(id);
    }

    @PostMapping("/register")
    public Car registerCar(@RequestBody Car car, @RequestParam Long ownerId) {
        User owner = userRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User not found"));
        return carService.registerCar(car, owner);
    }

    @GetMapping("/{id}/leases")
    public List<Lease> getLeaseHistory(@PathVariable Long id) {
        return carService.getLeaseHistory(id);
    }
}
