package WebsiteControllers;

import Model.OverView;
import Repositories.overviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by chett_000 on 2018/02/28.
 */
@Controller
public class approveListController {

    @Autowired
    overviewRepository repo;

    @RequestMapping(value = "/approvals")
    public ModelAndView view() {
        ModelAndView view = new ModelAndView("approvalList");
        ArrayList<OverView> list = (ArrayList<OverView>) repo.findByApprovedFalse();



        view.addObject("itemList", list);
        return view;
    }
}
