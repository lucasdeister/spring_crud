package com.exercicio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.exercicio.model.Setor;
import com.exercicio.repository.SetorRepository;


@RestController
@RequestMapping("/api/setores")
public class SetorController {
	
	private final SetorRepository setorRepository;

    public SetorController(SetorRepository setorRepository) {
		this.setorRepository = setorRepository;
	}

    //listar setores
    @GetMapping
    public List<Setor> list(){
        return setorRepository.findAll();
    }

    //obter setor por id
    @GetMapping("/{id}")
    public ResponseEntity <Setor> findById(@PathVariable Long id){
       return setorRepository.findById(id)
            .map(recordFound -> ResponseEntity.ok().body(recordFound))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Setor create(@RequestBody Setor setor){
       return setorRepository.save(setor);
    }

    //atualizar
    @PutMapping("/{id}")
    public ResponseEntity <Setor> update(@PathVariable Long id, @RequestBody Setor setor){
        
        return setorRepository.findById(id)
        .map(recordFound ->{
            recordFound.setName(setor.getName());
            recordFound.setName(setor.getCategory());
            Setor updated = setorRepository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        Optional<Setor> setorOptional = setorRepository.findById(id);
        if (setorOptional.isPresent()) {
            setorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
