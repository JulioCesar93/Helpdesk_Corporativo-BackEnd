package com.jcs.helpdesk.resources;

import com.jcs.helpdesk.domain.Analista;
import com.jcs.helpdesk.domain.dtos.AnalistaDTO;
import com.jcs.helpdesk.services.AnalistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/analistas")
public class AnalistaResource {

    //localhost:8080/analistas/1 Get id (tecnico)

    @Autowired
    private AnalistaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnalistaDTO> findById(@PathVariable Integer id) {
        Analista obj = service.findById(id);
        return ResponseEntity.ok().body(new AnalistaDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<AnalistaDTO>> findAll() {
        List<Analista> list = service.findAll();
        List<AnalistaDTO> listDTO = list.stream().map(obj -> new AnalistaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping
    public ResponseEntity<AnalistaDTO> create(@Valid @RequestBody AnalistaDTO objDTO) {
        Analista newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<AnalistaDTO> update(@PathVariable Integer id, @Valid @RequestBody AnalistaDTO objDTO) {
        Analista obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new AnalistaDTO(obj));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AnalistaDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}