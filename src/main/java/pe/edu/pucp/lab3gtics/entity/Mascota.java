package pe.edu.pucp.lab3gtics.entity;

import javax.persistence.*;

@Entity
@Table(name = "mascota")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmascota", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "anho", nullable = false, length = 45)
    private String anho;

    @Lob
    @Column(name = "historia", nullable = false)
    private String historia;

    @Lob
    @Column(name = "observaciones", nullable = false)
    private String observaciones;

    @Column(name = "sexo", nullable = false, length = 45)
    private String sexo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "raza_especie_idraza", nullable = false)
    private RazaEspecie razaespecie;

    @Column(name = "raza_otros", length = 45)
    private String razaotros;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_idcuenta")
    private Cuenta cuenta;

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuentaIdcuenta) {
        this.cuenta = cuentaIdcuenta;
    }

    public String getRazaotros() {
        return razaotros;
    }

    public void setRazaotros(String razaotros) {
        this.razaotros = razaotros;
    }

    public RazaEspecie getRazaespecie() {
        return razaespecie;
    }

    public void setRazaespecie(RazaEspecie razaespecie) {
        this.razaespecie = razaespecie;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}