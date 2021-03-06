package com.example.financeiro.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.financeiro.api.model.Pessoa;
import com.example.financeiro.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizarPessoa(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaId(id);

		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		return pessoaRepository.save(pessoaSalva);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaId(id);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}

	public Pessoa buscarPessoaId(Long id) {
		/*
		 * Pessoa pessoaSalva = pessoaRepository.findById(id).orElseThrow(() -> new
		 * EmptyResultDataAccessException(1)); if (pessoaSalva == null) { throw new
		 * EmptyResultDataAccessException(1); } return pessoaSalva;
		 * 
		 * }
		 */

		Pessoa pessoaSalva = pessoaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));

		return pessoaSalva;
	}

}
