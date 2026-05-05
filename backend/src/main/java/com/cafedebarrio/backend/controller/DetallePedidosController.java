package com.cafedebarrio.backend.controller;

import com.cafedebarrio.backend.dto.DetallePedidoDTO;
import com.cafedebarrio.backend.service.DetallePedidoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles")
@RequiredArgsConstructor
public class DetallePedidosController {

    private final DetallePedidoService detallePedidoService; 

    @GetMapping
    public List<DetallePedidoDTO> listar() {
        return detallePedidoService.listar();
    }

    @GetMapping("/{id}")
    public DetallePedidoDTO obtener(@PathVariable Long id) {
        return detallePedidoService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        detallePedidoService.eliminar(id);
    }
}