package ejercicio_back.service;

import ejercicio_back.model.ProductoDetalleResponse;
import ejercicio_back.model.ProductoReponse;
import ejercicio_back.model.ProductoRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductoService {
    Boolean crearProducto(ProductoRequest productoRequest);
    Boolean actualizarProducto(Long id, ProductoRequest productoRequest);
    Boolean eliminarProducto(Long id);
    List<ProductoReponse> obtenerProductos(boolean ordenarPorPrecio);
    List<String> obtenerTiposProducto();
}
