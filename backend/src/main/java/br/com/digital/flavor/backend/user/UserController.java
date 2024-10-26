package br.com.digital.flavor.backend.user;

import br.com.digital.flavor.backend.user.dto.NewUserDto;
import br.com.digital.flavor.backend.user.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserLoginDto> save(@RequestBody NewUserDto newUserDto) {
        return ResponseEntity.ok(userService.save(newUserDto));
    }
}
