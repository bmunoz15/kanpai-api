package cl.ufro.dci.kanpaiapi.controller.account;

import cl.ufro.dci.kanpaiapi.model.Reader;
import cl.ufro.dci.kanpaiapi.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    ReaderService service;

    @PostMapping()
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {
        return ResponseEntity.status(200).body(service.createReader(reader));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReaderbyId(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.getReaderbyID(id));
    }

    @GetMapping()
    public ResponseEntity<List<Reader>> getAllReader() {
        return ResponseEntity.status(200).body(service.getAllReader());
    }

    @PutMapping()
    public ResponseEntity<Reader> updateReader(@RequestBody Reader reader) {
        return ResponseEntity.status(200).body(service.updateReader(reader));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReader(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.deleteReader(id));
    }
}
