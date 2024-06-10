package ejercicio_back.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;


public enum EUnidadVenta {
    METRO("Metro"),
    KILO("Kilo"),
    MILILITRO("Mililitro"),
    LITRO("Litro");

    private final String unidad;

    EUnidadVenta(String unidad) {
        this.unidad = unidad;
    }

    public String getUnidad() {
        return unidad;
    }
}