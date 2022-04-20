package pe.edu.pucp.lab3gtics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.lab3gtics.entity.OpcionServicio;

@Repository
public interface OpcionServicioRepository extends JpaRepository<OpcionServicio,Integer> {
}
