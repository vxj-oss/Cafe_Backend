package com.cafedebarrio.backend.service;

import com.cafedebarrio.backend.dto.PedidoDTO;

import java.util.List;

public interface PedidoService {

    List<PedidoDTO> listar();
    PedidoDTO obtenerPorId(Long id);
    PedidoDTO guardar(PedidoDTO dto);
    void eliminar(Long id);
    PedidoDTO actualizarEstado(Long id, String estado);
    
}