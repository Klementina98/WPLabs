package mk.ukim.finki.wp.lab2.Web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("home")
    public String getHomePage(){
        return "home";
    }
    @GetMapping("deniedPage")
    public String deniedPage(){
        return "deniedPage";
    }
}
