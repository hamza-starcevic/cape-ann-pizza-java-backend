package pizza;


import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/auth/register")
    public @ResponseBody User register(@RequestBody User user) {


        //Check if user already exists
        User existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser != null) {
            return null;
        }

        //genereate uuid for user
        user.setId(java.util.UUID.randomUUID().toString());


        //hash password
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);


        if (userRepository.findByEmail(user.getEmail()) != null) {
            return user;
        }
        userRepository.save(user);
        return user;
    }

    //Login
    @PostMapping("/auth/login")
    public @ResponseBody User login(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser == null) {
            return null;
        }
        if(BCrypt.checkpw(user.getPassword(), existingUser.getPassword())) {
            return existingUser;
        }
        return null;
    }

}
