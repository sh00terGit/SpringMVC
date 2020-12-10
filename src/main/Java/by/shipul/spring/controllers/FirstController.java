package by.shipul.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage( @RequestParam("name") String name ) {
        return "first/hello";
    }

    @GetMapping("/good-bye")
    public String goodbyePage(HttpServletRequest request) {
        String name = request.getParameter("name");
        return "first/good-bye";
    }
}
