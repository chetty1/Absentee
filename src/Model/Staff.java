package Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by chett_000 on 2017/05/10.
 */

@Document(collection = "Staff")
public class Staff {
    public Staff() {

    }

    @Id
    public String id;
    public String name;
    public String username;
    public String password;
    public double leaveBalance;
    public int famRespon;
    public String employmentDate;
    public String hoursWorked;
    public String department;
    public int sickLeaveBalance;
    public int  sickLeaveDue;
    public double leaveDue;
    public int famResponDue;

    public ArrayList<String> role;


    public Staff(String name, String username, String password, ArrayList<String> role, String employmentDate, int leaveBalance, String hoursWorked, String department,int sickLeaveBalance,int sickLeaveDue,double leaveDue,int famRespon,int famResponDue) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.employmentDate = employmentDate;
        this.leaveBalance = leaveBalance;
        this.hoursWorked = hoursWorked;
        this.department = department;
        this.sickLeaveBalance=sickLeaveBalance;
        this.sickLeaveDue = sickLeaveDue;
        this.leaveDue = leaveDue;
        this.famRespon=famRespon;
        this.famResponDue = famResponDue;

    }

    public int getFamRespon() {
        return famRespon;
    }

    public void setFamRespon(int famRespon) {
        this.famRespon = famRespon;
    }

    public int getFamResponDue() {
        return famResponDue;
    }

    public void setFamResponDue(int famResponDue) {
        this.famResponDue = famResponDue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(double leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(String hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSickLeaveBalance() {
        return sickLeaveBalance;
    }

    public void setSickLeaveBalance(int sickLeaveBalance) {
        this.sickLeaveBalance = sickLeaveBalance;
    }

    public int getSickLeaveDue() {
        return sickLeaveDue;
    }

    public void setSickLeaveDue(int sickLeaveDue) {
        this.sickLeaveDue = sickLeaveDue;
    }

    public double getLeaveDue() {
        return leaveDue;
    }

    public void setLeaveDue(double leaveDue) {
        this.leaveDue = leaveDue;
    }

    public ArrayList<String> getRole() {
        return role;
    }

    public void setRole(ArrayList<String> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", leaveBalance=" + leaveBalance +
                ", employmentDate='" + employmentDate + '\'' +
                ", hoursWorked='" + hoursWorked + '\'' +
                ", department='" + department + '\'' +
                ", sickLeaveBalance=" + sickLeaveBalance +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        Staff staff = (Staff) o;
        return this.department.equals(staff.department) &&
                this.id.equals(staff.id) &&
                this.name.equals(staff.name) &&
                this.username.equals(staff.username) &&
                this.hoursWorked.equals(staff.hoursWorked)&&
                this.employmentDate.equals(staff.employmentDate) &&
                this.leaveBalance == staff.leaveBalance &&
                this.leaveDue == staff.leaveDue &&
                this.sickLeaveDue == staff.sickLeaveDue &&
                this.famResponDue == staff.famResponDue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                id,
                department,
                hoursWorked,
                department,
                username,
                password,
                employmentDate,
                leaveBalance,
                leaveDue,
                sickLeaveDue,
                famResponDue

        );
    }
}
