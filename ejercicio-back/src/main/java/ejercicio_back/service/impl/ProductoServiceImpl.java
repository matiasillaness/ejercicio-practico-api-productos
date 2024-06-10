package ejercicio_back.service.impl;

import ejercicio_back.entities.DetalleProducto;
import ejercicio_back.entities.EUnidadVenta;
import ejercicio_back.entities.Producto;
import ejercicio_back.model.ProductoDetalleResponse;
import ejercicio_back.model.ProductoReponse;
import ejercicio_back.model.ProductoRequest;
import ejercicio_back.repository.ProductoRepository;
import ejercicio_back.service.ProductoService;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Boolean crearProducto(ProductoRequest productoRequest) {

        if (!checkTipoProducto(productoRequest.getDetalleProducto().getUnidadVenta())) {
            JOptionPane.showMessageDialog(null, "Tipo de producto no válido");
            return false;
        }
        if (checkNombreProducto(productoRequest.getNombre())) {
            JOptionPane.showMessageDialog(null, "El producto ya existe");
            return false;
        }

        Producto producto = new Producto();
        DetalleProducto detalleProducto = new DetalleProducto();

        producto.setNombre(productoRequest.getNombre());
        producto.setPrecio(productoRequest.getPrecio());

        detalleProducto.setCantidad(productoRequest.getDetalleProducto().getCantidad());
        detalleProducto.setUnidadDeVenta(EUnidadVenta.valueOf(productoRequest.getDetalleProducto().getUnidadVenta()));

        producto.setDetalleProducto(detalleProducto);

        productoRepository.save(producto);

        return true;
    }

    @Override
    public Boolean actualizarProducto(Long id, ProductoRequest productoRequest) {
        if (!checkTipoProducto(productoRequest.getDetalleProducto().getUnidadVenta())) {
            JOptionPane.showMessageDialog(null, "Tipo de producto no válido");
            return false;
        }
        if (checkNombreProducto(productoRequest.getNombre())) {
            JOptionPane.showMessageDialog(null, "El producto ya existe");
            return false;
        }

        Producto producto = productoRepository.findById(id).orElse(null);

        if (producto == null) {
            JOptionPane.showMessageDialog(null, "El producto no existe");
            return false;
        }

        DetalleProducto detalleProducto = new DetalleProducto();

        producto.setNombre(productoRequest.getNombre());
        producto.setPrecio(productoRequest.getPrecio());

        detalleProducto.setCantidad(productoRequest.getDetalleProducto().getCantidad());
        detalleProducto.setUnidadDeVenta(EUnidadVenta.valueOf(productoRequest.getDetalleProducto().getUnidadVenta()));

        producto.setDetalleProducto(detalleProducto);

        productoRepository.save(producto);

        return true;
    }

    @Override
    public Boolean eliminarProducto(Long id) {
        try {
            productoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto");
            return false;
        }
    }

    @Override
    public List<ProductoReponse> obtenerProductos(boolean ordenarPorPrecio) {
        List<ProductoReponse> productos = new ArrayList<>();
        List<Producto> productosDB = productoRepository.findAll();

        try {
            for (Producto producto : productosDB) {
                ProductoReponse productoReponse = new ProductoReponse();
                ProductoDetalleResponse productoDetalleResponse = new ProductoDetalleResponse();

                productoReponse.setId(producto.getId());
                productoReponse.setNombre(producto.getNombre());
                productoReponse.setPrecio(producto.getPrecio());

                productoDetalleResponse.setCantidad(producto.getDetalleProducto().getCantidad());
                productoDetalleResponse.setUnidadVenta(producto.getDetalleProducto().getUnidadDeVenta().name());

                productoReponse.setDetalleProducto(productoDetalleResponse);

                productos.add(productoReponse);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los productos");
        }

        if (!ordenarPorPrecio) {
            productos.sort(Comparator.comparing(ProductoReponse::getPrecio));
        }

        return productos;
    }

    @Override
    public List<String> obtenerTiposProducto() {
        List<String> tiposProducto = new ArrayList<>();
        EUnidadVenta[] unidadVentas = EUnidadVenta.values();

        for (EUnidadVenta unidadVenta : unidadVentas) {
            tiposProducto.add(unidadVenta.name());
        }

        return tiposProducto;
    }

    private Boolean checkTipoProducto(String tipoProducto) {
        EUnidadVenta[] unidadVentas = EUnidadVenta.values();

        for (EUnidadVenta unidadVenta : unidadVentas) {
            if (unidadVenta.name().equals(tipoProducto)) {
                return true;
            }
        }

        return false;
    }

    private Boolean checkNombreProducto(String nombreProducto) {
       Producto producto = productoRepository.findByNombre(nombreProducto);
       return producto != null;
    }
}
