package br.com.digital.flavor.backend.user;

import br.com.digital.flavor.backend.user.dto.CustomerDto;
import br.com.digital.flavor.backend.user.dto.NewUserDto;
import br.com.digital.flavor.backend.user.dto.UserLoginDto;
import br.com.digital.flavor.backend.user.dto.UserSettingsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserLoginDto> save(@RequestBody NewUserDto newUserDto) {
        return ResponseEntity.ok(this.userService.save(newUserDto));
    }

    @GetMapping("/get-all-customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(this.userService.getAllCustomers());
    }

    @GetMapping("/get-all-customers/{nameOrEmail}")
    public ResponseEntity<List<CustomerDto>> getAllCustomersByNameOrEmail(@PathVariable String nameOrEmail) {
        return ResponseEntity.ok(this.userService.getAllCustomersByNameOrEmail(nameOrEmail));
    }

    @GetMapping("get-user-settings-by-id")
    public ResponseEntity<UserSettingsDto> getUserById() {
        return ResponseEntity.ok(this.userService.getUserSettingsById());
    }
}
