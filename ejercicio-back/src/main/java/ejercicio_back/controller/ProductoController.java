package ejercicio_back.controller;


import ejercicio_back.model.ProductoReponse;
import ejercicio_back.model.ProductoRequest;
import ejercicio_back.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Crear producto", description = "Crea un nuevo producto", tags = {"productos"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Producto creado"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Producto ya existe"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error interno")
            })
    @PostMapping
    public ResponseEntity<Boolean> crearProducto(@RequestBody ProductoRequest productoRequest) {
        return ResponseEntity.ok(productoService.crearProducto(productoRequest));
    }

    @Operation(summary = "Obtener tipos de producto", description = "Obtiene los tipos de producto", tags = {"productos"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Tipos de producto encontrados"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error interno")
            })
    @GetMapping(value = "/tipos")
    public ResponseEntity<List<String>> obtenerTiposProducto() {
        return ResponseEntity.ok(productoService.obtenerTiposProducto());
    }

    @Operation(summary = "Obtener productos", description = "Obtiene todos los productos y se puede ordenar por precio", tags = {"productos"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Productos encontrados"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error interno")
            })
    @GetMapping
    public ResponseEntity<List<ProductoReponse>> obtenerProductos(@RequestParam Boolean ordenarPorPrecio){
        return ResponseEntity.ok(productoService.obtenerProductos(ordenarPorPrecio));
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto", tags = {"productos"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Producto eliminado"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error interno")
            })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> eliminarProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.eliminarProducto(id));
    }

    @Operation(summary = "Actualizar producto", description = "Actualiza un producto", tags = {"productos"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Producto actualizado"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error interno")
            })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Boolean> actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequest productoRequest) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, productoRequest));
    }
}
