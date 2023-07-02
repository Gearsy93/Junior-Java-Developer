//2.1. Исходный код класса Vehicle
class Vehicle {
private int power;

public int getPower() {
return power;
}
public Vehicle(int power) {
if (power < 1 || power > 3000) this.power = 1;
else this.power = power;
}

void printSpecification() {
System.out.println("Vehicle power: " + Integer.toString(power));
}
}
//2.2. Исходный код класса Car
class Car extends Vehicle {
private String model;

public String getModel() {
return model;
}

Car(int power, String model) {
super(power);
if (model == null) this.model = "default";
else this.model = model;
}

@Override
void printSpecification() {
super.printSpecification();
System.out.println("Car model: " + model);
}
}
//2.3. Исходный код класса PassengerCar
class PassengerCar extends Car {
private int seats;
public int getSeats() {
return seats;
}
PassengerCar(int seats, int power, String model) {
super(power, model);
if (seats < 2 || seats > 8) this.seats = 5;
else this.seats = seats;
}

@Override
void printSpecification() {
super.printSpecification();
System.out.println("Seats count: " + Integer.toString(seats));
}
}
//2.4. Исходный код класса Lorry
class Lorry extends Car {
private int capacity;

public int getCapacity () {
return capacity;
}

Lorry(int capacity, int power, String model) {
super(power, model);
if (capacity < 500 || capacity > 10000) this.capacity = 2500;
else this.capacity = capacity;
}
@Override
void printSpecification() {
super.printSpecification();
System.out.println("Capacity: " + Integer.toString(capacity));
}
}
//2.5. Исходный код класса Motorcycle
final class Motorcycle extends Vehicle {
private int speed;

public int getSpeed() {
return speed;
}

Motorcycle(int speed, int power) {
super(power);
if (speed < 0 || speed > 300) this.speed = 200;
else this.speed = speed;
}

@Override
void printSpecification() {
super.printSpecification();
System.out.println("Moto speed: " + Integer.toString(speed));
}
}
//2.6. Исходный код класса Task
class Task {
public static void task() {
PassengerCar passsengerCar = new PassengerCar(5, 300, "Model X");
Lorry lorry = new Lorry(6500, 620, "Semi");
Motorcycle motorcycle = new Motorcycle(170, 210);
passsengerCar.printSpecification();
lorry.printSpecification();
motorcycle.printSpecification();
}
}