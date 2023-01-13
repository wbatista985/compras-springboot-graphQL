package com.udemy.compras.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.compras.dto.CompraDto;
import com.udemy.compras.dto.CompraResumoDto;
import com.udemy.compras.entity.Compra;
import com.udemy.compras.service.ClienteService;
import com.udemy.compras.service.CompraService;
import com.udemy.compras.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CompraGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CompraService compraService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProdutoService produtoService;

    public Compra compra(Long id){
        return compraService.findById(id);
    }

    //paginação de compras
    public List<Compra> getCompras(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return compraService.findAll(pageable);
    }

    public List<CompraResumoDto> getComprasRelatorio(){
        return compraService.findAllComprasRelatorio();
    }


    public Compra saveCompra(CompraDto input) {
        ModelMapper m = new ModelMapper();
        Compra c = m.map(input, Compra.class);

        c.setData(new Date());
        c.setCliente(clienteService.findById(input.getClienteId()));
        c.setProduto(produtoService.findById(input.getProdutoId()));

        return compraService.save(c);
    }
    public Boolean deleteCompra(Long id) {
          return  compraService.deleteById(id);
    }
}

