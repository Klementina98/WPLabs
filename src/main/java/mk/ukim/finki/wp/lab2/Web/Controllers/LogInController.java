package mk.ukim.finki.wp.lab2.Web.Controllers;

import mk.ukim.finki.wp.lab2.Exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.lab2.Model.MyUser;
import mk.ukim.finki.wp.lab2.Service.AuthService;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LogInController {

    public final AuthService authService;

    public LogInController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping
    public String getLogInPage(){
        return "login";
    }

    @PostMapping
    public String logIn(HttpServletRequest request, Model model){
       MyUser user = null;
       try{
           user=authService.logIn(request.getParameter("username"),
                                request.getParameter("password"));
           request.getSession().setAttribute("user",user);
           return  "redirect:/courses";
       }catch (InvalidUserCredentialsException exception){
           model.addAttribute("hasError",true);
           model.addAttribute("error",exception.getMessage());
           return "login";
       }
    }
}
