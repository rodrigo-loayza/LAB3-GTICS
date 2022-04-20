package pe.edu.pucp.lab3gtics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.lab3gtics.dto.MascotaCantidadServiciosDto;
import pe.edu.pucp.lab3gtics.entity.Cuenta;
import pe.edu.pucp.lab3gtics.entity.Mascota;
import pe.edu.pucp.lab3gtics.entity.RazaEspecie;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {

    @Query(value = "select m.idmascota as id, m.nombre as nombre, m.anho as anho, m.sexo as sexo,\n" +
            "    r.descripcion as raza, count(s.idservicio) as cantidadservicios\n" +
            "from mascota m\n" +
            "left join servicio s on (m.idmascota = s.mascota_idmascota)\n" +
            "inner join raza_especie r on (m.raza_especie_idraza = r.idraza)\n" +
            "group by m.idmascota;", nativeQuery = true)
    List<MascotaCantidadServiciosDto> obtenerListaMascotas();

    @Query(value = "select m.idmascota as id, m.nombre as nombre, m.anho as anho, m.sexo as sexo,\n" +
            "    r.descripcion as raza, count(s.idservicio) as cantidadservicios\n" +
            "from mascota m\n" +
            "left join servicio s on (m.idmascota = s.mascota_idmascota)\n" +
            "inner join raza_especie r on (m.raza_especie_idraza = r.idraza)\n" +
            "where lower(m.sexo) like %?1% \n" +
            "group by m.idmascota;", nativeQuery = true)
    List<MascotaCantidadServiciosDto> obtenerMascotaPorSexo(String sexo);

    @Query(value = "select m.idmascota as id, m.nombre as nombre, m.anho as anho, m.sexo as sexo,\n" +
            "    r.descripcion as raza, count(s.idservicio) as cantidadservicios\n" +
            "from mascota m\n" +
            "left join servicio s on (m.idmascota = s.mascota_idmascota)\n" +
            "inner join raza_especie r on (m.raza_especie_idraza = r.idraza)\n" +
            "where lower(r.descripcion) like %?1% \n" +
            "group by m.idmascota;", nativeQuery = true)
    List<MascotaCantidadServiciosDto> obtenerMascotaPorRaza(String raza);

    @Query(value = "select m.idmascota as id, m.nombre as nombre, m.anho as anho, m.sexo as sexo,\n" +
            "    r.descripcion as raza, count(s.idservicio) as cantidadservicios\n" +
            "from mascota m\n" +
            "left join servicio s on (m.idmascota = s.mascota_idmascota)\n" +
            "inner join raza_especie r on (m.raza_especie_idraza = r.idraza)\n" +
            "inner join cuenta c on (m.cuenta_idcuenta = c.idcuenta)\n" +
            "where lower(c.correo) like %?1% \n" +
            "group by m.idmascota;", nativeQuery = true)
    List<MascotaCantidadServiciosDto> obtenerMascotaPorCuenta(String cuenta);

}
