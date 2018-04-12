package WebsiteControllers;

import Repositories.staffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by chett_000 on 2018/03/06.
 */

@Controller
public class UpdateEmployeeList {


    @Autowired
    staffRepository repository;

    @RequestMapping(value = "/updatelist")
    public ModelAndView view(){
        ModelAndView view = new ModelAndView("UpdateEmployeeList");

        view.addObject("itemList",repository.findAll());
        return view;
    }



}
