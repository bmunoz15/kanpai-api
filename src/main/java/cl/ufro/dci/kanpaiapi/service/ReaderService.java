package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.dto.ReaderDto;
import cl.ufro.dci.kanpaiapi.model.Reader;
import cl.ufro.dci.kanpaiapi.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {
    @Autowired
    ReaderRepository repository;

    public Reader createReader(ReaderDto readerDto) {
        Reader reader = new Reader();
        buildFromDto(reader,readerDto);
        return repository.save(reader);
    }

    public Reader getReaderbyID(long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Reader> getAllReader(){
        return  repository.findAll();
    }
    public ReaderDto updateReader(ReaderDto readerDto) {

        Reader readerStored = repository.findById(readerDto.getReaId()).orElseThrow();
        buildFromDto(readerStored,readerDto);

        return repository.save(readerStored).toDto();
    }

    public String deleteReader(long id) {
        Reader reader = getReaderbyID(id);
        repository.delete(reader);
        return "si";
    }
    private void buildFromDto(Reader reader, ReaderDto readerDto){
        reader.setReaName(readerDto.getReaName());
        reader.setReaLastName(readerDto.getReaLastName());
        reader.setReaNickName(readerDto.getReaNickName());
        reader.setReaGender(readerDto.getReaGender());
        reader.setReaBirthday(readerDto.getReaBirthday());
        reader.setReaMangas(readerDto.getReaMangas());
    }
}
