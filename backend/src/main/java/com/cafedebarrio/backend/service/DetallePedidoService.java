package com.cafedebarrio.backend.service;

import com.cafedebarrio.backend.dto.DetallePedidoDTO;
import java.util.List;

public interface DetallePedidoService {
    List<DetallePedidoDTO> listar();
    DetallePedidoDTO obtenerPorId(Long id);
    void eliminar(Long id);
}