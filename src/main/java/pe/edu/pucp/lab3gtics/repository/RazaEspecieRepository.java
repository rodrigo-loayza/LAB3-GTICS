package pe.edu.pucp.lab3gtics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.lab3gtics.entity.RazaEspecie;

@Repository
public interface RazaEspecieRepository extends JpaRepository<RazaEspecie, Integer> {
}
