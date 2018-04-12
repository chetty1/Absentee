package WebsiteControllers;

import Model.OverView;
import Model.Staff;
import Repositories.overviewRepository;
import Repositories.staffRepository;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chett_000 on 2018/02/27.
 */


@Controller
public class statsController {


    private String before;
    private String after;

    @Autowired
    overviewRepository repo;

    @Autowired
    staffRepository staffRepo;


    @RequestMapping(value = "/getstatsdate")
    public void getDate(HttpServletRequest request, HttpServletResponse response) {
        before = request.getParameter("before");
        after = request.getParameter("after");

    }

    @RequestMapping(value = "/stats", method = RequestMethod.POST)
    public ModelAndView post() throws ParseException {

        ModelAndView modelAndView = new ModelAndView("Stats");
        ArrayList<OverView> list = new ArrayList<>(repo.findAll());
        ArrayList<OverView> dateComp = new ArrayList<>();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");


        for (int i = 0; i < list.size(); i++) {
            if (format.parse(before).compareTo(format.parse(list.get(i).getDate())) == -1 && format.parse(after).compareTo(format.parse(list.get(i).getDate())) == 1) {
                dateComp.add(list.get(i));
            } else if (format.parse(before).compareTo(format.parse(list.get(i).getDate())) == 0) {
                dateComp.add(list.get(i));
            } else if (format.parse(after).compareTo(format.parse(list.get(i).getDate())) == 0) {
                dateComp.add(list.get(i));
            }
        }


        HashMap<String, OverView> map = new HashMap<>();
        HashMap<String, Integer> absentdayFreq = new HashMap<>();
        HashMap<String, Integer> leaveDayFreq = new HashMap<>();
        HashMap<String, Integer> typeFreq = new HashMap<>();
        HashMap<String, Integer> staffFreq = new HashMap<>();
        HashMap<String,Integer> staffleaveFreq =  new HashMap<>();
        HashMap<String,OverView> overtimeComp = new HashMap<>();

        ArrayList<Staff> staff = new ArrayList<>(staffRepo.findAll());

        for (int i = 0; i < staff.size(); i++) {
            staffFreq.put(staff.get(i).getName(), 0);
            staffleaveFreq.put(staff.get(i).getName(),0);

            OverView view = new OverView(staff.get(i), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", true, "0","0");

            overtimeComp.put(staff.get(i).getName(),view);

        }


        for (OverView overView : dateComp) {
            OverView dupliate = map.get(overView.getStaff().getName());

            if (dupliate == null) {
                dupliate = overView;
            } else {
                dupliate.setOverTimeHrshalf(addTime(overView.getOverTimeHrshalf(), dupliate.getOverTimeHrshalf()));
                dupliate.setOvertimeHrsDouble(addTime(overView.getOvertimeHrsDouble(), dupliate.getOvertimeHrsDouble()));
                dupliate.setHrsWorked(addTime(overView.getHrsWorked(), dupliate.getHrsWorked()));
                dupliate.setHrsLate(addTime(overView.getHrsLate(), dupliate.getHrsLate()));
                dupliate.setUnpaidLeave(String.valueOf(Integer.parseInt(overView.getUnpaidLeave()) + Integer.parseInt(dupliate.getUnpaidLeave())));
                dupliate.setSickLeave(String.valueOf(Integer.parseInt(overView.getSickLeave()) + Integer.parseInt(dupliate.getSickLeave())));
                dupliate.setFamRespon(String.valueOf(Integer.parseInt(overView.getFamRespon()) + Integer.parseInt(dupliate.getFamRespon())));
                dupliate.setOnSiteLocal(addTime(overView.getOnSiteLocal(),dupliate.getOnSiteLocal()));
                dupliate.setOnSiteAway(addTime(overView.getOnSiteAway(),dupliate.getOnSiteAway()));
                dupliate.setAnnualLeave(String.valueOf(Integer.parseInt(overView.getAnnualLeave()) + Integer.parseInt(dupliate.getAnnualLeave())));
                dupliate.setOtLeave(String.valueOf(Integer.parseInt(overView.getOtLeave()) + Integer.parseInt(dupliate.getOtLeave())));

            }


            map.put(overView.getStaff().getName(), dupliate);

            addToMap(absentdayFreq, overView.getSickLeave(), dayFormat.format(format.parse(overView.getDate())));
            int leave = Integer.parseInt(overView.getUnpaidLeave()) + Integer.parseInt(overView.getFamRespon()) + Integer.parseInt(overView.getAnnualLeave()) + Integer.parseInt(overView.getOtLeave());
            addToMap(staffFreq, overView.getSickLeave(), overView.getStaff().getName());
            addToMap(leaveDayFreq, String.valueOf(leave), dayFormat.format(format.parse(overView.getDate())));
            addToMap(staffleaveFreq,String.valueOf(leave),overView.getStaff().getName());
            addTypeToMap(typeFreq, overView);
            addComptoMap(overtimeComp,overView);

        }

        String totaltime = "00:00";
        String hrsLate = "00:00";
        int sickLeave = 0;
        int leave = 0;
        for (Map.Entry<String, OverView> entry : map.entrySet()) {

            totaltime = addTime(totaltime, entry.getValue().getHrsWorked());
            hrsLate = addTime(hrsLate, entry.getValue().getHrsLate());
            sickLeave = sickLeave + Integer.parseInt(entry.getValue().getSickLeave());
            leave = leave + Integer.parseInt(entry.getValue().getUnpaidLeave()) + Integer.parseInt(entry.getValue().getFamRespon()) + Integer.parseInt(entry.getValue().getAnnualLeave()) + Integer.parseInt(entry.getValue().getOtLeave());


        }
        modelAndView.addObject("compFreq",overtimeComp);
        modelAndView.addObject("staffleaveFreq",staffleaveFreq);
        modelAndView.addObject("staffFreq",staffFreq);
        modelAndView.addObject("typeFrequency", typeFreq);
        modelAndView.addObject("leaveFreq", leaveDayFreq);
        modelAndView.addObject("dayFrequency", absentdayFreq);
        modelAndView.addObject("Leave", leave);
        modelAndView.addObject("sickLeave", sickLeave);
        modelAndView.addObject("hoursLate", hrsLate);
        modelAndView.addObject("hoursWorked", totaltime);
        modelAndView.addObject("dateBefore",before);
        modelAndView.addObject("dateAfter",after);
        return modelAndView;
    }


    public void addToMap(HashMap<String, Integer> map, String leave, String key) {
        Integer day = map.get(key);
        if (day == null) {
            day = Integer.parseInt(leave);
        } else {
            day = day + Integer.parseInt(leave);
        }
        map.put(key, day);

    }
public void addComptoMap(HashMap<String,OverView> map,OverView view){
    OverView oview =  map.get(view.getStaff().getName());

    if(oview==null){
        oview = new OverView();
        oview.setHrsWorked(getHours(view.getHrsWorked()));
        oview.setOvertimeHrsDouble(getHours(view.getOvertimeHrsDouble()));
        oview.setOverTimeHrshalf(getHours(view.getOverTimeHrshalf()));
        oview.setHrsLate(String.valueOf(Integer.parseInt(getHours(view.getHrsLate()))));
    }
    else{
        oview.setHrsWorked(getHours(addTime(oview.getHrsWorked(),view.getHrsWorked())));
        oview.setOvertimeHrsDouble(getHours(addTime(oview.getOvertimeHrsDouble(),view.getOvertimeHrsDouble())));
        oview.setOverTimeHrshalf(getHours(addTime(oview.getOverTimeHrshalf(),view.getOverTimeHrshalf())));
        oview.setHrsLate(String.valueOf(Integer.parseInt(getHours(addTime(view.getHrsLate(),oview.getHrsLate())))));

    }
    map.put(view.getStaff().getName(),oview);
    System.out.println(map.get("Joshua Robb"));

}


    public void addTypeToMap(HashMap<String, Integer> map, OverView view) {
        String types[] = {"Unpaid Leave", "Sick Leave", "Family Respon", "Annual Leave", "OT Leave"};

        for (int i = 0; i < types.length; i++) {
            Integer type = map.get(types[i]);
            if (type == null) {
                switch (types[i]) {
                    case "Unpaid Leave":
                        type = Integer.parseInt(view.getUnpaidLeave());
                        break;
                    case "Sick Leave":
                        type = Integer.parseInt(view.getSickLeave());
                        break;
                    case "Family Respon":
                        type = Integer.parseInt(view.getFamRespon());
                        break;
                    case "Annual Leave":
                        type = Integer.parseInt(view.getAnnualLeave());
                        break;
                    case "OT Leave":
                        type = Integer.parseInt(view.getOtLeave());
                        break;

                }
            } else {

                switch (types[i]) {
                    case "Unpaid Leave":
                        type = type + Integer.parseInt(view.getUnpaidLeave());

                        break;
                    case "Sick Leave":
                        type = type + Integer.parseInt(view.getSickLeave());
                        break;
                    case "Family Respon":
                        type = type + Integer.parseInt(view.getFamRespon());
                        break;
                    case "Annual Leave":
                        type = type + Integer.parseInt(view.getAnnualLeave());
                        break;
                    case "OT Leave":
                        type = type + Integer.parseInt(view.getOtLeave());
                        break;

                }

            }

            map.put(types[i], type);
        }


    }

    public String getHours(String time1){
        PeriodFormatter hoursMinutes = new PeriodFormatterBuilder()
                .appendHours()
                .appendSeparator(":")
                .appendMinutes()
                .toFormatter();
        Period period1 = Period.parse(time1, hoursMinutes);

        return  String.valueOf(period1.getHours());

    }


    public String addTime(String time1, String time2) {
        if(time1.equals("0")){
            time1 = "00:00";
        }
        if(time2.equals("0")){
            time2 = "00:00";
        }

        PeriodFormatter hoursMinutes = new PeriodFormatterBuilder()
                .appendHours()
                .appendSeparator(":")
                .appendMinutes()
                .toFormatter();

        Period period1 = Period.parse(time1, hoursMinutes);
        Period period2 = Period.parse(time2, hoursMinutes);

        period1 = period1.plusHours(period2.getHours());
        period1 = period1.plusMinutes(period2.getMinutes());


        PeriodType type = PeriodType.time();


        period1 = period1.normalizedStandard(type);


        String date3 = String.valueOf(period1.getHours()) + ":" + String.valueOf(period1.getMinutes());

        return date3;
    }

    @RequestMapping(value = "/stats")
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView("Stats");

        modelAndView.addObject("beforeDate","Date Before");
        modelAndView.addObject("afterDate", "Date After");


        return modelAndView;
    }
}
