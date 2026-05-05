package com.cafedebarrio.backend.service;

import com.cafedebarrio.backend.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> listar();
    CategoriaDTO obtenerPorId(Long id);
    CategoriaDTO guardar(CategoriaDTO dto);
    CategoriaDTO actualizar(Long id, CategoriaDTO dto);
    void eliminar(Long id);
}