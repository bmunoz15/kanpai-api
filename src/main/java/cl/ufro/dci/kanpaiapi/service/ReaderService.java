package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.model.Reader;
import cl.ufro.dci.kanpaiapi.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {
    @Autowired
    ReaderRepository repository;

    public Reader createReader(Reader reader) {
        return repository.save(reader);
    }

    public Reader getReaderbyID(long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Reader> getAllReader(){
        return  repository.findAll();
    }
    public Reader updateReader(Reader reader) {

        Reader readerStored = repository.findById(reader.getReaId()).orElseThrow();

        return repository.save(reader);
    }

    public String deleteReader(long id) {
        Reader reader = getReaderbyID(id);
        repository.delete(reader);
        return "si";
    }
}
