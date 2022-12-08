package application.service;

import application.domain.Entity;
import application.repository.AbstractRepository;
import application.service.exceptions.NotFoundException;

import java.util.Collection;
import java.util.Optional;

/**
 * The abstract class <b>Service</b> is used to manipulate and to validate data from the given repository.
 * @param <ID> the identifier of the Entity
 * @param <E> the type of the Element, should extend Entity
 */
public abstract class AbstractService<ID, E extends Entity<ID>> {
    protected AbstractRepository<ID, E> repository;

    /**
     * Creates a new Service.
     * @param repository a reference to the repository
     */
    public AbstractService(AbstractRepository<ID, E> repository) {
        this.repository = repository;
    }

    /**
     * Finds the User that have the given identifier.
     * @param id the identifier of the User to find
     * @return the User if there is one with the given identifier
     * @throws IllegalArgumentException when the given identifier is invalid
     * @throws NotFoundException when there is no User with the given identifier
     */
    public E find(ID id) throws IllegalArgumentException, NotFoundException {
        Optional<E> entity = repository.find(id);
        if (entity.isPresent()) {
            return entity.get();
        }
        throw new NotFoundException("There is no such entity in the repository");
    }
}
