package com.cafedebarrio.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cliente_nombre", nullable = false)
    private String clienteNombre;
    @Column(nullable = false)
    private String celular;
    private String direccion;

    private LocalDateTime fecha;

    @Builder.Default
    private String estado = "PENDIENTE";

    private Double total;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles;
}