package br.com.erudio.services;

import br.com.erudio.dto.PersonDTO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    private final PersonMapper mapper;
    private final PersonRepository repository;

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class);

    public PersonServices(PersonMapper mapper, PersonRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<PersonDTO> findAll() {
        logger.info("Finding all persons");

        List<Person> entities = repository.findAll();
        return mapper.toDTOList(entities);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
        return mapper.toDTO(entity);
    }

    public PersonDTO create(PersonDTO dto) {
        logger.info("Creating a person");

        Person entity = mapper.toEntity(dto);
        Person saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    public PersonDTO update(PersonDTO dto) {
        logger.info("Updating a person");

        Person entity = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));

        // Atualiza apenas os campos alteráveis
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());

        Person updated = repository.save(entity);

        return mapper.toDTO(updated);
    }

    public void delete(Long id) {
        logger.info("Deleting a person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));

        repository.delete(entity);
    }
}
