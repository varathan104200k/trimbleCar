package trimble.cars.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import trimble.cars.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> { }
