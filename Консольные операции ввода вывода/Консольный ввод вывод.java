class CreditCalculator {
String lastName;
String firstName;
String secondName;
double sum;
int loanMaturity;
double interestRate;

CreditCalculator() {
Scanner scanner = new Scanner(System.in);
System.out.print("Lastname: ");
lastName = scanner.nextLine();
while(lastName == "") {
System.out.println("Lastname is incorrect. It must be not empty string");
lastName = scanner.nextLine();
}
System.out.print("Firstname: ");
firstName = scanner.nextLine();
while (firstName == "") {
System.out.println("Firstname is incorrect. It must be not empty string");
firstName = scanner.nextLine();
}
System.out.print("Secondname: ");
secondName = scanner.nextLine();

System.out.print("Interest rate: ");
do {
while (!scanner.hasNextDouble()) {
System.out.println("Interest rate is incorrect. It must be strictly positive number");
scanner.next();
}
interestRate = scanner.nextDouble();
if (interestRate <= 0.0)
{
System.out.println("Interest rate is incorrect. It must be strictly positive number");
scanner.nextLine();
}
} while (interestRate <= 0.0);
System.out.print("Credit amount: ");
do {
while (!scanner.hasNextDouble()) {
System.out.println("Credit amount is incorrect. It must be strictly positive number");
scanner.next();
}
sum = scanner.nextDouble();
if (sum <= 0.0) {
System.out.println("Credit amount is incorrect. It must be strictly positive number");
scanner.nextLine();
}
} while (sum <= 0.0);
System.out.print("Loan maturity: ");
do {
while (!scanner.hasNextInt()) {
System.out.println("Loan maturity is incorrect. It must be strictly positive int");
scanner.next();
}
loanMaturity = scanner.nextInt();
if (loanMaturity <= 0) {
System.out.println("Loan maturity is incorrect. It must be strictly positive int");
scanner.nextLine();
}
} while (loanMaturity <= 0);
scanner.close();
}

void printCreditAgreement() {
System.out.println(String.format("%.2f",
(this.sum * (this.interestRate / (12 * 100)))/(1 - Math.pow(1 + (this.interestRate / (12 * 100)), -this.loanMaturity)))
);
}
}