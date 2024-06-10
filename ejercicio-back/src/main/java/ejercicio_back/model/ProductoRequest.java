package ejercicio_back.model;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductoRequest {
    private String nombre;
    private BigDecimal precio;
    private DetalleProductoRequest detalleProducto;
}
