package trimble.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trimble.cars.model.Car;
import trimble.cars.model.Lease;
import trimble.cars.model.User;
import trimble.cars.repository.CarRepository;
import trimble.cars.repository.LeaseRepository;
import trimble.cars.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaseService {
    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    // Start Lease (for End Customer)
    public Lease startLease(Long carId, Long customerId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        User customer = userRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

        if (customer.getLeases().size() >= 2) {
            throw new RuntimeException("Customer can have a maximum of 2 active leases.");
        }

        Lease lease = new Lease();
        lease.setCar(car);
        lease.setCustomer(customer);
        lease.setStartDate(LocalDate.now());
        leaseRepository.save(lease);

        car.setStatus("On Lease");
        carRepository.save(car);

        return lease;
    }

    // End Lease (for End Customer)
    public Lease endLease(Long leaseId) {
        Lease lease = leaseRepository.findById(leaseId).orElseThrow(() -> new RuntimeException("Lease not found"));

        lease.setEndDate(LocalDate.now());
        leaseRepository.save(lease);

        Car car = lease.getCar();
        car.setStatus("Ideal");
        carRepository.save(car);

        return lease;
    }

    // Lease History (for End Customer)
    public List<Lease> getLeaseHistory(Long customerId) {
        User customer = userRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        return customer.getLeases();
    }
}
