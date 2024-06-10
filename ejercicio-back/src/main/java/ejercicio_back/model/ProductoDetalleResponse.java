package ejercicio_back.model;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDetalleResponse {
    private String unidadVenta;
    private BigDecimal cantidad;
}
