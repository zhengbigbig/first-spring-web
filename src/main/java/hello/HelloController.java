package hello;

        import hello.entity.RankItem;
        import hello.service.RankService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.servlet.ModelAndView;

        import java.util.HashMap;
        import java.util.List;

// MVC
// Model View Controller
@RestController
public class HelloController {

    @Autowired
    private RankService rankService;

    @RequestMapping("/")
    public ModelAndView index() {
        List<RankItem> items = rankService.getRank();
        HashMap<String, Object> model = new HashMap<>();
        model.put("items", items);
        return new ModelAndView("index", model);
    }

    @RequestMapping("/rankData")
    @ResponseBody
    public Object getRankData(){
        return rankService.getRank();
    }
}