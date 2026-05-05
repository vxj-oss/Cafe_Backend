package com.cafedebarrio.backend.service.impl;

import com.cafedebarrio.backend.dto.DetallePedidoDTO;
import com.cafedebarrio.backend.dto.PedidoDTO;
import com.cafedebarrio.backend.entity.DetallePedido;
import com.cafedebarrio.backend.entity.Pedido;
import com.cafedebarrio.backend.entity.Producto;
import com.cafedebarrio.backend.repository.PedidoRepository;
import com.cafedebarrio.backend.repository.ProductoRepository;
import com.cafedebarrio.backend.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Override
    public List<PedidoDTO> listar() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoDTO obtenerPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        return toDTO(pedido);
    }

    @Override
    @Transactional
    public PedidoDTO guardar(PedidoDTO dto) {

        if (dto.getDetalles() == null || dto.getDetalles().isEmpty()) {
            throw new RuntimeException("El pedido debe tener al menos un detalle");
        }

        Pedido pedido = new Pedido();
        pedido.setFecha(LocalDateTime.now());
        pedido.setClienteNombre(dto.getClienteNombre());
        pedido.setCelular(dto.getCelular());
        pedido.setDireccion(dto.getDireccion());
        pedido.setEstado("PENDIENTE");

        List<DetallePedido> detalles = dto.getDetalles().stream().map(d -> {

            Producto producto = productoRepository.findById(d.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: id " + d.getProductoId()));
            if (producto.getStock() < d.getCantidad()) {
                throw new RuntimeException(
                    "Stock insuficiente para el producto '" + producto.getNombre() +
                    "'. Stock disponible: " + producto.getStock() +
                    ", cantidad solicitada: " + d.getCantidad()
                );
            }
            producto.setStock(producto.getStock() - d.getCantidad());
            productoRepository.save(producto);

            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setPedido(pedido);

            return detalle;

        }).collect(Collectors.toList());

        pedido.setDetalles(detalles);

        double total = detalles.stream()
                .mapToDouble(d -> d.getCantidad() * d.getPrecioUnitario())
                .sum();

        pedido.setTotal(total);

        return toDTO(pedidoRepository.save(pedido));
    }

    @Override
    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }

    private PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setFecha(pedido.getFecha());
        dto.setTotal(pedido.getTotal());
        dto.setClienteNombre(pedido.getClienteNombre());
        dto.setCelular(pedido.getCelular());
        dto.setDireccion(pedido.getDireccion());
        dto.setEstado(pedido.getEstado()); 

        if (pedido.getDetalles() != null) {
            dto.setDetalles(
                    pedido.getDetalles().stream().map(d -> {
                        DetallePedidoDTO det = new DetallePedidoDTO();
                        det.setProductoId(d.getProducto().getId());
                        det.setCantidad(d.getCantidad());
                        det.setPrecioUnitario(d.getPrecioUnitario());
                        det.setSubtotal(d.getCantidad() * d.getPrecioUnitario());
                        return det;
                    }).collect(Collectors.toList())
            );
        }

        return dto;
    }
    @Override
    public PedidoDTO actualizarEstado(Long id, String estado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        List<String> estadosValidos = List.of("PENDIENTE", "EN_PREPARACION", "ENTREGADO");
        if (!estadosValidos.contains(estado)) {
            throw new RuntimeException("Estado inválido: " + estado +
                ". Valores permitidos: " + estadosValidos);
        }
        pedido.setEstado(estado);
        return toDTO(pedidoRepository.save(pedido));
    }
}