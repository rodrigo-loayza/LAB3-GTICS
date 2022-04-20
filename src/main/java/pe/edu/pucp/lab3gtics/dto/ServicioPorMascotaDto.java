package pe.edu.pucp.lab3gtics.dto;

import pe.edu.pucp.lab3gtics.entity.Mascota;

import javax.crypto.Mac;
import java.time.Instant;

public interface ServicioPorMascotaDto {
    Integer getId();
    Integer getMascota();
    Integer getCuenta();
    String getHorainicio();
    Integer getDuracion();
    String getEntrega();
    Integer getResponsable();
    String getDescripcion();

}
