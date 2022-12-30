package br.com.jsonreadinsertmongo.controller;

import br.com.jsonreadinsertmongo.domain.User;
import br.com.jsonreadinsertmongo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<User>> list(){
        return ResponseEntity.ok().body(userService.list());
    }
}
