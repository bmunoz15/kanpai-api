package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.dto.PublisherDto;
import cl.ufro.dci.kanpaiapi.exception.ApiForbiddenException;
import cl.ufro.dci.kanpaiapi.exception.ApiRequestException;
import cl.ufro.dci.kanpaiapi.model.Publisher;
import cl.ufro.dci.kanpaiapi.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class PublisherService {
    @Autowired
    PublisherRepository repository;

    public Publisher createPublisher(PublisherDto publisherDto) {
        log.info("Creating new publisher");
        try {
            Publisher publisher = new Publisher();
            buildFromDto(publisher, publisherDto);
            return repository.save(publisher);
        } catch (Exception e) {
            log.error("Error creating new publisher {}", e.toString());
            throw new ApiRequestException("Error creating new publisher");
        }
    }

    public Publisher getPublisherbyID(long id) {
        log.info("Fetching publisher");
        try {
            return repository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            log.error("No publisher with Id " + id + " found");
            throw new ApiRequestException("No publisher with Id " + id + " found");
        } catch (ApiForbiddenException e) {
            log.error(e.getMessage());
            throw new ApiForbiddenException(e.getMessage());
        } catch (Exception e) {
            log.error("error fetching publisher" + " " + e.toString());
            throw new ApiRequestException("Error fetching publisher");
        }
    }

    public List<Publisher> getAllPublisher() {
        return repository.findAll();
    }

    public PublisherDto updatePublisher(PublisherDto publisherDto) {
        log.info("Updating publisher");
        try {
            Publisher publisherStored = repository.findById(publisherDto.getPubId()).orElseThrow();
            buildFromDto(publisherStored, publisherDto);
            return repository.save(publisherStored).toDto();
        } catch (ApiForbiddenException e) {
            log.error(e.toString());
            throw new ApiForbiddenException(e.getMessage());
        } catch (Exception e) {
            log.error("Error updating publisher with id " + publisherDto.getPubId() + " " + e.toString());
            throw new ApiRequestException("Error updating publisher");
        }
    }

    public String deletePublisher(long id) {
        log.info("deleting publisher");
        try {
            Publisher publisher = getPublisherbyID(id);
            repository.delete(publisher);
            return "si";
        } catch (ApiForbiddenException e) {
            log.error(e.getMessage());
            throw new ApiForbiddenException(e.getMessage());
        } catch (Exception e) {
            log.error("error deleting publisher" + " " + e.toString());
            throw new ApiRequestException("Error deleting publisher");
        }
    }

    private void buildFromDto(Publisher publisher, PublisherDto publisherDto) {
        publisher.setPubName(publisherDto.getPubName());
        publisher.setPubDescription(publisherDto.getPubDescription());
        publisher.setPubType(publisherDto.getPubType());
        publisher.setPubMangas(publisherDto.getPubMangas());
    }
}
