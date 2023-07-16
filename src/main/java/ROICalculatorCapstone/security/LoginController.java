package ROICalculatorCapstone.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String ShowLogin(Model model) {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Clear the user session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Redirect to the login page
        return "redirect:/login";
    }

    @PostMapping("/login1")
    public String login(HttpSession session, @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        User user = userRepository.findByUsername(username);
        if(user != null && passwordEncoder.matches(password, user.getPassword())){
            return "redirect:/properties";
        }
        return "login";
    }
}
