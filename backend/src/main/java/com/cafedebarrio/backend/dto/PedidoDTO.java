package com.cafedebarrio.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Data
public class PedidoDTO {

    private Long id;
    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String clienteNombre;
    @NotBlank(message = "El celular es obligatorio")
    private String celular;
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
    private LocalDateTime fecha;
    private String estado;
    private Double total;
    
    @NotEmpty(message = "El pedido debe tener al menos un producto")
    private List<DetallePedidoDTO> detalles;
}