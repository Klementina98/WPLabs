package mk.ukim.finki.wp.lab2.Web.Servlets;

import mk.ukim.finki.wp.lab2.Exceptions.InvalidArgumentException;
import mk.ukim.finki.wp.lab2.Model.MyUser;
import mk.ukim.finki.wp.lab2.Service.AuthService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/servlet/login")
public class LogInServlet extends HttpServlet {

    public final SpringTemplateEngine springTemplateEngine;
    public final AuthService authService;

    public LogInServlet(SpringTemplateEngine springTemplateEngine, AuthService authService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authService = authService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req,resp,req.getServletContext());
        springTemplateEngine.process("login.html",webContext,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username= req.getParameter("username");
        String password=req.getParameter("password");
        MyUser newUser=null;
        try {
            //da probame da go najavime
            newUser=authService.logIn(username,password);

        }catch (InvalidArgumentException ex){
            //vo sluchaj ako nemoze da se najavi
            //shtom sakame da pushtime neshto na templete na html strana moze WebContext->Model model
            WebContext webContext=new WebContext(req,resp,req.getServletContext());
            webContext.setVariable("hasError", true);
            webContext.setVariable("error",ex.getMessage());
            springTemplateEngine.process("login.html",webContext,resp.getWriter());
        }
        req.getSession().setAttribute("user",newUser);
        resp.sendRedirect("/courses");

    }
}
