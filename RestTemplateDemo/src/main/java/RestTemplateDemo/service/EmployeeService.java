package RestTemplateDemo.service;

import RestTemplateDemo.domain.entity.Employee;
import RestTemplateDemo.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeService {

    private final RestTemplate restTemplate;
    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Employee> getall(){
        String url = "https://dummy.restapiexample.com/api/v1/employees";
        return restTemplate.getForObject(url, EmployeeDTO.class).getData();
    }
}
