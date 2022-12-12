package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.dto.PublisherDto;
import cl.ufro.dci.kanpaiapi.model.Publisher;
import cl.ufro.dci.kanpaiapi.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    PublisherRepository repository;

    public Publisher createPublisher(PublisherDto publisherDto) {
        Publisher publisher = new Publisher();
        buildFromDto(publisher,publisherDto);
        return repository.save(publisher);
    }

    public Publisher getPublisherbyID(long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Publisher> getAllPublisher(){
        return  repository.findAll();
    }
    public PublisherDto updatePublisher(PublisherDto publisherDto) {

        Publisher publisherStored = repository.findById(publisherDto.getPubId()).orElseThrow();
        buildFromDto(publisherStored,publisherDto);

        return repository.save(publisherStored).toDto();
    }

    public String deletePublisher(long id) {
        Publisher publisher = getPublisherbyID(id);
        repository.delete(publisher);
        return "si";
    }
    private void buildFromDto(Publisher publisher, PublisherDto publisherDto){
        publisher.setPubName(publisherDto.getPubName());
        publisher.setPubDescription(publisherDto.getPubDescription());
        publisher.setPubType(publisherDto.getPubType());
        publisher.setPubMangas(publisherDto.getPubMangas());
    }
}
