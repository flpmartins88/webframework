package core;

import lib.annotation.Controller;
import lib.annotation.Path;

@Controller
@Path("/home")
public class HomeController {

    @Path({"/", "/index"})
    public String home() {
        return "index.jsp";
    }
}
