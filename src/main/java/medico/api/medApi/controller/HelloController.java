package medico.api.medApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/hello")
public class HelloController {

    @GetMapping
    public String olaMundo() {
        return  "Hello word";
    }
}
