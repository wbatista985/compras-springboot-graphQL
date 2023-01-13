package com.udemy.compras.service;

import com.udemy.compras.entity.Produto;
import com.udemy.compras.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto findById(Long id){
        return produtoRepository.findById(id).orElse(null);
    }
    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    @Transactional
    public Produto save(Produto p){
        return produtoRepository.save(p);
    }

    @Transactional
    public Boolean deleteById(Long id) {
        if(produtoRepository.findById(id).isPresent()){
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
