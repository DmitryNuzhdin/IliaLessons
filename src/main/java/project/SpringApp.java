package project;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import project.data.DataStorage;
import project.data.InMemoryDataStorage;
import project.services.IOService;

/**
 * @author Ilia Moskalenko
 */
@Configuration
@ComponentScan(basePackages = "project")
public class SpringApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApp.class);
        context.getBean(IOService.class).start();

    }

//    @Bean
//    public DataStorage getDataStorage(){
//        return new InMemoryDataStorage();
//    }
}
