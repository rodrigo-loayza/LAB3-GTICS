package pe.edu.pucp.lab3gtics.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.lab3gtics.entity.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Integer> {
}
