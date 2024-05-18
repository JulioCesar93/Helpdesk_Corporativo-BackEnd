package com.jcs.helpdesk.resources;

import com.jcs.helpdesk.domain.Associado;
import com.jcs.helpdesk.domain.dtos.AssociadoDTO;
import com.jcs.helpdesk.services.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/Associados")
public class AssociadoResource {

    //localhost:8080/associados/1 Get id (associado)

    @Autowired
    private AssociadoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AssociadoDTO> findById(@PathVariable Integer id) {
        Associado obj = service.findById(id);
        return ResponseEntity.ok().body(new AssociadoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<AssociadoDTO>> findAll() {
        List<Associado> list = service.findAll();
        List<AssociadoDTO> listDTO = list.stream().map(obj -> new AssociadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PatchMapping
    public ResponseEntity<AssociadoDTO> create(@Valid @RequestBody AssociadoDTO objDTO) {
        Associado newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AssociadoDTO> update(@PathVariable Integer id, @Valid @RequestBody AssociadoDTO objDTO) {
        Associado obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new AssociadoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AssociadoDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
