package pe.edu.pucp.lab3gtics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.pucp.lab3gtics.dto.ServicioPorMascotaDto;
import pe.edu.pucp.lab3gtics.entity.Servicio;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Servicio, Integer> {

    @Query(value = "SELECT \n" +
            "s.idservicio as id,\n" +
            "s.mascota_idmascota as mascota,\n" +
            "s.cuenta_idcuenta as cuenta,\n" +
            "s.hora_inicio as horainicio,\n" +
            "s.duracion as duracion,\n" +
            "s.entrega as entrega,\n" +
            "s.responsable_idresponsable as responsable,\n" +
            "op.descripcion\n" +
            "FROM servicio as s \n" +
            "inner join opcion_servicio as ops on (s.idservicio = ops.servicio_idservicio)\n" +
            "inner join opcion as op on (ops.opcion_idopcion = op.idopcion)\n" +
            "where s.mascota_idmascota = ?1", nativeQuery = true)
    List<ServicioPorMascotaDto> obtenerServicioMascota(Integer idmascota);

}
