package WebsiteControllers;

import Model.OverView;
import Repositories.overviewRepository;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by chett_000 on 2018/04/23.
 */
@Controller
public class editList {

    @Autowired
    overviewRepository repo;

    private String search;

    @RequestMapping("/editlist")
    public ModelAndView view() throws ParseException {
        ModelAndView view = new ModelAndView("EditList");
        ArrayList<OverView> list = new ArrayList<>(repo.findAll());
        ArrayList<OverView> dateList = new ArrayList<>();


        for(OverView oview:list){
            SimpleDateFormat year = new SimpleDateFormat("dd/MM/yyyy");
            DateTime emptTime = new DateTime(year.parse(oview.getDate()));
            DateTime time = new DateTime();

            int days = Days.daysBetween(emptTime, time).getDays();


            if(days<=14){
                dateList.add(oview);

            }


        }
        Collections.reverse(dateList);

        view.addObject("editList",dateList);

        return view;
    }
    @RequestMapping(value = "/getsearch",method = RequestMethod.POST)
    public void getsearch(HttpServletRequest request, HttpServletResponse response){
        search = request.getParameter("search");

    }
    @RequestMapping(value = "/editlist",method = RequestMethod.POST)
    public ModelAndView post() throws ParseException {
        ModelAndView view = new ModelAndView("EditList");
        ArrayList<OverView> list = new ArrayList<>(repo.findByNameLike(search));
        ArrayList<OverView> dateList = new ArrayList<>();


        for(OverView oview:list){
            SimpleDateFormat year = new SimpleDateFormat("dd/MM/yyyy");
            DateTime emptTime = new DateTime(year.parse(oview.getDate()));
            DateTime time = new DateTime();

            int days = Days.daysBetween(emptTime, time).getDays();


            if(days<=14){
                dateList.add(oview);

            }


        }

        view.addObject("editList",dateList);

        return view;    }




}
