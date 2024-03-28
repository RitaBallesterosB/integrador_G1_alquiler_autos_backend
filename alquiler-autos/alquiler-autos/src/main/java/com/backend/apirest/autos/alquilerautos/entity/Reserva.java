package com.backend.apirest.autos.alquilerautos.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservas")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "VEHICULOS_id_vehiculos")
    private Vehiculo vehiculo;//Deberia ser ID de tipo LONG??

    @ManyToOne
    @JoinColumn(name = "USUARIOS_id_usuarios")
    private Usuario usuario;//Deberia ser ID de tipo LONG??

    @Column(name="fecha_entrega")
    private Date fechaEntrega;

    @Column(name="fecha_devolucion")
    private Date fechaDevolucion;

    private String nombre;
    private String apellido;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    private String telefono;
    // Constructor, getters y setters

    @Column(name = "metodo_pago")
    private Long metodoPago;

    public Reserva() {
    }

    public Reserva(Long id, Vehiculo vehiculo, Usuario usuario, Date fechaEntrega, Date fechaDevolucion, String nombre, String apellido, String correoElectronico, String telefono, Long metodoPago) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.usuario = usuario;
        this.fechaEntrega = fechaEntrega;
        this.fechaDevolucion = fechaDevolucion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.metodoPago = metodoPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Optional<Vehiculo> vehiculo) {
        Vehiculo vehicu= vehiculo.get();
        this.vehiculo = vehicu;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Optional<Usuario> usuario) {
        Usuario user= usuario.get();
        this.usuario = user;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(Long metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", vehiculo=" + vehiculo.getId() +
                ", usuario=" + usuario.getIdUsuario() +
                ", fechaEntrega=" + fechaEntrega +
                ", fechaDevolucion=" + fechaDevolucion +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", telefono='" + telefono + '\'' +
                ", metodoPago=" + metodoPago +
                '}';
    }
}