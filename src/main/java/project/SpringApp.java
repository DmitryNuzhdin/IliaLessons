package project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ilia Moskalenko
 */
@Configuration
@ComponentScan(basePackages = "project")
@SpringBootApplication
public class SpringApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class);
        /*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApp.class);
        context.getBean(IOService.class).start();*/

    }

//    @Bean
//    public DataStorage getDataStorage(){
//        return new InMemoryDataStorage();
//    }
}
