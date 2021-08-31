package com.example.financeiro.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.financeiro.api.event.RecursoEvent;

@Component
public class RecursoListener implements ApplicationListener<RecursoEvent> {
	
	/* Comportamento para recursos criados (Pessoas e Categorias)*/

	@Override
	public void onApplicationEvent(RecursoEvent recursoEvent) {
		HttpServletResponse response = recursoEvent.getResponse();
		Long id = recursoEvent.getId();
		adicionarHeaderLocation(response, id);
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
