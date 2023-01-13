package com.udemy.compras.resolverGraphQL;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.compras.entity.Cliente;
import com.udemy.compras.entity.Compra;
import com.udemy.compras.entity.Produto;
import com.udemy.compras.service.ClienteService;
import com.udemy.compras.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompraResolver implements GraphQLResolver<Compra> {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProdutoService produtoService;

    public String status(Compra c) {
        return "Teste : " + c.getStatus();
    }

    public Cliente cliente(Compra c){
        return clienteService.findById(c.getCliente().getId());
    }

    public Produto produto(Compra c){
        return produtoService.findById(c.getProduto().getId());
    }
}
