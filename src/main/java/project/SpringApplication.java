package project;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import project.data.DataStorage;
import project.data.InMemoryDataStorage;
import project.models.Model;
import project.services.IOService;
import project.services.ServiceSupervisor;

import java.util.Map;

@Configuration
@ComponentScan(basePackages = "project")
public class SpringApplication {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApplication.class);
//        context.registerBean(InMemoryDataStorage.class);
//        context.refresh();

        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        DataStorage inMemoryDataStorage = context.getBean(DataStorage.class);
        Model model = context.getBean(Model.class);
        Map<String, IOService> ioService = context.getBeansOfType(IOService.class);

        System.out.println(ioService);

        System.out.println("--------------");
        context.getBean(ServiceSupervisor.class).showAllServiceNames();
    }

//    @Bean
//    InMemoryDataStorage getInMemoryDataStorage() {
//        return new InMemoryDataStorage();
//    }

}
