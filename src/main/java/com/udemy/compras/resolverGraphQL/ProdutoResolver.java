package com.udemy.compras.resolverGraphQL;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.compras.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoResolver implements GraphQLResolver<Produto> {

    public String valorReais(Produto p) {
        return "R$: : " + p.getValor();
    }
}
