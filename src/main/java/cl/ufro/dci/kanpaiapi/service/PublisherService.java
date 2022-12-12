package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.model.Publisher;
import cl.ufro.dci.kanpaiapi.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    PublisherRepository repository;

    public Publisher createPublisher(Publisher publisher) {
        return repository.save(publisher);
    }

    public Publisher getPublisherbyID(long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Publisher> getAllPublisher(){
        return  repository.findAll();
    }
    public Publisher updatePublisher(Publisher publisher) {

        Publisher publisherStored = repository.findById(publisher.getPubId()).orElseThrow();

        return repository.save(publisher);
    }

    public String deletePublisher(long id) {
        Publisher publisher = getPublisherbyID(id);
        repository.delete(publisher);
        return "si";
    }
}
