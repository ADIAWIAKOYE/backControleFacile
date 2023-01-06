package example.backcontrolefacile.Controller;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
public class TestController {

    @GetMapping("/")
    public String test(){
        return " Bienvenue User !!!";
    }
}
