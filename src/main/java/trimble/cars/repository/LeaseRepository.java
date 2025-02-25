package trimble.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trimble.cars.model.Lease;

public interface LeaseRepository extends JpaRepository<Lease, Long> { }
