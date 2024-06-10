package ejercicio_back.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DetalleProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "detalleProducto")
    private Producto producto;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidad_de_venta")
    private EUnidadVenta unidadDeVenta;

    @Column(name = "cantidad", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal cantidad;
}
