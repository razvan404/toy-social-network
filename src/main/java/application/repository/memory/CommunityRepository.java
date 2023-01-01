package application.repository.memory;

import application.model.Community;


/**
 * The class <b>CommunityRepository</b> stores Communities and contains CRUD operations with the data stored in memory.
 */
public class CommunityRepository extends AbstractRepositoryInMemory<Long, Community> {
    private static final CommunityRepository communityRepositoryInstance = new CommunityRepository();
    /**
     * Private constructor, we use the Singleton design pattern for the CommunityRepository
     */
    private CommunityRepository() {}

    /**
     * @return an instance for the class CommunityRepository
     */
    public static CommunityRepository getInstance() {
        return communityRepositoryInstance;
    }

    /**
     * The list of Communities will be cleared and the identifier for them will be reset.
     */
    public void clear() {
        entities.clear();
    }
}
