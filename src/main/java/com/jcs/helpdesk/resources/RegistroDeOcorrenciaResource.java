package com.jcs.helpdesk.resources;

import com.jcs.helpdesk.domain.RegistroDeOcorrencia;
import com.jcs.helpdesk.domain.dtos.RegistroDeOcorrenciaDTO;
import com.jcs.helpdesk.services.RegistroDeOcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/registroDeOcorrencia")
public class RegistroDeOcorrenciaResource {

    @Autowired
    private RegistroDeOcorrenciaService service;

    @GetMapping(value = "/id")
    public ResponseEntity<RegistroDeOcorrenciaDTO> findById(@PathVariable Integer id) {
        RegistroDeOcorrencia obj = service.findById(id);
        return ResponseEntity.ok().body(new RegistroDeOcorrenciaDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<RegistroDeOcorrenciaDTO>> findAll() {
        List<RegistroDeOcorrencia> list = service.findAll();
        List<RegistroDeOcorrenciaDTO> listDTO = list.stream().map(obj -> new RegistroDeOcorrenciaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<RegistroDeOcorrenciaDTO> create(@Valid @RequestBody RegistroDeOcorrenciaDTO objDTO) {
        RegistroDeOcorrencia obj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RegistroDeOcorrenciaDTO> update (@PathVariable Integer id, @Valid @RequestBody RegistroDeOcorrenciaDTO objDto) {
        RegistroDeOcorrencia newObj = service.update(id, objDto);
        return ResponseEntity.ok().body(new RegistroDeOcorrenciaDTO(newObj));
    }
}
