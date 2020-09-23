package com.manoel.relogio.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.manoel.relogio.api.model.Relogio;
import com.manoel.relogio.api.repository.RelogioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RelogioService 
{
	private final RelogioRepository relogioRepository;
	
	public List<Relogio> listar()
	{
		return relogioRepository.findAll();
	}
	
	public Optional<Relogio> buscar(Long id)
	{
		return relogioRepository.findById(id);
	}
	
	public Relogio criar(Relogio relogio)
	{
		return relogioRepository.save(relogio);
	}
	
	public void remover(Long id)
	{
		relogioRepository.deleteById(id);
	}
	
	
}
