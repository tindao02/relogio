package com.manoel.relogio.api.controller;

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
import com.manoel.relogio.api.service.RelogioService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/relogios")
@AllArgsConstructor
public class RelogioController 
{
	private final RelogioService relogioService;
	
	@GetMapping
	public List<Relogio> listar()
	{
		return relogioService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Relogio> buscarPorId(@PathVariable Long id)
	{
		return ResponseEntity.ok(relogioService.buscarPorId(id));
	}
	
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
