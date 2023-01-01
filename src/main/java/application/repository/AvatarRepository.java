package application.repository;

import application.model.Avatar;
import java.util.Collection;

public interface AvatarRepository extends AbstractRepository<Short, Avatar> {
    Collection<Avatar> findAll();
}
