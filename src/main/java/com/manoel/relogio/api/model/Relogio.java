package com.manoel.relogio.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relogio 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50)
	@NotEmpty
	@NotBlank
	private String marca;
	
	@Column(name = "data_cadastro", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@Enumerated(EnumType.STRING)
	@NotNull
	private Status status;
	
	//Executa antes, assim podendo automaticamente setar a data de cadastro 
	@PrePersist
	public void prePersist()
	{
		this.setDataCadastro(LocalDate.now());
	}
	
	//Para fazer alterações no Status
	public void alterarStatus(String status)
	{
		this.setStatus(Status.valueOf(status));
	}
	
}
