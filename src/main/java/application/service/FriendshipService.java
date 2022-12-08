package application.service;

import application.domain.Friendship;
import application.utils.pair.DistinctPair;
import application.domain.User;
import application.domain.exceptions.ValidationException;
import application.repository.FriendshipRepository;
import application.service.exceptions.AlreadyExistsException;
import application.service.exceptions.NotFoundException;

import java.util.UUID;

/**
 * The class <b>FriendshipService</b> is used to manipulate data from FriendshipRepository.
 */
public class FriendshipService extends AbstractService<DistinctPair<UUID>, Friendship> {
    /**
     * Constructs a new FriendshipService.
     * @param repository the repository associated with the repository
     */
    public FriendshipService(FriendshipRepository repository) {
        super(repository);
    }

    /**
     * Saves a Friendship into the repository.
     * @param user1 a reference for the first User
     * @param user2 a reference for the second User
     * @throws ValidationException when the two users cannot create a Friendship
     */
    public void save(User user1, User user2) throws ValidationException, AlreadyExistsException {
        //if (repository.save(Friendship.create(user1.getID(), user2.getID())).isPresent()) {
        //    throw new AlreadyExistsException("There is already a friendship between these 2 users!");
        //}
    }

    /**
     * Deletes a Friendship from the repository.
     * @param user1 a reference for the first User
     * @param user2 a reference for the second User
     */
    public void delete(UUID user1, UUID user2) throws NotFoundException {
        if (repository.delete(new DistinctPair<>(user1, user2)).isEmpty()) {
            throw new NotFoundException("There is no friendship between the specified users!");
        }
    }

    /**
     * Finds a Friendship in the repository
     * @param user1 the first User
     * @param user2 the second User
     * @return the Friendship between the 2 Users if it exists in the repository
     * @throws NotFoundException when there is no friendship between the 2 given users
     */
    public Friendship find(User user1, User user2) throws NotFoundException {
        return repository.find(new DistinctPair<>(user1.getID(), user2.getID()))
                .orElseThrow(() -> new NotFoundException("There is no such friendship!"));

    }
}
