package core;

import lib.annotation.Controller;
import lib.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping({"/", "/index"})
    public String home() {
        return "index.jsp";
    }
}
