class Task {
enum Planes {
BOEING(8000, 10),
IL90(5400, 9),
TU153(14000, 15);
int distance;
int time;
Planes(int distance, int time) {
if (distance < 0) this.distance = 0;
else this.distance = distance;
if (time < 0) this.time = 0;
else this.time = time;
}
public int speed() {
return time == 0 ? 0 : this.distance / this.time;
}
}

int maxSpeed() {
int currentMax = 0;
for (Planes plane: Planes.values()) {
System.out.println(plane.speed());
if (plane.speed() > currentMax) currentMax = plane.speed();
}
return currentMax;
}
}