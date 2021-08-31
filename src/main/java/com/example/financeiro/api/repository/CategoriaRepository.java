package com.example.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.financeiro.api.model.Categoria;

// a JpaRepository é uma outra interface que disponibiliza vários métodos para utilização, CRUD e outros.

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
