package com.signo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.signo.models.Signo;
import com.signo.repositories.SignoRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/signos")
public class SignoController {

    @Autowired
    private SignoRepository signoRepository;

    @GetMapping
    public List<Signo> getAllSignos() {
        return signoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Signo> getSignoById(@PathVariable Long id) {
        Optional<Signo> signo = signoRepository.findById(id);
        return signo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Signo createSigno(@RequestBody Signo signo) {
        return signoRepository.save(signo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Signo> updateSigno(@PathVariable Long id, @RequestBody Signo signoDetails) {
        Optional<Signo> signoOptional = signoRepository.findById(id);

        if (signoOptional.isPresent()) {
        	Signo signo = signoOptional.get();
        	signo.setNome(signoDetails.getNome());
        	signo.setIdade(signoDetails.getIdade());
        	signo.setSigno(signoDetails.getSigno());
            signo.setTelefone(signoDetails.getTelefone());
            signoRepository.save(signo);
            return ResponseEntity.ok(signo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSigno(@PathVariable Long id) {
        if (signoRepository.existsById(id)) {
        	signoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
