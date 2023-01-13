package com.udemy.compras.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.compras.dto.ClienteDto;
import com.udemy.compras.entity.Cliente;
import com.udemy.compras.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ClienteGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {
    @Autowired
    private ClienteService clienteService;
    public Cliente cliente(Long id){
        return clienteService.findById(id);
    }
    public List<Cliente> clientes(){
        return clienteService.findAll();
    }

    @Transactional
    public Cliente saveCliente(ClienteDto input) {
        /*Cliente c = new Cliente();
        c.setId(input.getId());
        c.setNome(input.getNome());
        c.setEmail(input.getEmail());*/

        ModelMapper m = new ModelMapper();
        Cliente c = m.map(input, Cliente.class);

        return clienteService.save(c);
    }
    public Boolean deleteCliente(Long id) {
          return   clienteService.deleteById(id);
    }
}

