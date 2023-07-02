//1. Исходный код класса ProductInfo
abstract class ProductInfo {
String name;
double price;
}
//2. Исходный код класса Transaction
class Transaction {
private double price;

public double getPrice() {
return price;
}
private double tax=1.2;
double dealPrice() {
return this.price * this.tax;
}
private void printCheck(double price) {
System.out.println(String.format("price: %.2f USD", price));
}

public void printCheck() {
System.out.print("Order ");
printCheck(this.price);
System.out.println(String.format("Total price: %.2f USD", dealPrice()));
}

class TransactionItem extends ProductInfo {
Transaction getTransaction() {
return Transaction.this;
}
private double price;
private String name;
// CHECK
boolean isEmpty(String s) {
for (int i = 0; i < s.length(); ++i) {
if (!Character.isWhitespace(s.charAt(i))) {
return false;
}
}
return true;
}
TransactionItem(String name, double price) {
this.price = price < 0 ? 0 : price;
getTransaction().price += this.price;
this.name = (name == null || isEmpty(name)) ? "Default" : name;
}
void printInfo() {
System.out.print(String.format("Item: %s, ", this.name));
getTransaction().printCheck(this.price);
}
}
}
//3. Исходный код класса Receipt
class Receipt {
public static void printReceipt(ProductInfo[] productInfos) {
Transaction transaction = new Transaction();
productInfos[0] = transaction.new TransactionItem("Eggs", 2);
productInfos[1] = transaction.new TransactionItem("Sour cream", 1.5);
productInfos[2] = transaction.new TransactionItem("Apple", 3);
productInfos[3] = transaction.new TransactionItem("Cheese", 4);
productInfos[4] = transaction.new TransactionItem("ToothPaste", 2);

for (ProductInfo item : productInfos) {
((Transaction.TransactionItem)item).printInfo();
}
transaction.printCheck();
}

public static void printTransactionInfo(Transaction.TransactionItem transactionItem) {
transactionItem.getTransaction().printCheck();
}
}