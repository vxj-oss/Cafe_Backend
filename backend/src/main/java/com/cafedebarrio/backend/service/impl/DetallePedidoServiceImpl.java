package com.cafedebarrio.backend.service.impl;

import com.cafedebarrio.backend.dto.DetallePedidoDTO;
import com.cafedebarrio.backend.entity.DetallePedido;
import com.cafedebarrio.backend.repository.DetallePedidoRepository;
import com.cafedebarrio.backend.service.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detalleRepository;

    @Override
    public List<DetallePedidoDTO> listar() {
        return detalleRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DetallePedidoDTO obtenerPorId(Long id) {
        DetallePedido detalle = detalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
        return toDTO(detalle);
    }

    @Override
    public void eliminar(Long id) {
        detalleRepository.deleteById(id);
    }

    private DetallePedidoDTO toDTO(DetallePedido d) {
        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setProductoId(d.getProducto().getId());
        dto.setCantidad(d.getCantidad());
        dto.setPrecioUnitario(d.getPrecioUnitario());
        return dto;
    }
}