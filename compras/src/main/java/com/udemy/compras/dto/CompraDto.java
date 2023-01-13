package com.udemy.compras.dto;

import lombok.Data;

@Data
public class CompraDto {
    private Long id;
    private Integer quantidade;
    private String status;
    private Long clienteId;
    private Long produtoId;
}
