package hello;

import hello.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

// MVC
// Model View Controller
@RestController
public class HelloController {

    @Autowired
    private RankService rankService;

    @RequestMapping("/")
    public ModelAndView index() {
        System.out.println(111);
        HashMap<String, Object> model = new HashMap<>();
        model.put("index", 1);
        model.put("name", "zhangxxx");
        model.put("score", 100);
        return new ModelAndView("index", model);
    }
}