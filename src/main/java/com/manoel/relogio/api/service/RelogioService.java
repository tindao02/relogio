package com.manoel.relogio.api.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
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
	
	public Relogio salvar(Relogio relogio)
	{
		return relogioRepository.save(relogio);
	}
	
	public void remover(Long id)
	{
		relogioRepository.deleteById(id);
	}
	
	public Relogio buscarPorId(Long id)
	{
		return relogioRepository.findById(id)
								.orElseThrow( () -> new EmptyResultDataAccessException(1) );
	}
	
	public void atualizarStatus(Long id, String status)
	{		
		Relogio relogioSalvo = buscarPorId(id);
		relogioSalvo.alterarStatus(status);
		relogioRepository.save(relogioSalvo);
	}
	
}
