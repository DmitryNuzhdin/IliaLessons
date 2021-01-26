package project.models.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepo  extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findById(long Id);
}
