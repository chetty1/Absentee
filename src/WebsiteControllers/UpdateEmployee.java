package WebsiteControllers;

import Model.Staff;
import Repositories.staffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by chett_000 on 2018/03/07.
 */

@Controller
public class UpdateEmployee {


    @Autowired
    staffRepository repo;
    Staff staff;


    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {
        ModelAndView view = new ModelAndView("UpdateEmployee");
        Optional<Staff> find = repo.findById(id);
        staff = find.get();
        view.addObject("name", staff.getName());
        view.addObject("empDate", staff.getEmploymentDate());
        view.addObject("department", staff.getDepartment());
        view.addObject("hoursWorked", staff.getHoursWorked());
        view.addObject("leaveBalance", staff.getLeaveBalance());
        view.addObject("sickleaveBalance", staff.getSickLeaveBalance());
        view.addObject("sickleaveDue", staff.getSickLeaveDue());
        view.addObject("leaveDue", staff.getLeaveDue());
        view.addObject("famleaveDue",staff.getFamResponDue());
        view.addObject("famleaveBalance",staff.getFamRespon());


        view.addObject("id", id);
        return view;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void post(HttpServletRequest request, HttpServletResponse response) {


        String department = request.getParameter("department");
        String leaveBalance = request.getParameter("leaveBalance");
        String hoursWorked = request.getParameter("hoursWorked");
        String employmentDate = request.getParameter("employmentDate");
        String sickLeaveBalance = request.getParameter("sickleaveBalance");
        String sickleaveDue = request.getParameter("sickLeaveDue");
        String leaveDue = request.getParameter("leaveDue");
        String famResponDue = request.getParameter("famleaveDue");
        String famResponBalance = request.getParameter("famleaveBalance");



        staff.setFamResponDue(Integer.parseInt(famResponDue));
        staff.setFamRespon(Integer.parseInt(famResponBalance));
        staff.setSickLeaveDue(Integer.parseInt(sickleaveDue));
        staff.setLeaveDue(Double.parseDouble(leaveDue));
        staff.setDepartment(department);
        staff.setEmploymentDate(employmentDate);
        staff.setLeaveBalance(Double.parseDouble(leaveBalance));
        staff.setHoursWorked(hoursWorked);
        staff.setSickLeaveBalance(Integer.parseInt(sickLeaveBalance));
        System.out.println(staff);

        repo.save(staff);
    }

}
