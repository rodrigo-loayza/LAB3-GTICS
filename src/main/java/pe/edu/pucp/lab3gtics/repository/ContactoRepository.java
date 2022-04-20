package pe.edu.pucp.lab3gtics.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.lab3gtics.dto.ContactoMascota;


import java.util.List;

@Repository
public interface ContactoRepository {

    @Query(value = "select cu.idcuenta as contacto, count(ma.idmascota) as mascotas,  cu.correo, cu.direccion, cu.telefono from cuenta as cu\n" +
            "inner join mascota ma on cu.idcuenta=ma.cuenta_idcuenta\n" +
            "group by idcuenta;", nativeQuery = true)
    List<ContactoMascota> obtenerContactoMascota();

}
