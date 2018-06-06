package WebsiteControllers;

import Model.OverView;
import Model.Staff;
import Repositories.overviewRepository;
import Repositories.staffRepository;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by chett_000 on 2018/04/24.
 */

@Controller
public class EditOverview {

    @Autowired
    staffRepository repo;

    @Autowired
    overviewRepository repository;


    OverView oview;

    @RequestMapping(value = "{id}/edit")
    public ModelAndView view1(@PathVariable String id) {
ModelAndView view =  new ModelAndView("EditOverview");

        Optional<OverView> overView = repository.findById(id);
        oview = overView.get();

        if(!oview.isApproved()){
            view.addObject("absent","checked");

        }

        view.addObject("name",overView.get().getStaff().getName());
        view.addObject("hrsworked",convertFromTime(oview.getHrsWorked()));
        view.addObject("hrslate",convertFromTime(oview.getHrsLate()));
        view.addObject("onsitelocal",convertFromTime(oview.getOnSiteLocal()));
        view.addObject("onsiteaway",convertFromTime(oview.getOnSiteAway()));
        view.addObject("timeandahalf",convertFromTime(oview.getOverTimeHrshalf()));
        view.addObject("doublepay",convertFromTime(oview.getOvertimeHrsDouble()));
        view.addObject("date", overView.get().getDate());
        return view;
    }




    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public
   @ResponseBody
    ResponseEntity reguser1(HttpServletRequest request, HttpServletResponse response)  {
        String beforeTime = request.getParameter("beforeTime");
        String absent = request.getParameter("absent");
        String localbefore = request.getParameter("onsiteLocalbefore");
        String awaybefore = request.getParameter("onsiteawaybefore");
        String doublepay = request.getParameter("doublepay");
        String timeandahalf = request.getParameter("timeandahalf");
        String date = request.getParameter("date");
        String hourslate = request.getParameter("hourslate");
        boolean absentbool = Boolean.parseBoolean(absent);


        if (date == null || date.equals("")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {

            if (absentbool) {



                oview.setOverTimeHrshalf("00:00");
                oview.setOvertimeHrsDouble("00:00");
                oview.setHrsWorked("00:00");
                oview.setHrsLate("00:00");
                oview.setUnpaidLeave("0");
                oview.setSickLeave("0");
                oview.setFamRespon("0");


                oview.setOnSiteLocal("00:00");
                oview.setOnSiteAway("00:00");
                oview.setApproved(false);


                oview.setAnnualLeave("0");
                oview.setOtLeave("0");

                repository.save(oview);


                return new ResponseEntity(HttpStatus.OK);
            }
            else if (!absentbool ) {

                oview.setOverTimeHrshalf(convertTime(timeandahalf));
                oview.setOvertimeHrsDouble(convertTime(doublepay));
                oview.setHrsWorked(convertTime(beforeTime));
                oview.setHrsLate(convertTime(hourslate));
                oview.setUnpaidLeave("0");
                oview.setSickLeave("0");
                oview.setFamRespon("0");


                oview.setOnSiteLocal(convertTime(localbefore));
                oview.setOnSiteAway(convertTime(awaybefore));
                oview.setApproved(true);


                oview.setAnnualLeave("0");
                oview.setOtLeave("0");


                repository.save(oview);
                //System.out.println(oview.getId());
               return new ResponseEntity(HttpStatus.OK);

            }


        }
        return new ResponseEntity(HttpStatus.OK);

    }


    public String convertFromTime(String time){
        PeriodFormatter hoursMinutes = new PeriodFormatterBuilder()
                .appendHours()
                .appendSeparator(":")
                .appendMinutes()
                .toFormatter();
        Period period1 = Period.parse(time, hoursMinutes);

        period1 =period1.normalizedStandard(PeriodType.time());
        int hours = period1.getHours();
        int min = period1.getMinutes();

        return String.valueOf(hours+((double)min/(double) 60));

    }


    public String convertTime(String userInput) {
        double input= Double.parseDouble(userInput);


        int hours = (int)input;


        double minutestmp = ((input - hours) * 60);
        int minutes = (int)minutestmp;

        String formatted = String.format("%02d:%02d", hours,minutes);
        return formatted;
    }



}
