package com.cafedebarrio.backend.controller;

import com.cafedebarrio.backend.dto.PedidoDTO;
import com.cafedebarrio.backend.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public List<PedidoDTO> listar() {
        return pedidoService.listar();
    }

    @GetMapping("/{id}")
    public PedidoDTO obtener(@PathVariable Long id) {
        return pedidoService.obtenerPorId(id);
    }

   @PostMapping
    public PedidoDTO crear(@Valid @RequestBody PedidoDTO dto) {
        return pedidoService.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
    }

    @PatchMapping("/{id}/estado")
    public PedidoDTO actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String estado = body.get("estado");
        return pedidoService.actualizarEstado(id, estado);
    }
    
}