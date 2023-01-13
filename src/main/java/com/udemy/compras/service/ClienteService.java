package com.udemy.compras.service;

import com.udemy.compras.entity.Cliente;
import com.udemy.compras.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    @Cacheable("clientes")
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente save(Cliente c){
        return clienteRepository.save(c);
    }

    @Transactional
    public Boolean deleteById(Long id) {
        if(clienteRepository.findById(id).isPresent()){
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
