package com.cafedebarrio.backend.controller;

import com.cafedebarrio.backend.dto.CategoriaDTO;
import com.cafedebarrio.backend.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> listar() {
        return categoriaService.listar();
    }

    @GetMapping("/{id}")
    public CategoriaDTO obtener(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id);
    }

    @PostMapping
    public CategoriaDTO crear(@RequestBody CategoriaDTO dto) {
        return categoriaService.guardar(dto);
    }

    @PutMapping("/{id}")
    public CategoriaDTO actualizar(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        return categoriaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
    }
}