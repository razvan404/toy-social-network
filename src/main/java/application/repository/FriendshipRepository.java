package application.repository;

import application.domain.Friendship;
import application.utils.pair.DistinctPair;
import application.utils.pair.Pair;

import java.util.UUID;

public interface FriendshipRepository extends AbstractRepository<DistinctPair<UUID>, Friendship> {

}