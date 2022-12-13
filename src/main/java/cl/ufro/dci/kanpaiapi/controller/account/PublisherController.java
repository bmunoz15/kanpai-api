package cl.ufro.dci.kanpaiapi.controller.account;

import cl.ufro.dci.kanpaiapi.dto.PublisherDto;
import cl.ufro.dci.kanpaiapi.model.Publisher;
import cl.ufro.dci.kanpaiapi.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
    @Autowired
    PublisherService service;

    @PostMapping()
    public ResponseEntity<PublisherDto> createPublisher(@RequestBody PublisherDto publisherDto) {
        return ResponseEntity.status(200).body(service.createPublisher(publisherDto).toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> getPublisherbyId(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.getPublisherbyID(id).toDto());
    }
    @GetMapping()
    public ResponseEntity<List<PublisherDto>> getAllPublisher() {
        return ResponseEntity.status(200).body(service.getAllPublisher()
                .stream().map(Publisher::toDto)
                .toList());
    }

    @PutMapping()
    public ResponseEntity<PublisherDto> updatePublisher(@RequestBody PublisherDto publisherDto) {
        return ResponseEntity.status(200).body(service.updatePublisher(publisherDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.deletePublisher(id));
    }
}
