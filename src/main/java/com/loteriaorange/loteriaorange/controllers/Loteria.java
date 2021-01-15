package com.loteriaorange.loteriaorange.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loteriaorange.loteriaorange.models.Aposta;
import com.loteriaorange.loteriaorange.services.LoteriaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping(value = "/apostador")
@Api(value="API Loteria")
@CrossOrigin(origins="*")
public class Loteria {

	@Autowired
	private LoteriaService lotService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna lista de apostas do usuário")
	public ResponseEntity<?> find(@PathVariable("id") String email) {

		List<Aposta> apostadas = lotService.buscar(email);
		return ResponseEntity.ok().body(apostadas);
		
	}
	@GetMapping(value= "/criaraposta/{id}")
	@ApiOperation(value = "Cria uma aposta para um usuário")
	public ResponseEntity<?> create(@PathVariable("id") String email) {
		Aposta aposta = lotService.create(email);
		return ResponseEntity.ok().body(aposta);
	}
}
