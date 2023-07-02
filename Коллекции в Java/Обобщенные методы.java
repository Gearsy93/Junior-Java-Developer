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
    boolean isAlive();

}

abstract class AbstractUnit implements Unit {
    protected int power;
    protected String name;
    // Порешать
    public boolean isAlive() {
        return this.power > 0;
    }
    protected boolean isEmpty(String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString() {
        return String.format(
                this.getClass().getSimpleName() + " %s has power %d",
                this.name, this.power);
    }
}

abstract class StrikeAbleUnit<T extends StrikeAbleUnit> extends AbstractUnit {
    public abstract void strike(T opponentUnit);
}
abstract class UnitWithoutAnimal<T extends StrikeAbleUnit> extends StrikeAbleUnit<T>  {
    @Override
    public  void strike(T opponentUnit) {
        if (opponentUnit instanceof HorseOwner) {
            if (((HorseOwner)opponentUnit).horse.power > 0) {
                if (((HorseOwner)opponentUnit).horse.power <= this.power) {
                    ((HorseOwner)opponentUnit).horse.power = 0;
                }
                else {
                    ((HorseOwner)opponentUnit).horse.power -= this.power;
                }
            }
            else {
                if (this.power >= opponentUnit.power) {
                    opponentUnit.power = 0;
                }
                else {
                    opponentUnit.power -= this.power;
                }
            }
        } else if (opponentUnit instanceof WargOwner) {
            if (((WargOwner)opponentUnit).warg.power > 0) {
                if (((WargOwner)opponentUnit).warg.power <= this.power) {
                    ((WargOwner)opponentUnit).warg.power = 0;
                }
                else {
                    ((WargOwner)opponentUnit).warg.power -= this.power;
                }
            }
            else {
                if (this.power >= opponentUnit.power) {
                    opponentUnit.power = 0;
                }
                else {
                    opponentUnit.power -= this.power;
                }
            }
        }
        else {
            if (this.power >= opponentUnit.power) {
                opponentUnit.power = 0;
            }
            else {
                opponentUnit.power -= this.power;
            }
        }
    }
}

abstract class HorseOwner<T extends StrikeAbleUnit> extends StrikeAbleUnit<T> {
    Horse horse = new Horse();
    class Horse extends AbstractUnit implements Unit {
        Horse() {
            Random random = new Random();
            this.power = random.nextInt(4,6);
            this.name = "HorseName";
        }
    }
    @Override
    public void strike(T opponentUnit) {
        int fullPower = this.power + this.horse.power;
        if (opponentUnit instanceof HorseOwner) {
            if (((HorseOwner)opponentUnit).horse.power > 0) {
                if (((HorseOwner)opponentUnit).horse.power <= fullPower) {
                    ((HorseOwner)opponentUnit).horse.power = 0;
                }
                else {
                    ((HorseOwner)opponentUnit).horse.power -= fullPower;
                }
            }
            else {
                if (fullPower >= opponentUnit.power) {
                    opponentUnit.power = 0;
                }
                else {
                    opponentUnit.power -= fullPower;
                }
            }
        } else if (opponentUnit instanceof WargOwner) {
            if (((WargOwner)opponentUnit).warg.power > 0) {
                if (((WargOwner)opponentUnit).warg.power <= fullPower) {
                    ((WargOwner)opponentUnit).warg.power = 0;
                }
                else {
                    ((WargOwner)opponentUnit).warg.power -= fullPower;
                }
            }
            else {
                if (fullPower >= opponentUnit.power) {
                    opponentUnit.power = 0;
                }
                else {
                    opponentUnit.power -= fullPower;
                }
            }
        }
        else {
            if (fullPower >= opponentUnit.power) {
                opponentUnit.power = 0;
            }
            else {
                opponentUnit.power -= fullPower;
            }
        }
    }
    @Override
    public String toString() {
        return String.format(
                this.getClass().getSimpleName() + " %s has power %d",
                this.name, this.power + this.horse.power);
    }
}

abstract class WargOwner<T extends StrikeAbleUnit> extends StrikeAbleUnit<T> {
    Warg warg = new Warg();
    class Warg extends AbstractUnit implements Unit {
        Warg() {
            Random random = new Random();
            this.power = random.nextInt(4,8);
            this.name = "WargName";
        }
    }
    @Override
    public void strike(T opponentUnit) {
        int fullPower = this.power + this.warg.power;
        if (opponentUnit instanceof HorseOwner) {
            if (((HorseOwner)opponentUnit).horse.power > 0) {
                if (((HorseOwner)opponentUnit).horse.power <= fullPower) {
                    ((HorseOwner)opponentUnit).horse.power = 0;
                }
                else {
                    ((HorseOwner)opponentUnit).horse.power -= fullPower;
                }
            }
            else {
                if (fullPower >= opponentUnit.power) {
                    opponentUnit.power = 0;
                }
                else {
                    opponentUnit.power -= fullPower;
                }
            }
        }
        else if (opponentUnit instanceof WargOwner) {
            if (((WargOwner)opponentUnit).warg.power > 0) {
                if (((WargOwner)opponentUnit).warg.power <= fullPower) {
                    ((WargOwner)opponentUnit).warg.power = 0;
                }
                else {
                    ((WargOwner)opponentUnit).warg.power -= fullPower;
                }
            }
            else {
                if (fullPower >= opponentUnit.power) {
                    opponentUnit.power = 0;
                }
                else {
                    opponentUnit.power -= fullPower;
                }
            }
        }
        else {
            if (fullPower >= opponentUnit.power) {
                opponentUnit.power = 0;
            }
            else {
                opponentUnit.power -= fullPower;
            }
        }
    }
    @Override
    public String toString() {
        return String.format(
                this.getClass().getSimpleName() + " %s has power %d",
                this.name, this.power + this.warg.power);
    }
}

interface MordorUnit extends Unit {

}
interface MiddleEarthUnit extends Unit {

}

interface Cavalry extends Unit {

}

interface Infantry extends Unit {

}

// Unit Classes

interface Human extends MiddleEarthUnit {
    final int lowBound = 7;
    final int highBound = 9;
}

class HumanWithHorse extends HorseOwner implements Human, Cavalry {
    HumanWithHorse(String name) {
        Random random = new Random();
        this.power = random.nextInt(lowBound,highBound);
        this.name = (name == null || isEmpty(name)) ? super.toString() : name;
    }
}
class HumanWithOutHorse extends UnitWithoutAnimal implements Human, Infantry {
    HumanWithOutHorse(String name) {
        Random random = new Random();
        this.power = random.nextInt(lowBound,highBound);
        this.name = (name == null || isEmpty(name)) ? super.toString() : name;
    }
}

interface Orc {

}

class OrcWithWarg extends WargOwner implements Orc, MordorUnit, Cavalry {
    OrcWithWarg(String name) {
        Random random = new Random();
        this.power = random.nextInt(8, 11);
        this.name = (name == null || isEmpty(name)) ? super.toString() : name;
    }
}
class OrcWithoutWarg extends UnitWithoutAnimal implements Orc, MordorUnit, Infantry {
    OrcWithoutWarg(String name) {
        Random random = new Random();
        this.power = random.nextInt(8, 11);
        this.name = (name == null || isEmpty(name)) ? super.toString() : name;
    }
}

class Wizard extends HorseOwner implements MiddleEarthUnit, Cavalry {
    Wizard(String name) {
        this.power = 20;
        Random random = new Random();
        this.name = (name == null || isEmpty(name)) ? super.toString() : name;
    }
}

class Rohhirim extends HumanWithHorse {
    Rohhirim(String name) {
        super(name);
    }
}

class Elf extends UnitWithoutAnimal implements MiddleEarthUnit, Infantry {
    Elf(String name) {
        Random random = new Random();
        this.power = random.nextInt(4, 8);
        this.name = (name == null || isEmpty(name)) ? super.toString() : name;
    }
}
class WoodenElf extends UnitWithoutAnimal implements MiddleEarthUnit, Infantry {
    WoodenElf(String name) {
        this.power = 6;
        this.name = (name == null || isEmpty(name)) ? super.toString() : name;
    }
}

class UrukHai extends UnitWithoutAnimal implements Orc, MordorUnit, Infantry {
    UrukHai(String name) {
        Random random = new Random();
        this.power = random.nextInt(10, 13);
        this.name = (name == null || isEmpty(name)) ? super.toString() : name;
    }
}
class Troll extends UnitWithoutAnimal implements MordorUnit, Infantry {
    Troll(String name) {
        Random random = new Random();
        this.power = random.nextInt(11, 16);
        this.name = (name == null || isEmpty(name)) ? super.toString() : name;
    }
}
class Goblin extends UnitWithoutAnimal implements MordorUnit, Infantry {
    Goblin(String name) {
        Random random = new Random();
        this.power = random.nextInt(2, 6);
        this.name = (name == null || isEmpty(name)) ? super.toString() : name;
    }
}