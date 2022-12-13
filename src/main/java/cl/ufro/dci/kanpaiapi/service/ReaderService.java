package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.dto.ReaderDto;
import cl.ufro.dci.kanpaiapi.exception.ApiForbiddenException;
import cl.ufro.dci.kanpaiapi.exception.ApiRequestException;
import cl.ufro.dci.kanpaiapi.model.Publisher;
import cl.ufro.dci.kanpaiapi.model.Reader;
import cl.ufro.dci.kanpaiapi.repository.ReaderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ReaderService {
    @Autowired
    ReaderRepository repository;

    public Reader createReader(ReaderDto readerDto) {
        log.info("Creating new reader");
        try {
            Reader reader = new Reader();
            buildFromDto(reader, readerDto);
            return repository.save(reader);
        } catch (Exception e) {
            log.error("Error creating new reader {}", e.toString());
            throw new ApiRequestException("Error creating new reader");
        }
    }

    public Reader getReaderbyID(long id) {
        log.info("Fetching reader");
        try {
            return repository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            log.error("No reader with Id " + id + " found");
            throw new ApiRequestException("No reader with Id " + id + " found");
        } catch (ApiForbiddenException e) {
            log.error(e.getMessage());
            throw new ApiForbiddenException(e.getMessage());
        } catch (Exception e) {
            log.error("error fetching reader" + " " + e.toString());
            throw new ApiRequestException("Error fetching reader");
        }
    }

    public List<Reader> getAllReader() {
        return repository.findAll();
    }

    public ReaderDto updateReader(ReaderDto readerDto) {
        log.info("Updating reader");
        try {
            Reader readerStored = repository.findById(readerDto.getReaId()).orElseThrow();
            buildFromDto(readerStored, readerDto);
            return repository.save(readerStored).toDto();
        } catch (ApiForbiddenException e) {
            log.error(e.toString());
            throw new ApiForbiddenException(e.getMessage());
        } catch (Exception e) {
            log.error("Error updating reader with id " + readerDto.getReaId() + " " + e.toString());
            throw new ApiRequestException("Error updating reader");
        }
    }

    public String deleteReader(long id) {
        log.info("deleting reader");
        try {
            Reader reader = getReaderbyID(id);
            repository.delete(reader);
            return "si";
        } catch (ApiForbiddenException e) {
            log.error(e.getMessage());
            throw new ApiForbiddenException(e.getMessage());
        } catch (Exception e) {
            log.error("error deleting reader" + " " + e.toString());
            throw new ApiRequestException("Error deleting reader");
        }
    }

    private void buildFromDto(Reader reader, ReaderDto readerDto) {
        reader.setReaName(readerDto.getReaName());
        reader.setReaLastName(readerDto.getReaLastName());
        reader.setReaNickName(readerDto.getReaNickName());
        reader.setReaGender(readerDto.getReaGender());
        reader.setReaBirthday(readerDto.getReaBirthday());
        reader.setReaMangas(readerDto.getReaMangas());
    }
}
