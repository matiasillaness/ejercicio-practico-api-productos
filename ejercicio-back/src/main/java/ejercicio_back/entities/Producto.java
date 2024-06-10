package ejercicio_back.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "detalle_producto_id", referencedColumnName = "id")
    private DetalleProducto detalleProducto;

    public Producto(String nombre, BigDecimal precio, DetalleProducto detalleProducto) {
        this.nombre = nombre;
        this.precio = precio;
        this.detalleProducto = detalleProducto;
    }


    public void addDetalleProducto(DetalleProducto detalleProducto) {
        this.detalleProducto = detalleProducto;
    }
    public void removeDetalleProducto() {
        this.detalleProducto = null;
    }
}
