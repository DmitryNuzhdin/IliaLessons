package project.models.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface TaskRepo extends CrudRepository<TaskEntity, Long> {
    Optional<TaskEntity> findById(long id);

    List<TaskEntity> findUserIdAndIsSolved(long userId, boolean solved);

}
