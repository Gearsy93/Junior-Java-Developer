package com.intellekta.generics.middleearth;

import java.util.ArrayList;
import java.util.Random;

public class Army<T extends Unit> {
    private ArrayList<Cavalry> cavalries = new ArrayList<>();

    public ArrayList<Cavalry> getCavalry() {
        return cavalries;
    }

    private ArrayList<Infantry> infantries = new ArrayList<>();

    public ArrayList<Infantry> getInfantry() {
        return infantries;
    }
    public ArrayList<T> getArmy() {
        ArrayList<T> arrayListT = new ArrayList<>(cavalries.size() + infantries.size());
        for (Cavalry cavalry : this.cavalries) {
            arrayListT.add((T) cavalry);
        }
        for (Infantry infantry : this.infantries) {
            arrayListT.add((T)infantry);
        }
        return arrayListT;
    }

    public boolean recruit(T unit) {
        if (unit != null) {
            if (unit instanceof Cavalry) {
                this.cavalries.add((Cavalry) unit);
            }
            else {
                this.infantries.add((Infantry) unit);
            }
            return true;
        }
        return false;
    }

    public void print() {
        for (T unit : getArmy()) {
            System.out.print(unit.toString());
        }
    }

    public boolean release(T unit) {
        if (unit != null) {
            if (unit instanceof Cavalry) {
                return this.cavalries.remove(unit);
            }
            else {
                return this.infantries.remove(unit);
            }
        }
        return false;
    }
    public T getRandomUnit() {
        ArrayList<T> armyList = getArmy();
        if (armyList.size() == 0) return null;
        else {
            Random random = new Random();
            return armyList.get(random.nextInt(armyList.size()));
        }

    }
    public T getRandomUnit(T unit) {
        if (unit == null) return null;
        if (unit instanceof Cavalry && this.cavalries.size() > 0) {
            Random random = new Random();
            return (T)getCavalry().get(random.nextInt(this.cavalries.size()));
        }
        else if (this.infantries.size() > 0) {
            Random random = new Random();
            return (T)getInfantry().get(random.nextInt(this.infantries.size()));
        }
        else return null;
    }
}

interface Unit {
    String toString();
}

interface MordorUnit extends Unit {


}
interface MiddleEarthUnit extends Unit {

}

interface Cavalry extends Unit {

}

interface Infantry extends Unit {

}

class MordorCavalry implements MordorUnit, Cavalry {
    @Override
    public String toString() {
        return "A Mordor Cavalry Unit" + System.lineSeparator();
    }
}

class MordorInfantry implements MordorUnit, Infantry {
    @Override
    public String toString() {
        return "A Mordor Infantry Unit" + System.lineSeparator();
    }
}

class MiddleEarthCavalry implements MiddleEarthUnit, Cavalry {
    @Override
    public String toString() {
        return "A MiddleEarth Cavalry Unit" + System.lineSeparator();
    }
}

class MiddleEarthInfantry implements MiddleEarthUnit, Infantry {
    @Override
    public String toString() {
        return "A MiddleEarth Infantry Unit" + System.lineSeparator();
    }
}

