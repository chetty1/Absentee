package WebsiteControllers;

import Model.OverView;
import Model.Staff;
import Repositories.overviewRepository;
import Repositories.staffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by chett_000 on 2018/02/28.
 */

@Controller
public class approveController {

    @Autowired
    staffRepository repo;

    @Autowired
    overviewRepository overviewRepo;


    @RequestMapping(value = "/{id}/approve")
    public ModelAndView view(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("approveAbsentee");
        Optional<OverView> overView = overviewRepo.findById(id);
        view.addObject("name", overView.get().getStaff().getName());

        view.addObject("id", id);
        return view;
    }

    @RequestMapping(value = "/{id}/approveuser")
    public
    @ResponseBody
    ResponseEntity ajax(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {

        Optional<OverView> overView = overviewRepo.findById(id);
        String leaveSelect = request.getParameter("leaveSelect");


        OverView newView = setLeave(leaveSelect, overView.get(), "1");

        overviewRepo.save(newView);

        if (request.getParameter("name") == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        } else {
            return new ResponseEntity(HttpStatus.OK);
        }

    }


    public OverView setLeave(String type, OverView view, String leaveDays) {

        switch (type) {
            case "Unpaid Leave":
                view.setUnpaidLeave(leaveDays);
                Staff staff = view.getStaff();
                // staff.setLeaveBalance(staff.getLeaveBalance()-Integer.parseInt(leaveDays));
                repo.save(staff);
                view.setStaff(staff);
                break;
            case "Sick Leave":
                view.setSickLeave(leaveDays);
                Staff staff6 = view.getStaff();
                //staff6.setSickLeaveBalance(staff6.getSickLeaveBalance()-Integer.parseInt(leaveDays));
                repo.save(staff6);
                view.setStaff(staff6);
                break;
            case "Family Responsibility Leave":
                view.setFamRespon(leaveDays);
                Staff staff1 = view.getStaff();
                // staff1.setLeaveBalance(staff1.getLeaveBalance()-Integer.parseInt(leaveDays));
                repo.save(staff1);
                view.setStaff(staff1);
                break;
         /*   case "On Site(Local)":
                view.setOnSiteLocal(leaveDays);
                Staff staff2= view.getStaff();
                staff2.setLeaveBalance(staff2.getLeaveBalance()-Integer.parseInt(leaveDays));
                repo.save(staff2);
                view.setStaff(staff2);
                break;
            case "On Site(Overseas)":
                view.setOnSiteAway(leaveDays);
                Staff staff3= view.getStaff();
                staff3.setLeaveBalance(staff3.getLeaveBalance()-Integer.parseInt(leaveDays));
                repo.save(staff3);
                view.setStaff(staff3);
                break;*/
            case "Annual Leave":
                view.setAnnualLeave(leaveDays);
                Staff staff4 = view.getStaff();
                // staff4.setLeaveBalance(staff4.getLeaveBalance()-Integer.parseInt(leaveDays));
                repo.save(staff4);
                view.setStaff(staff4);
                break;
            case "Overtime Leave":
                view.setOtLeave(leaveDays);
                Staff staff5 = view.getStaff();
                //  staff5.setLeaveBalance(staff5.getLeaveBalance()-Integer.parseInt(leaveDays));
                repo.save(staff5);
                view.setStaff(staff5);
                break;

        }


        view.setApproved(true);
        return view;
    }


}
