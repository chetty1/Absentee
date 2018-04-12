package WebsiteControllers;

import Model.Staff;
import Repositories.staffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by chett_000 on 2018/02/27.
 */
@Controller
public class loginController {




    @RequestMapping(value = "/login")
    public String login(){


        return "login";
    }
}
