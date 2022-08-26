package RestTemplateDemo.service;

import RestTemplateDemo.domain.entity.Employee;
import RestTemplateDemo.objects.RawData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
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
        RawData rawData = restTemplate.getForObject(url, RawData.class);
        System.out.println(rawData);
        System.out.println(rawData.getStatus());
        List<Employee> data= rawData.getData();
        System.out.println(data);
        return data;
    }
}
