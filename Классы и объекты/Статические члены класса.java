class Detail {
String name;
int quantity;
int number;
static int total;
static String status;
final static int TOTAL_MIN;
final static int TOTAL_MAX;

static {
total = 0;
status = "Need to buy some details";
TOTAL_MIN = 100;
TOTAL_MAX = 1000;
}

Detail () {
this.name = "default";
this.quantity = 0;
this.number = 0;
}
Detail (String name, int quantity, int number) {
if (name == null) {
this.name = "default";
}
else this.name = name;
if (quantity < 0) {
this.quantity = 0;
}
else this.quantity = quantity;
if (number < 0) {
this.number = 0;
}
else this.number = number;
total += quantity;
if (total >= TOTAL_MIN && total <= TOTAL_MAX) {
status = "OK";
}
else if (total > TOTAL_MAX) {
status = "Not enough storage space";
}
}

public boolean sell(int sellCount) {
if (sellCount > quantity || sellCount > total || sellCount < 0 || sellCount == 0) {
return false;
}
quantity -= sellCount;
total -= sellCount;
if (total < TOTAL_MIN) {
status = "Need to buy some details";
}
else if (total >= TOTAL_MIN && total <= TOTAL_MAX) {
status = "OK";
}
else {
status = "Not enough storage space";
}
return true;
}

public static void printDetail(Detail detail) {
if (detail == null) return;
if (detail.quantity < 0 || detail.number < 0 || detail.name == null) {
System.out.println(String.format("%s %d: %d", "default", 0, 0));
}
else {
System.out.println(String.format("%s %d: %d", detail.name, detail.number, detail.quantity));
}
}
}