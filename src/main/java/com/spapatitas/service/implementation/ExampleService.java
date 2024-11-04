package com.spapatitas.service.implementation;

import com.spapatitas.service.interfaces.IExampleService;
import org.springframework.stereotype.Service;

@Service
public class ExampleService implements IExampleService {
    /*
    Aquí se inyecta la dependencia del repository:

    @Autowired
    private ExampleRepository exampleRespository;

    y se implementan los métodos, como por ejemplo:

    @Override
    public Optional<Example> findById(long id){
        return exampleRepository.finById(id);
    }
     */
}
