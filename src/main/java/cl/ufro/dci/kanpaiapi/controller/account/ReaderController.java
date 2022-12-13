package cl.ufro.dci.kanpaiapi.controller.account;

import cl.ufro.dci.kanpaiapi.dto.ReaderDto;
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
    public ResponseEntity<ReaderDto> createReader(@RequestBody ReaderDto readerDto) {
        return ResponseEntity.status(200).body(service.createReader(readerDto).toDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderDto> getReaderbyId(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.getReaderbyID(id).toDto());
    }

    @GetMapping()
    public ResponseEntity<List<ReaderDto>> getAllReader() {
        return ResponseEntity.status(200).body(service.getAllReader()
                .stream().map(Reader::toDto)
                .toList());
    }

    @PutMapping()
    public ResponseEntity<ReaderDto> updateReader(@RequestBody ReaderDto readerDto) {
        return ResponseEntity.status(200).body(service.updateReader(readerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReader(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.deleteReader(id));
    }
}
