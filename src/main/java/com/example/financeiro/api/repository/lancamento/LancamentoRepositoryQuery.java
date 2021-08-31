package com.example.financeiro.api.repository.lancamento;

import com.example.financeiro.api.model.Lancamento;
import com.example.financeiro.api.repository.filter.LancamentoFilter;
import com.example.financeiro.api.repository.projection.ResumoLancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

	// Implementação para criar um método para filtrar
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

	// método para criar projeção(Resumo) com paginação
	public Page<ResumoLancamento> resumirLancamento(LancamentoFilter lancamentoFilter, Pageable pageable);

}