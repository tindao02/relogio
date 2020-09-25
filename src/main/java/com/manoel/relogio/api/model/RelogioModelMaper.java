package com.manoel.relogio.api.model;

/***
 * Classe DTO para retornar indicando o tempo de cadastro como diferencial
 */

import java.time.LocalDate;
import java.time.Period;

import lombok.Data;

@Data
public class RelogioModelMaper 
{
	private Long id;
	private String marca;
	private Status status;
	private LocalDate dataCadastro;
	private String tempoCadastrado;
	
	public void geraIdade()
	{
		Period periodo = Period.between(this.getDataCadastro(), LocalDate.now());
		this.setTempoCadastrado(periodo.getYears() + " anos " + periodo.getMonths() + " meses e " + periodo.getDays() + " dias");
	}
	
}
