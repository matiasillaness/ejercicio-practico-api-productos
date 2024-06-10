package ejercicio_back.model;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoReponse {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private ProductoDetalleResponse detalleProducto;
}
