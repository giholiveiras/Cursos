package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Cursos;
import com.projetojpa.services.CursosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name="cursos", description = "API REST DE GERENCIAMENTO DE CURSOS")
@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "*")
public class CursosController {
	private final CursosService cursosService;

	@Autowired
	public CursosController(CursosService CursosService) {
		this.cursosService = CursosService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza curso por iD")
	public ResponseEntity<Cursos> buscaCursosControlId(@PathVariable Long id) {
		Cursos cursos = cursosService.buscaCursosId(id);
		if (cursos != null) {
			return ResponseEntity.ok(cursos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping
	@Operation(summary = "Apresenta todos os cursos")
	public ResponseEntity<List<Cursos>> buscaTodosCursosControl() {
		List<Cursos> Cursos = cursosService.buscaTodosCursos();
		return ResponseEntity.ok(Cursos);
	}

	@PostMapping
	@Operation(summary = "Cadastra um Cursos")
	public ResponseEntity<Cursos> salvaCursosControl (@RequestBody @Valid Cursos cursos){
		Cursos salvaCursos= cursosService.salvarCursos(cursos);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaCursos);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Cursos> alteraCursosControl (@PathVariable Long id, @RequestBody @Valid Cursos cursos){
		Cursos alteraCursos = cursosService.alterarCursos(id, cursos);
		if (alteraCursos != null) {
			return ResponseEntity.ok(cursos);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("{id}")
	@Operation(summary = "Exclui um cursos")
	public ResponseEntity<Cursos> apagaCursosControl(@PathVariable Long id){
		boolean apagar = cursosService.apagarCursos(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}