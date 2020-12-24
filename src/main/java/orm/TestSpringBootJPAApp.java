package orm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import javax.sql.DataSource;

@SpringBootApplication
public class TestSpringBootJPAApp {
    private static final Logger log = LoggerFactory.getLogger(TestSpringBootJPAApp.class);

    public static void main(String[] args) {
        SpringApplication.run(TestSpringBootJPAApp.class);
    }

//    @Bean
//    public CommandLineRunner demo(TestRepo testRepo) {
//        return (args) -> {
//            testRepo.save(new TestEntity("A"));
//            testRepo.save(new TestEntity("B"));
//            testRepo.save(new TestEntity("C"));
//            log.info("-----SAVED------");
//            testRepo.findByData("A  ").forEach(q -> log.info(q.getData()));
//        };
//    }


    @Bean
    public CommandLineRunner demo(TestRepo repo, MasterRepo masterRepo, DetailRepo detailRepo) {
        return (args) -> {
//            repo.save(new TestEntity("A"));
//            repo.save(new TestEntity("B"));
//            repo.save(new TestEntity("C"));
//            log.info("-----SAVED------");
//            repo.findAll().forEach(testEntity -> log.info(testEntity.toString()));
//            log.info("-----ID = 1------");
//            log.info(repo.findById(1).toString());
//            log.info("-----DATA = A------");
//            repo.findByData("A").forEach(testEntity -> log.info(testEntity.toString()));

            Master m = new Master("Vasya");
            Detail d1 = new Detail("d1", m);
            Detail d2 = new Detail("d2", m);

            masterRepo.save(m);
            log.info("MASTER after save: " + m.toString());

            detailRepo.save(d1);
            detailRepo.save(d2);

            log.info("MASTERS:");
            masterRepo.findAll().forEach(testEntity -> log.info(testEntity.toString()));
            log.info("DETAILS:");
            detailRepo.findAll().forEach(testEntity -> log.info(testEntity.toString()));

        };
    }

//    @Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("org.h2.Driver");
//        dataSourceBuilder.url("jdbc:h2:C:\\Users\\Dmitry\\IdeaProjects\\IliaLessons\\db\\orm;DB_CLOSE_ON_EXIT=FALSE");
//        return dataSourceBuilder.build();
//    }

}
