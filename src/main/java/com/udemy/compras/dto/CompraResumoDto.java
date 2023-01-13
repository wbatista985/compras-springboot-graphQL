package com.udemy.compras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompraResumoDto {
    private Long compraId;
    private String cliente;
    private String produto;
    private int quantidade;
}
