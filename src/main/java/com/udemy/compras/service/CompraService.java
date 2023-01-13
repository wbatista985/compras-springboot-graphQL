package com.udemy.compras.service;

import com.udemy.compras.dto.CompraResumoDto;
import com.udemy.compras.entity.Cliente;
import com.udemy.compras.entity.Compra;
import com.udemy.compras.graphql.exceptions.DomainException;
import com.udemy.compras.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    public Compra findById(Long id){
        return compraRepository.findById(id).orElse(null);
    }
    public List<Compra> findAll(Pageable pageable){
        return compraRepository.findAll(pageable).getContent();
    }

    @Transactional
    //notação de cache
    //@CacheEvict(value = "comprasByCliente", key = "#c.cliente.id")
    public Compra save(Compra c){
        if(c.getQuantidade() > 100) {
            throw new DomainException("Não é possível fazer uma compra com mais de 100 items");
        }
        return compraRepository.save(c);
    }

    @Transactional
    public Boolean deleteById(Long id) {
        if(compraRepository.findById(id).isPresent()){
            compraRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //notação de cache hibernate
    //@Cacheable(value = "comprasByCliente", key = "#c.id")
    public List<Compra> findAllByCliente(Cliente c) {
        return compraRepository.findAllByCliente(c);
    }

    public List<Compra> findAllByClienteAndQuantidade(Cliente c, int quantidade) {
        return compraRepository.findAllByClienteAndQuantidade(c, quantidade);
    }

    public List<CompraResumoDto> findAllComprasRelatorio() {
        return compraRepository.findAllComprasRelatorio();
    }
}
