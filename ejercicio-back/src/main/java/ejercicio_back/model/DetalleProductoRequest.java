package ejercicio_back.model;

import java.math.BigDecimal;

public class DetalleProductoRequest {
    private String unidadVenta;
    private BigDecimal cantidad;

    public String getUnidadVenta() {
        return unidadVenta;
    }

    public void setUnidadVenta(String unidadVenta) {
        this.unidadVenta = unidadVenta;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
}
