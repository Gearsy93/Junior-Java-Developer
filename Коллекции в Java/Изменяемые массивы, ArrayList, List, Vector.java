public class Main
{
public static void main(String[] args) {
Shop shop = Shop.createShopInfo();
shop.printShopSummary();
}
}
abstract class Customer {
private int purchaseCount;
public int getPurchaseCount() {
return this.purchaseCount;
}
public void setPurchaseCount(int purchaseCount) {
if (purchaseCount < 0) return;
else {
this.purchaseCount = purchaseCount;
if (this.purchaseCount < 5) this.discountSize = 0.0;
else if (this.purchaseCount >= 5 && this.purchaseCount < 10) this.discountSize = 0.05;
else if (this.purchaseCount >= 10 && this.purchaseCount < 15) this.discountSize = 0.1;
else this.discountSize = 0.2;
}
}
private double discountSize;
public double getDiscountSize() {
return this.discountSize;
}
public void setDiscountSize(double discountSize) {
if (discountSize == 0.0 || discountSize == 0.05 || discountSize == 0.1 || discountSize == 0.2) {
this.discountSize = discountSize;
}
else this.discountSize = 0.0;
}
private String name;
public String getName() {
return name;
}
boolean isEmpty(String s) {
for (int i = 0; i < s.length(); ++i) {
if (!Character.isWhitespace(s.charAt(i))) {
return false;
}
}
return true;
}
Customer(int purchaseCount, String name) {
this.purchaseCount = purchaseCount < 0 ? 0 : purchaseCount;
this.name = (name == null || isEmpty(name)) ? "No-name" : name;
}
abstract void customerInfo();
}

class CashCustomer extends Customer {
private String documentNumber;
public String getDocumentNumber() {
return this.documentNumber;
}
public CashCustomer(String name, int purchaseCount, String documentNumber) {
super(purchaseCount, name);
this.documentNumber = (documentNumber != null && documentNumber.matches("\\d{4} \\d{6}")) ? documentNumber : "0000 000000";

}

@Override
public void customerInfo() {
System.out.print(String.format("Customer %s (passport: %s) has a discount %d ", this.getName(), this.getDocumentNumber(), (int)(this.getDiscountSize() * 100)) + "%" + System.lineSeparator());
}
}

class CardCustomer extends Customer {
private String cardNumber;
public String getCardNumber() {
return cardNumber;
}
public CardCustomer(String name, int purchaseCount, String cardNumber) {
super(purchaseCount, name);
this.cardNumber = (cardNumber != null && cardNumber.matches("\\d{16}")) ? cardNumber : "0000000000000000";
}

@Override
public void customerInfo() {
System.out.println(String.format("Customer %s (card: %s) has a discount %d ", this.getName(), this.getCardNumber(), (int)(this.getDiscountSize() * 100)) + "%" + System.lineSeparator());
}
}

class Shop {
private ArrayList<Customer> shopCustomers = new ArrayList<Customer>();

public void printShopSummary() {
for (Customer customer : this.shopCustomers) {
customer.customerInfo();
}
}
public static Shop createShopInfo() {
Shop shop = new Shop();
System.out.println("Shop data: ");
Scanner scanner = new Scanner(System.in);
String input = scanner.nextLine();
String[] splitInput;
while (!input.equals("exit"))
{
splitInput = input.split(",");
if (splitInput.length == 1) {
if (splitInput[0].equals("1")) {
shop.shopCustomers.add(new CardCustomer("", 0, ""));
}
else if (splitInput[0].equals("2")) {
shop.shopCustomers.add(new CashCustomer("", 0, ""));
}
}
else if (splitInput.length == 2) {
if (splitInput[0].equals("1")) {
shop.shopCustomers.add(new CardCustomer(splitInput[1], 0, ""));
}
else if (splitInput[0].equals("2")) {
shop.shopCustomers.add(new CashCustomer(splitInput[1], 0, ""));
}
}
else if (splitInput.length == 3) {
if (splitInput[0].equals("1")) {
shop.shopCustomers.add(new CardCustomer(splitInput[1], splitInput[2] == "" ? 0 : Integer.parseInt(splitInput[2]), ""));
}
else if (splitInput[0].equals("2")) {
shop.shopCustomers.add(new CashCustomer(splitInput[1], splitInput[2] == "" ? 0 : Integer.parseInt(splitInput[2]), ""));
}
}
else if (splitInput.length == 4) {
if (splitInput[0].equals("1")) {
shop.shopCustomers.add(new CardCustomer(splitInput[1], splitInput[2] == "" ? 0 : Integer.parseInt(splitInput[2]), ""));
}
else if (splitInput[0].equals("2")) {
shop.shopCustomers.add(new CashCustomer(splitInput[1], splitInput[2] == "" ? 0 : Integer.parseInt(splitInput[2]), splitInput[3]));
}
}
else if (splitInput.length >= 5) {
if (splitInput[0].equals("1")) {
shop.shopCustomers.add(new CardCustomer(splitInput[1], splitInput[2] == "" ? 0 : Integer.parseInt(splitInput[2]), splitInput[4]));
}
else if (splitInput[0].equals("2")) {
shop.shopCustomers.add(new CashCustomer(splitInput[1], splitInput[2] == "" ? 0 : Integer.parseInt(splitInput[2]), splitInput[3]));
}
}
input = scanner.nextLine();
}
return shop;
}

}