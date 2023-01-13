package com.udemy.compras.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.compras.dto.ProdutoDto;
import com.udemy.compras.entity.Produto;
import com.udemy.compras.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ProdutoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {
    @Autowired
    private ProdutoService produtoService;
    public Produto produto(Long id){
        return produtoService.findById(id);
    }
    public List<Produto> produtos(){
        return produtoService.findAll();
    }

    @Transactional
    public Produto saveProduto(ProdutoDto input) {
        /*Produto c = new Produto();
        c.setId(input.getId());
        c.setNome(input.getNome());
        c.setEmail(input.getEmail());*/

        ModelMapper m = new ModelMapper();
        Produto p = m.map(input, Produto.class);

        return produtoService.save(p);
    }
    public Boolean deleteProduto(Long id) {
          return  produtoService.deleteById(id);
    }
}

