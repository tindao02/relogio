package com.manoel.relogio.api.controller;

/***
 * Classe de Controller
 * 
 * Métodos Get, Post, Delete e Put
 * 
 * Nesta classe optei por manter as classes o mais limpo possível e deixar a maior parte da configuração na classe service,
 * assim como apenas a classe service que manipula a classe RelogioRepository
 * 
 */

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.manoel.relogio.api.model.Relogio;
import com.manoel.relogio.api.model.RelogioModelMaper;
import com.manoel.relogio.api.service.RelogioService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/relogios")
@AllArgsConstructor
public class RelogioController 
{
	private final RelogioService relogioService;
	
	//Nos métodos GET tem como retorno RelogioModelMapper que é um DTO onde inclui o tempo de cadastro
	@GetMapping
	public List<RelogioModelMaper> listar()
	{
		return relogioService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RelogioModelMaper> buscarPorId(@PathVariable Long id)
	{
		return ResponseEntity.ok(relogioService.buscarPorIdModel(id));
	}
	
	//Neste médoto Optei por retornar o tipo Relegio para diferencias dos métodos GET
	@PostMapping
	public ResponseEntity<Relogio> criar(@Valid @RequestBody Relogio relogio)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(relogioService.salvar(relogio));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id)
	{
		relogioService.remover(id);
	}
	
	//Diferente dos outros métodos mantive maior parte da configuração no método para demonstrar as diferentes formas
	@PutMapping("/{id}")
	public ResponseEntity<Relogio> atualizar(@PathVariable Long id, @Valid @RequestBody Relogio relogio)
	{
		Relogio relogioSalvo = relogioService.buscarPorId(id);
		
		relogioSalvo.setMarca(relogio.getMarca());		
		relogioSalvo.alterarStatus(relogio.getStatus().name());

		return ResponseEntity.ok(relogioService.salvar(relogioSalvo));
	}
	
	@PutMapping("/{id}/status")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarStatus(@PathVariable Long id, @RequestBody String status)
	{
		relogioService.atualizarStatus(id, status);
	}
	
	
	
}
