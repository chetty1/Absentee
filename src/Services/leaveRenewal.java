package Services;


import Model.Staff;
import Repositories.staffRepository;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class leaveRenewal {

    @Autowired
    staffRepository repo;

    @PostConstruct

    public void renewLeave() throws ParseException {




        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        SimpleDateFormat format = new SimpleDateFormat("dd");
        SimpleDateFormat year = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<Staff> list = (ArrayList<Staff>) repo.findAll();

            for (Staff staff : list) {
                if (!staff.employmentDate.equals("")) {

                    if (format.format(new Date()).equals(format.format(year.parse(staff.getEmploymentDate())))) {

                        staff.setLeaveBalance(Double.parseDouble(decimalFormat.format(staff.getLeaveBalance() + staff.getLeaveDue())));
                        repo.save(staff);
                    }
                }
        }


        for (Staff staff : list) {
            if (!staff.employmentDate.equals("")) {
                DateTime emptTime = new DateTime(year.parse(staff.getEmploymentDate()));
                DateTime time = new DateTime();


                int days = Days.daysBetween(emptTime, time).getDays();



                if ( days%1095 == 0 ) {
                    staff.setSickLeaveBalance((staff.getSickLeaveDue()));
                    repo.save(staff);
                }

                if(days%365 == 0){
                    staff.setFamRespon(staff.getFamResponDue());
                    repo.save(staff);
                }
            }

        }


    }


}
