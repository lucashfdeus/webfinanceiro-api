package com.example.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financeiro.api.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
