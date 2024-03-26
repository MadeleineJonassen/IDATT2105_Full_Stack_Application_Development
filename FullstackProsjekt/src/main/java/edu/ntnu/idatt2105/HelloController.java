package edu.ntnu.idatt2105;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}