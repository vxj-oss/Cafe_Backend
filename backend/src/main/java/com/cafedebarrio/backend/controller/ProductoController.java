package com.cafedebarrio.backend.controller;

import com.cafedebarrio.backend.dto.ProductoDTO;
import com.cafedebarrio.backend.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    //@GetMapping
    //public List<ProductoDTO> listar() {
       // return productoService.listar();
    //}

    @GetMapping("/{id}")
    public ProductoDTO obtener(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    public ProductoDTO crear(@RequestBody ProductoDTO dto) {
        return productoService.guardar(dto);
    }

    @PutMapping("/{id}")
    public ProductoDTO actualizar(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        return productoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }

    @GetMapping
    public List<ProductoDTO> listar(@RequestParam(required = false) Long categoria) {
        if (categoria != null) {
            return productoService.listarPorCategoria(categoria);
        }
        return productoService.listar();
    }
}