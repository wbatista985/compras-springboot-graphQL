package com.udemy.compras.repository;

import com.udemy.compras.dto.CompraResumoDto;
import com.udemy.compras.entity.Cliente;
import com.udemy.compras.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {

    List<Compra> findAllByCliente(Cliente c);

    List<Compra> findAllByClienteAndQuantidade(Cliente c, int quantidade);

    //@QueryHints cache da Jpa.
    @Query("select new com.udemy.compras.dto.CompraResumoDto(c.id, cli.nome, p.nome, c.quantidade)from Compra c inner join c.cliente cli inner join c.produto p" )
    @QueryHints({@QueryHint(name = "org,hibernate.cacheable", value = "true")})
    List<CompraResumoDto> findAllComprasRelatorio();

    /*exemplo com query
    @Query("select c from Compra c where c.cliente = :cliente")
    List<Compra> findAllByCliente(@Param("cliente") Cliente cliente);

    exemple query
    @Query("select c from Compra c where c.cliente.id = :clienteId")
    List<Compra> findAllByCliente(@Param("clienteId") Cliente clienteId);
     */
}
