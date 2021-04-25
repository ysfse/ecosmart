package ecosmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ecosmart.entities.CarbonBalance;

public interface CarbonBalanceRepository extends JpaRepository<CarbonBalance,Long> {

}
