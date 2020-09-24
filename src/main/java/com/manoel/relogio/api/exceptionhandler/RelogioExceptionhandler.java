package com.manoel.relogio.api.exceptionhandler;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class RelogioExceptionhandler extends ResponseEntityExceptionHandler
{
	//Captura as mensagens que não conseguiu ler, quando o usuário mando algum elemento a mais
	//Quando tenta alterar status e manda campo vazio
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		String mensagemUsuario = "Mensagem Inválida";
		String mensagemDesenvolvedor = Optional.ofNullable(ex.getCause()).orElse(ex).toString();
		
		return handleExceptionInternal(ex, new mensagemError(mensagemUsuario, mensagemDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	//Tratar argumentos que não estão válidos
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		String mensagemUsuario = "Mensagem Inválida";
		String mensagemDesenvolvedor = ex.getBindingResult().toString();
		
		return handleExceptionInternal(ex, new mensagemError(mensagemUsuario, mensagemDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	//Quando o usuário tenta buscar um recurso que não existi
	//EX.: quando tenta deletar um valor que não existe ou já foi deletado anteriomente
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request)
	{		
		String mensagemUsuario = "Recurso não encontrado";
		String mensagemDesenvolvedor = ex.toString();
		
		return handleExceptionInternal(ex, new mensagemError(mensagemUsuario, mensagemDesenvolvedor), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	//Usado quando o usuário no momento da atualização do status envia um valor incorreto
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request)
	{		
		String mensagemUsuario = "Valor incorreto";
		String mensagemDesenvolvedor = ex.toString();
		
		return handleExceptionInternal(ex, new mensagemError(mensagemUsuario, mensagemDesenvolvedor), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	//Padrão mensagem para cliente e desenvolvedor
	@Data
	@AllArgsConstructor
	private static class mensagemError
	{
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
	}
}
