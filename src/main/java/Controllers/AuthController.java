package Controllers;

import com.scaler.taskmanagementassignment.Entity.User;
import com.scaler.taskmanagementassignment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;


    private BCryptPasswordEncoder passwordEncoder;

    public AuthController(@Qualifier("user service") UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return ResponseEntity.ok("User registered successfully.");
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());

        // If the user is not found, register the user
        if (existingUser == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return ResponseEntity.ok("User not found. Registered successfully.");
        }

        // If user is found, validate the password
        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password.");
        }

        // If user is found and password matches, login successful
        return ResponseEntity.ok("Login successful.");
    }
}
