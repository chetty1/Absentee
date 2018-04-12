package WebsiteControllers;

import Model.OverView;
import Repositories.overviewRepository;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chett_000 on 2018/03/07.
 */
@Controller
public class FactoryOverviewController {

    @Autowired
    overviewRepository repo;
    private String before = "";

    private String after = "";

    @RequestMapping(value = "/getdate", method = RequestMethod.POST)
    public void getDates(HttpServletRequest request, HttpServletResponse response) {

        before = request.getParameter("before");
        after = request.getParameter("after");


    }

    @RequestMapping(value = "/factoryoverview", method = RequestMethod.POST)
    public ModelAndView model() throws ParseException {


        ModelAndView view = new ModelAndView("FactoryOverView");
        ArrayList<OverView> list = (ArrayList) repo.findAll();

        ArrayList<OverView> oview = new ArrayList<>();

        HashMap<String, OverView> map = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


        for (int i = 0; i < list.size(); i++) {
            if (format.parse(before).compareTo(format.parse(list.get(i).getDate())) == -1 && format.parse(after).compareTo(format.parse(list.get(i).getDate())) == 1) {
                oview.add(list.get(i));
            } else if (format.parse(before).compareTo(format.parse(list.get(i).getDate())) == 0) {
                oview.add(list.get(i));
            } else if (format.parse(after).compareTo(format.parse(list.get(i).getDate())) == 0) {
                oview.add(list.get(i));
            }
        }

        for (OverView overView : oview) {
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


        }
        view.addObject("datebefore", before);
        view.addObject("dateafter", after);

        view.addObject("spentList", map);

        return view;
    }


    @RequestMapping(value = "/factoryoverview")
    public String view() throws ParseException {
        return "FactoryOverView";
    }


    public String addTime(String time1, String time2) {

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


}
