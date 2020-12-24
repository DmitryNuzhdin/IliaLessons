package orm;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface TestRepo extends CrudRepository<TestEntity, Long> {
    TestEntity findById(long id);
    List<TestEntity> findByData(String data);
}