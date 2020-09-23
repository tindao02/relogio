package com.manoel.relogio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manoel.relogio.api.model.Relogio;
import com.manoel.relogio.api.repository.RelogioRepository;
import com.manoel.relogio.api.service.RelogioService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/relogios")
@AllArgsConstructor
public class RelogioController 
{
	private final RelogioService relogioService;
	private final RelogioRepository r;
	
	@GetMapping
	public List<Relogio> listar()
	{
		return r.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Relogio> criar(@Valid @RequestBody Relogio relogio)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(relogioService.criar(relogio));
	}
	
}
