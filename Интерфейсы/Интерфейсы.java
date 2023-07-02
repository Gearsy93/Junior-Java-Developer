class HourlyStaff implements Staff {
private int workTime;
public int getWorkTime() {
return workTime;
}

HourlyStaff(int workTime) {
this.workTime = (workTime < 4 || workTime > 10) ? 0 : workTime;
}

public int calculateSalary(int salary) {
return salary < 0 ? 0 : salary * this.workTime;
}
}

class MonthlyStaff implements Staff {
int workDays;
public int getWorkDays() {
return workDays;
}
int premium;
public int getPremium() {
return premium;
}
MonthlyStaff(int workDays, int premium) {
this.workDays = (workDays <= 0 || workDays > 30) ? 0 : workDays;
this.premium = (premium < 0 || premium > 10000) ? 0 : premium;
}
public int calculateSalary(int salary) {
return salary < 0 ? premium : salary * workDays + premium;
}
}

class WeeklyStaff extends MonthlyStaff implements Staff {
private int workWeeks;
public int getWorkWeeks() {
return workWeeks;
}
WeeklyStaff(int workDays, int premium, int workWeeks) {
super(workDays, premium);
this.workWeeks = (workWeeks < 2 || workWeeks > 4) ? 0 : workWeeks;
}

@Override
public int calculateSalary(int salary) {
return salary < 0 ? 0 : this.workWeeks * salary;
}

void printYearSalary(int salary) {
System.out.println(Integer.toString(salary < 0 ? 0 : workWeeks*salary*26));
}
}

public interface Staff {
int calculateSalary(int salary);
}
