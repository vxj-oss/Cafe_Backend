package com.cafedebarrio.backend.service;

import com.cafedebarrio.backend.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> listar();
    ProductoDTO obtenerPorId(Long id);
    ProductoDTO guardar(ProductoDTO dto);
    ProductoDTO actualizar(Long id, ProductoDTO dto);
    void eliminar(Long id);
    List<ProductoDTO> listarPorCategoria(Long categoriaId);
}