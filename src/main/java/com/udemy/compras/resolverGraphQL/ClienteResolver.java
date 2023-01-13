package com.udemy.compras.resolverGraphQL;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.compras.entity.Cliente;
import com.udemy.compras.entity.Compra;
import com.udemy.compras.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteResolver implements GraphQLResolver<Cliente> {

    @Autowired
    private CompraService compraService;

    public List<Compra> compras(Cliente c) {
        return compraService.findAllByCliente(c);
    }

    public List<Compra> comprasQuantidade(Cliente c, int quantidade) {
        return compraService.findAllByClienteAndQuantidade(c, quantidade);
    }
}
