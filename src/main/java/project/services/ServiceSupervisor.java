package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceSupervisor {
    private final List<IOService> services;

    public ServiceSupervisor(List<IOService> services) {
        this.services = services;
    }

    public void showAllServiceNames() {
        services.forEach(ioService -> System.out.println(ioService.getClass().getSimpleName()));
    }

    public void startAll(){
        services.forEach(IOService::start);
    }
}
