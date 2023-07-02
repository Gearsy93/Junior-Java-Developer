//1. Исходный код интерфейса DeviceMode
interface DeviceMode {
void printHeatingMode();
}
//2. Исходный код абстрактного класса DeviceFunction
abstract class DeviceFunction implements DeviceMode {
void deviceOn() {
System.out.println("Device is ON");
}

abstract void deviceOff();
public void printHeatingMode() {
System.out.println("The device is in heating mode");
}
}
//3. Исходный код класса AutomaticMicrowave
class AutomaticMicrowave extends DeviceFunction {
public void deviceOff() {
System.out.println("Done");
}
}
//4. Исходный код класса ManualMicrowave
class ManualMicrowave extends DeviceFunction {
public void deviceOff() {
System.out.println("Waiting for new task");
}
}