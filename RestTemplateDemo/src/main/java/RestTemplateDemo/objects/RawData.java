package RestTemplateDemo.objects;

import RestTemplateDemo.domain.entity.Employee;

import java.util.List;

public class RawData {
    private String status;
    private List<Employee> data;

    public String getStatus(){
        return status;
    }
    public List<Employee> getData() {
        return data;
    }
}
