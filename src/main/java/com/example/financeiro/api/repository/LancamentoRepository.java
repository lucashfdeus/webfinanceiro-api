package com.example.financeiro.api.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.financeiro.api.model.Lancamento;
import com.example.financeiro.api.repository.lancamento.LancamentoRepositoryQuery;


@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
	
	
}
