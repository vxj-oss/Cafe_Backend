package com.cafedebarrio.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    private String descripcion;
    @Column(nullable = false)
    private Double precio;
    private Integer stock;
    @Column(name = "imagen_url")
    private String imagenUrl;
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "categoria_id, nullable = false")
    private Categoria categoria;
}
