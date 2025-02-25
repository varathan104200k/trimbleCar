package trimble.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trimble.cars.model.User;

public interface UserRepository extends JpaRepository<User, Long> { }
