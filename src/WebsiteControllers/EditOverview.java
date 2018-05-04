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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            view.addObject("checked","checked");

        }

        view.addObject("name",overView.get().getStaff().getName());
        view.addObject("date", overView.get().getDate());
        return view;
    }




    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity reguser1(HttpServletRequest request, HttpServletResponse response) throws ParseException {

        String name = request.getParameter("name");
        Staff staff1 = oview.getStaff();
        String overtimeType = request.getParameter("overtimeType");
        String beforeTime = request.getParameter("beforeTime");
        String afterTime = request.getParameter("afterTime");
        String absent = request.getParameter("absent");
        String localbefore = request.getParameter("onsiteLocalbefore");
        String localafter = request.getParameter("onsiteafter");
        String awaybefore = request.getParameter("onsiteawaybefore");
        String awayafter = request.getParameter("onsiteawayAfter");
        String date = request.getParameter("date");

        boolean absentbool = Boolean.parseBoolean(absent);
        if (localafter.equals("") || localafter == null) {
            localafter = "00:00";
        }
        if (localbefore.equals("") || localbefore == null) {
            localbefore = "00:00";
        }
        if (awayafter.equals("") || awayafter == null) {
            awayafter = "00:00";
        }
        if (awaybefore.equals("") || awaybefore == null) {
            awayafter = "00:00";
        }
        String hrsOnsiteLocal = getHoursandMinWorked(localbefore, localafter);
        String hrsOnsiteAway = getHoursandMinWorked(awaybefore, awayafter);

        OverView view =new OverView(staff1, "00:00", "00:00", "00:00", "00:00", "0", "0", "0", "00:00", "00:00", "0", "0", true, "0", date); ;
view.setId(oview.getId());



        if (absentbool) {
            view.setApproved(false);
        } else {
            view.setApproved(true);

            PeriodFormatter hoursMinutes = new PeriodFormatterBuilder()
                    .appendHours()
                    .appendSeparator(":")
                    .appendMinutes()
                    .toFormatter();

            Period late = Period.parse(getHoursandMinDifference(staff1, beforeTime, afterTime, date), hoursMinutes);
            late = late.minusMinutes(15);
            late = late.normalizedStandard(PeriodType.time());
            String totaltimesite1 = addTime(hrsOnsiteLocal, String.valueOf(late.getHours()) + ":" + String.valueOf(late.getMinutes()));
            Period plate = Period.parse(addTime(totaltimesite1, hrsOnsiteAway), hoursMinutes);
            plate= plate.normalizedStandard(PeriodType.time());
            if (plate.getMinutes() >= 0 && plate.getHours() >= 0 && !absentbool) {
                switch (overtimeType) {
                    case "Time and a Half":
                        view.setOverTimeHrshalf(String.valueOf(plate.getHours()) + ":" + String.valueOf(plate.getMinutes()));
                        break;
                    case "Double Pay":
                        view.setOvertimeHrsDouble(String.valueOf(plate.getHours()) + ":" + String.valueOf(plate.getMinutes()));
                        break;
                }

            } else if (plate.getMinutes() <= 0 && plate.getHours() <= 0 && !absentbool) {

                view.setHrsLate(String.valueOf(plate.getHours()) + ":" + String.valueOf(plate.getMinutes()));
            }


            SimpleDateFormat day = new SimpleDateFormat("EEEE");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            String days = day.format(format.parse(date));
            if (days.equals("Friday")) {
                Period period=Period.parse(getHoursandMinWorked(beforeTime, afterTime), hoursMinutes).plusMinutes(15).normalizedStandard(PeriodType.time());
                String totaltimesite = addTime(hrsOnsiteLocal,String.valueOf(period.getHours())+":"+String.valueOf(period.getMinutes() ));                String time = addTime(totaltimesite, hrsOnsiteAway);
                Period halfot = Period.parse(view.getOverTimeHrshalf(), hoursMinutes);
                Period doubleot = Period.parse(view.getOvertimeHrsDouble(), hoursMinutes);
                Period hworked = Period.parse(time, hoursMinutes);
                hworked = hworked.minusMinutes(halfot.getMinutes() + doubleot.getMinutes());
                hworked = hworked.minusHours(halfot.getHours() + doubleot.getHours());
                hworked = hworked.normalizedStandard(PeriodType.time());
                view.setHrsWorked(String.valueOf(hworked.getHours()) + ":" + String.valueOf(hworked.getMinutes()));
                view.setOnSiteAway(hrsOnsiteAway);
                view.setOnSiteLocal(hrsOnsiteLocal);
            } else {

                Period period=  Period.parse(getHoursandMinWorked(beforeTime, afterTime), hoursMinutes).minusMinutes(15).normalizedStandard(PeriodType.time());

              String  totaltimesite = addTime(hrsOnsiteLocal,String.valueOf(period.getHours())+":"+String.valueOf(period.getMinutes()));
                String time = addTime(totaltimesite, hrsOnsiteAway);
                Period halfot = Period.parse(view.getOverTimeHrshalf(), hoursMinutes);
                Period doubleot = Period.parse(view.getOvertimeHrsDouble(), hoursMinutes);
                Period hworked = Period.parse(time, hoursMinutes);
                hworked = hworked.minusMinutes(halfot.getMinutes() + doubleot.getMinutes());
                hworked = hworked.minusHours(halfot.getHours() + doubleot.getHours());
                hworked = hworked.normalizedStandard(PeriodType.time());
               view.setHrsWorked(String.valueOf(hworked.getHours()) + ":" + String.valueOf(hworked.getMinutes()));
                view.setOnSiteAway(hrsOnsiteAway);
                view.setOnSiteLocal(hrsOnsiteLocal);
            }
        }


        if (staff1 == null || date == null || date.equals("")) {


            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        } else {
            System.out.println(view);
            repository.save(view);
            return new ResponseEntity(HttpStatus.OK);

        }

    }


    public String addTime(String time1, String time2) {
        PeriodFormatter hoursMinutes = new PeriodFormatterBuilder()
                .appendHours()
                .appendSeparator(":")
                .appendMinutes()
                .toFormatter();

        Period ptime1 = Period.parse(time1, hoursMinutes);
        Period ptime2 = Period.parse(time2, hoursMinutes);

        ptime1 = ptime1.plusMinutes(ptime2.getMinutes());
        ptime1 = ptime1.plusHours(ptime2.getHours());

        ptime1 = ptime1.normalizedStandard(PeriodType.time());

        return String.valueOf(ptime1.getHours()) + ":" + String.valueOf(ptime1.getMinutes());

    }


    public String getHoursandMinDifference(Staff staff, String beforeTime, String afterTime, String date) throws ParseException {

        PeriodFormatter hoursMinutes = new PeriodFormatterBuilder()
                .appendHours()
                .appendSeparator(":")
                .appendMinutes()
                .toFormatter();


        String hoursandmin = getHoursandMinWorked(beforeTime, afterTime);
        System.out.println(hoursandmin);
        Period workday = Period.parse(hoursandmin, hoursMinutes);

        Period hoursWorked = Period.parse(staff.getHoursWorked(), hoursMinutes);
        SimpleDateFormat day = new SimpleDateFormat("EEEE");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String days = day.format(format.parse(date));
        if (days.equals("Friday")) {
            staff.setHoursWorked("07:00");
            hoursWorked = Period.parse(staff.getHoursWorked(), hoursMinutes);
            workday = workday.plusMinutes(30);
            workday = workday.minusHours(hoursWorked.getHours());
            workday = workday.minusMinutes(hoursWorked.getMinutes());


        }
        else if(days.equals("Saturday") ||days.equals("Sunday")){


        }

        else {
            workday = workday.minusHours(hoursWorked.getHours());
            workday = workday.minusMinutes(hoursWorked.getMinutes());
        }

        workday = workday.normalizedStandard(PeriodType.time());
        return String.valueOf(workday.getHours()) + ":" + String.valueOf(workday.getMinutes());

    }

    public String getHoursandMinWorked(String beforeTime, String afterTime) throws ParseException {
        SimpleDateFormat overtimeFormat = new SimpleDateFormat("HH:mm");
        DateTime startTime = new DateTime(overtimeFormat.parse(beforeTime));
        DateTime endTime = new DateTime(overtimeFormat.parse(afterTime));


        Period overtimePeriod = new Period(startTime, endTime);

        String hoursandmin = String.valueOf(overtimePeriod.getHours()) + ":" + String.valueOf(overtimePeriod.getMinutes());

        return hoursandmin;
    }


}
