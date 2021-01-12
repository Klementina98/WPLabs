package mk.ukim.finki.wp.lab2.Filter;

import mk.ukim.finki.wp.lab2.Model.MyUser;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Profile("filter")
public class LogInFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        MyUser user= (MyUser) request.getSession().getAttribute("user"); //postaveno vo Post baranjeto na logIn, od koga gi imame username i password vneseni od userot
        String path=request.getServletPath();

        //da mu zabranime da pristapi do druga pateka ako ne e logiran
        if (user==null && !path.equals("/login")){
            response.sendRedirect("/login");
        }else{
            //ako e najaven togash moze da prodolzi kon drugite dleovi od filterChain-ot
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
