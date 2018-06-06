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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chett_000 on 2017/06/03.
 */
@Controller
public class registerController {

    @Autowired
    staffRepository repo;

    @Autowired
    overviewRepository repository;

    @RequestMapping(value = "/register")
    public String view() {


        return "regAbsentee";
    }

    @RequestMapping(value = "/getName", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Staff> getTags(@RequestParam String Name) {
        System.out.println(repo.findByNameLike(Name));

        ArrayList<Staff> staffList = (ArrayList<Staff>) repo.findByNameLike(Name);
        return staffList;

    }


    @RequestMapping(value = "/reguser", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity reguser(HttpServletRequest request, HttpServletResponse response) throws ParseException {

        String name = request.getParameter("name");
        Staff staff1 = repo.findByName(name);
        String beforeTime = request.getParameter("beforeTime");
        String absent = request.getParameter("absent");
        String localbefore = request.getParameter("onsiteLocalbefore");
        String awaybefore = request.getParameter("onsiteawaybefore");
        String doublepay = request.getParameter("doublepay");
        String timeandahalf = request.getParameter("timeandahalf");
        String date = request.getParameter("date");
        String holiday = request.getParameter("holiday");
        String hourslate = request.getParameter("hourslate");
        boolean holidaybool = Boolean.parseBoolean(holiday);
        boolean absentbool = Boolean.parseBoolean(absent);


        if (date == null || date.equals("")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {

            if (absentbool) {

                repository.save(new OverView(staff1, "00:00", "00:00", "00:00", "00:00", "0", "0", "0", "00:00", "00:00", "0", "0", false, "0", date));


                return new ResponseEntity(HttpStatus.OK);
            } else if (holidaybool) {
                ArrayList<Staff> staffList = new ArrayList<>(repo.findAll());

                for (Staff staff : staffList) {
                    if (staff.getHoursWorked() == null) {
                        repository.save(new OverView(staff, "00:00", "00:00", "00:00", "00:00", "0", "0", "0", "00:00", "00:00", "0", "0", true, "0", date));

                    } else {
                        repository.save(new OverView(staff, "00:00", "00:00", staff.getHoursWorked(), "00:00", "0", "0", "0", "00:00", "00:00", "0", "0", true, "0", date));

                    }


                }
                return new ResponseEntity(HttpStatus.OK);
            } else if (!absentbool && !holidaybool) {
                repository.save(new OverView(staff1, convertTime(timeandahalf),convertTime(doublepay), convertTime(beforeTime), convertTime(hourslate), "0", "0", "0", convertTime(localbefore),convertTime(awaybefore), "0", "0", true, "0", date));

                return new ResponseEntity(HttpStatus.OK);

            }


        }
        return new ResponseEntity(HttpStatus.OK);
    }


    public String convertTime(String userInput) {
       double input= Double.parseDouble(userInput);


        int hours = (int)input;


        double minutestmp = ((input - hours) * 60);
        int minutes = (int)minutestmp;
        String formatted = String.format("%02d:%02d", hours,minutes);

       // String time = String.valueOf(hours)+":"+String.valueOf(minutes);
        return formatted;
    }
}

  /*  public String addTime(String time1, String time2) {
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
*/
