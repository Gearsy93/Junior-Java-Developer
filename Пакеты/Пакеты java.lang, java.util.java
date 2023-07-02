package com.intellekta.randommoney;
import java.lang.Math;
import java.util.Random;

public class RandomMoney {
double price;

public double getPrice() {
return price;
}
public void sale(int stock, double selltime) {
this.price = 5 * Math.sin(0.5 * selltime);
double financialResult = stock * this.price;
if (financialResult > 0) {
System.out.print(String.format("The company has earned %.2f USD", financialResult));
}
else if (financialResult == 0) {
System.out.print("The company did not earn anything and did not lose on the sale");
}
else {
System.out.print(String.format("The company has lost %.2f USD", financialResult));
}
}

public void testSale() {
Random random = new Random();
for (int i = 0; i < 3; i++) {
sale(random.nextInt(0, Integer.MAX_VALUE), random.nextDouble(0, 50));
}
}
}