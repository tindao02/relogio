package com.manoel.relogio.api.service;

/*
 * Classe onde executa as configurações as ações dos métodos GET, POST, DELETE e PUT
 * e única classe onde manipula o RelogioRepository
 * 
 * Não utilizo o @Autowired, pois o Java reconhece quando por usar um método construtor 
 * 
 */

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.manoel.relogio.api.model.Relogio;
import com.manoel.relogio.api.model.RelogioModelMaper;
import com.manoel.relogio.api.repository.RelogioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RelogioService 
{
	private final RelogioRepository relogioRepository;
	private final ModelMapper modelMapper; //usado para converter de Relogio para RelogioModelMapper
	
	public List<RelogioModelMaper> listar()
	{
		List<Relogio> listaRelogio = relogioRepository.findAll();
		return toListRelogioModelMapper(listaRelogio);
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
	
	//esta classe é para ser manipulada pelos GET
	//mantive a classe com retorno Relogio, pois alguns métodos necessitam fazer a buscar  
	public RelogioModelMaper buscarPorIdModel(Long id)
	{
		Relogio relogio = relogioRepository.findById(id)
								.orElseThrow( () -> new EmptyResultDataAccessException(1) );
		return toRelogioModelMapper(relogio);
	}
	
	public void atualizarStatus(Long id, String status)
	{		
		Relogio relogioSalvo = buscarPorId(id);
		relogioSalvo.alterarStatus(status);
		relogioRepository.save(relogioSalvo);
	}
	
	//Classes de Conversão de Relogio para RelogioModelMapper
	
	private RelogioModelMaper toRelogioModelMapper(Relogio relogio)
	{
		RelogioModelMaper relogioModelMaper =  modelMapper.map(relogio, RelogioModelMaper.class);
		relogioModelMaper.geraIdade();
		return relogioModelMaper;
	}
	
	private List<RelogioModelMaper> toListRelogioModelMapper(List<Relogio> relogios)
	{
		return relogios.stream()
					   .map(relogio -> toRelogioModelMapper(relogio))
					   .collect(Collectors.toList());
	}
	
}
