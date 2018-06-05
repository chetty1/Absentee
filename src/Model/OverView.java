package Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by chett_000 on 2018/03/08.
 */
@Document(collection = "Overview")
public class OverView {

    @Id
    private String id;
    private Staff staff;
    private String leaveDays;
    private String overTimeHrshalf;
    private String overtimeHrsDouble;
    private String hrsWorked;
    private String hrsLate;
    private String unpaidLeave;
    private String sickLeave;
    private String famRespon;
    private String onSiteLocal;
    private String onSiteAway;
    private String annualLeave;
    private String otLeave;
    private boolean approved;
    private String date;


    public OverView() {

    }

    public OverView(Staff staff, String overTimeHrshalf, String overtimeHrsDouble, String hrsWorked, String hrsLate, String unpaidLeave, String sickLeave, String famRespon, String onSiteLocal, String onSiteAway, String annualLeave, String otLeave, boolean approved, String leaveDays, String date) {

        this.staff = staff;
        this.overTimeHrshalf = overTimeHrshalf;
        this.overtimeHrsDouble = overtimeHrsDouble;
        this.hrsWorked = hrsWorked;
        this.hrsLate = hrsLate;
        this.unpaidLeave = unpaidLeave;
        this.sickLeave = sickLeave;
        this.famRespon = famRespon;
        this.onSiteLocal = onSiteLocal;
        this.onSiteAway = onSiteAway;
        this.annualLeave = annualLeave;
        this.otLeave = otLeave;
        this.approved = approved;
        this.leaveDays = leaveDays;
        this.date = date;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLeaveDays(String leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getLeaveDays() {
        return leaveDays;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getOverTimeHrshalf() {
        return overTimeHrshalf;
    }

    public void setOverTimeHrshalf(String overTimeHrshalf) {
        this.overTimeHrshalf = overTimeHrshalf;
    }

    public String getOvertimeHrsDouble() {
        return overtimeHrsDouble;
    }

    public void setOvertimeHrsDouble(String overtimeHrsDouble) {
        this.overtimeHrsDouble = overtimeHrsDouble;
    }

    public String getHrsWorked() {
        return hrsWorked;
    }

    public void setHrsWorked(String hrsWorked) {
        this.hrsWorked = hrsWorked;
    }

    public String getHrsLate() {
        return hrsLate;
    }

    public void setHrsLate(String hrsLate) {
        this.hrsLate = hrsLate;
    }

    public String getUnpaidLeave() {
        return unpaidLeave;
    }

    public void setUnpaidLeave(String unpaidLeave) {
        this.unpaidLeave = unpaidLeave;
    }

    public String getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(String sickLeave) {
        this.sickLeave = sickLeave;
    }

    public String getFamRespon() {
        return famRespon;
    }

    public void setFamRespon(String famRespon) {
        this.famRespon = famRespon;
    }

    public String getOnSiteLocal() {
        return onSiteLocal;
    }

    public void setOnSiteLocal(String onSiteLocal) {
        this.onSiteLocal = onSiteLocal;
    }

    public String getOnSiteAway() {
        return onSiteAway;
    }

    public void setOnSiteAway(String onSiteAway) {
        this.onSiteAway = onSiteAway;
    }

    public String getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(String annualLeave) {
        this.annualLeave = annualLeave;
    }

    public String getOtLeave() {
        return otLeave;
    }

    @Override
    public String toString() {
        return "OverView{" +
                "overTimeHrshalf='" + overTimeHrshalf + '\'' +
                ", overtimeHrsDouble='" + overtimeHrsDouble + '\'' +
                ", hrsWorked='" + hrsWorked + '\'' +
                ", hrsLate='" + hrsLate + '\'' +
                ", unpaidLeave='" + unpaidLeave + '\'' +
                ", sickLeave='" + sickLeave + '\'' +
                ", famRespon='" + famRespon + '\'' +
                ", onSiteLocal='" + onSiteLocal + '\'' +
                ", onSiteAway='" + onSiteAway + '\'' +
                ", annualLeave='" + annualLeave + '\'' +
                ", otLeave='" + otLeave + '\'' +
                '}';
    }

    public void setOtLeave(String otLeave) {
        this.otLeave = otLeave;
    }

}
