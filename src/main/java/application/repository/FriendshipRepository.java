package application.repository;

import application.model.Friendship;
import application.utils.pair.DistinctPair;

import java.util.UUID;

public interface FriendshipRepository extends AbstractRepository<DistinctPair<UUID>, Friendship> {

}