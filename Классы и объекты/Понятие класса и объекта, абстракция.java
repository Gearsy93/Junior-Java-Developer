//1. Задание
class Person {
String surname;
double height;
double weight;
int year;

Person(String surname, int year) {
if (surname == null) {
this.surname = "";
}
else this.surname = surname;
if (year < 0) {
this.year = 0;
}
else this.year = year;
}

Person(String surname, double height, double weight, int year) {
if (surname == null) {
this.surname = "";
}
else this.surname = surname;
if (year < 0) {
this.year = 0;
}
else this.year = year;
if (height < 0.0) {
this.height = 0.0;
}
else this.height = height;

if (weight < 0.0) {
this.weight = 0.0;
}
else this.weight = weight;
}

int getSalaryByDayRate(int dayRate) {
return dayRate < 0 ? 0 : dayRate * 21;
}

double getSalaryByDayRate(double dayRate) {
return dayRate < 0.0 ? 0.0 : dayRate * 21.0;
}

void getSalaryByDayRate(int salary, int premium) {
int output = 0;
if (salary < 0 && premium > 0) {
output = premium;
}
else if (premium < 0) {
output = 0;
}
else {
output = salary * 21 + premium;
}
System.out.println(String.format("Сотрудник %s получил за месяц %d", this.surname, output));
}
}
//2. Задание
static void printData(Person man) {
if (man == null) return;
System.out.println(man.getSalaryByDayRate(500));
System.out.println(man.getSalaryByDayRate(625.5));
man.getSalaryByDayRate(400, 6000);
}