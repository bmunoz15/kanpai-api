package cl.ufro.dci.kanpaiapi.controller.account;

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
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        return ResponseEntity.status(200).body(service.createPublisher(publisher));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherbyId(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.getPublisherbyID(id));
    }
    @GetMapping()
    public ResponseEntity<List<Publisher>> getAllPublisher() {
        return ResponseEntity.status(200).body(service.getAllPublisher());
    }

    @PutMapping()
    public ResponseEntity<Publisher> updatePublisher(@RequestBody Publisher publisher) {
        return ResponseEntity.status(200).body(service.updatePublisher(publisher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.deletePublisher(id));
    }
}
