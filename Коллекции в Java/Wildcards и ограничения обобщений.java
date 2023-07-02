package com.intellekta.generics.middleearth;

import java.util.Random;
import java.util.ArrayList;

public class Battle {
    final static int mordorArmySizeLowerBound = 6;
    // Change
    final static int mordorArmySizeHigherBound = 30;

    public static void fight() {

        Random random = new Random();
        int mordorArmySize = random.nextInt(mordorArmySizeLowerBound, mordorArmySizeHigherBound);
        // Верхняя граница не включается в random, поэтому везде округление вверх
        int middleEarthArmySize = random.nextInt((int)Math.ceil(mordorArmySize - mordorArmySize * 0.2), (int)Math.ceil(mordorArmySize + mordorArmySize * 0.2));
        Army<MordorUnit> mordorUnitArmy = formMordorArmy(mordorArmySize);
        Army<MiddleEarthUnit> middleEarthUnitArmy = formMiddleEarthArmy(middleEarthArmySize);
        System.out.println("Army of MordorUnit consists of:");
        printFormation(mordorUnitArmy);
        System.out.println("Army of MiddleEarthUnit consists of:");
        printFormation(middleEarthUnitArmy);
        fight(mordorUnitArmy, middleEarthUnitArmy);

    }
    public static void fight(Army<MordorUnit> mordorUnitArmy, Army<MiddleEarthUnit> middleEarthUnitArmy) {
        if (!StartPhase(1, mordorUnitArmy, middleEarthUnitArmy, mordorUnitArmy.getCavalry(), middleEarthUnitArmy.getCavalry())) {
            if (!StartPhase(2, mordorUnitArmy, middleEarthUnitArmy, mordorUnitArmy.getInfantry(), middleEarthUnitArmy.getInfantry())) {
                StartPhase(3, mordorUnitArmy, middleEarthUnitArmy, mordorUnitArmy.getArmy(), middleEarthUnitArmy.getArmy());
            }
        }
    }
    public static Army<MordorUnit> formMordorArmy(int size) {
        // ПРОЧЕКАТЬ, ЧТО ВСЕ ЮНИТЫ ГЕНЕРЯТСЯ
        Army<MordorUnit> mordorUnitArmy = new Army<>();
        Random random = new Random();
        int unitSequenceNumber = -1;
        for (int i = 0; i < size; i++) {
            unitSequenceNumber = random.nextInt(5);
            switch (unitSequenceNumber) {
                // ORCWITHWARG
                case 0:
                    mordorUnitArmy.recruit(new OrcWithWarg("orcWithWangID" + i));
                    break;
                // ORCWITHOUTWANG
                case 1:
                    mordorUnitArmy.recruit(new OrcWithoutWarg("orcWithoutWangID" + i));
                    break;
                // URUKHAI
                case 2:
                    mordorUnitArmy.recruit(new UrukHai("urukhaiID" + i));
                    break;
                // TROLL
                case 3:
                    mordorUnitArmy.recruit(new Troll("trollID" + i));
                    break;
                // GOBLIN
                case 4:
                    mordorUnitArmy.recruit(new Goblin("goblinID" + i));
                    break;
            }
        }
        return mordorUnitArmy;
    }

    public static Army<MiddleEarthUnit> formMiddleEarthArmy(int size) {
        boolean hasWizard = false;
        Army<MiddleEarthUnit> middleEarthUnitArmy = new Army<>();
        Random random = new Random();
        int unitSequenceNumber = -1;
        for (int i = 0; i < size; i++) {
            unitSequenceNumber = random.nextInt(6);
            switch (unitSequenceNumber) {
                //HUMANWITHHORSE
                case 0:
                    middleEarthUnitArmy.recruit(new HumanWithHorse("humanWithHorseID" + i));
                    break;
                //HUMANWITHOUTHORSE
                case 1:
                    middleEarthUnitArmy.recruit(new HumanWithOutHorse("humanWithoutHorseID" + i));
                    break;
                //WIZARD
                case 2:
                    if (!hasWizard) {
                        middleEarthUnitArmy.recruit(new Wizard("wizardID" + i));
                        hasWizard = true;
                    }
                    else i--;
                    break;
                //ELF
                case 3:
                    middleEarthUnitArmy.recruit(new Elf("elfID" + i));
                    break;
                //ROHHIRIM
                case 4:
                    middleEarthUnitArmy.recruit(new Rohhirim("rohhirimID" + i));
                    break;
                //WOODENELF
                case 5:
                    middleEarthUnitArmy.recruit(new WoodenElf("woodenelfID" + i));
                    break;
            }
        }
        return middleEarthUnitArmy;
    }

    private static void printFormation(Army army) {
        if (army != null) {
            for (var i : army.getArmy()) {
                System.out.println(i.toString());
            }
        }
        System.out.println();
    }
    private static boolean StartPhase(int phase, Army<MordorUnit> mordorUnitArmy, Army<MiddleEarthUnit> middleEarthUnitArmy, ArrayList<? extends Unit> mordorPhaseUnits, ArrayList<? extends Unit> middleEarthPhaseUnits) {
        if (mordorPhaseUnits.size() == 0 && middleEarthPhaseUnits.size() == 0) {
            System.out.println(String.format("No Units for phase %d, skipping...", phase));
            return false;
        }
        System.out.println("\nPhase " + phase);
        if (mordorPhaseUnits.size() == 0) {
            System.out.println(String.format("\nArmy of MiddleEarthUnit has won the %d phase. The winners list", phase));
            for (var i : middleEarthPhaseUnits) {
                System.out.println(i.toString());
            }
        }
        else if (middleEarthPhaseUnits.size() == 0) {
            System.out.println(String.format("\nArmy of MordorUnit has won the %d phase. The winners list", phase));
            for (var i : mordorPhaseUnits) {
                System.out.println(i.toString());
            }
        }
        Random random = new Random();
        MordorUnit mordorDuelist = null;
        MiddleEarthUnit middleEarthDuelist = null;
        if (phase != 3) {
            MordorUnit MordorExample = null;
            MiddleEarthUnit MiddleEarthExmaple = null;
            var phaseTypeObject = (mordorPhaseUnits.get(0));
            // 1 Фаза
            if (phaseTypeObject instanceof Cavalry) {
                MordorExample = new OrcWithWarg("OrkExample");
                MiddleEarthExmaple = new HumanWithHorse("HumanExample");

            }
            // 2 Фаза
            else if (phaseTypeObject instanceof Infantry) {
                MordorExample = new OrcWithoutWarg("OrkExample");
                MiddleEarthExmaple = new HumanWithOutHorse("HumanExample");
            }
            while (mordorPhaseUnits.size() > 0 && middleEarthPhaseUnits.size() > 0) {
                mordorDuelist = mordorUnitArmy.getRandomUnit(MordorExample);
                middleEarthDuelist = middleEarthUnitArmy.getRandomUnit(MiddleEarthExmaple);

                // Mordor turn
                if (random.nextInt(2) == 0) {
                    ((StrikeAbleUnit)mordorDuelist).strike((StrikeAbleUnit)middleEarthDuelist);
                    if (!middleEarthDuelist.isAlive()) {
                        middleEarthUnitArmy.release(middleEarthDuelist);
                        System.out.println(mordorDuelist.toString() + " strikes " + middleEarthDuelist.toString() + " and kills him");
                    }
                    else {
                        System.out.println(mordorDuelist.toString() + " strikes " + middleEarthDuelist.toString() + " and does not kill him");
                        ((StrikeAbleUnit)middleEarthDuelist).strike((StrikeAbleUnit)mordorDuelist);
                        if (!mordorDuelist.isAlive()) {
                            mordorUnitArmy.release(mordorDuelist);
                            System.out.println(middleEarthDuelist.toString() + " strikes " + mordorDuelist.toString() + " and kills him");
                        }
                        else {
                            System.out.println(middleEarthDuelist.toString() + " strikes " + mordorDuelist.toString() + " and does not kill him");
                        }
                    }
                }
                //MiddleEarthUnit
                else {
                    ((StrikeAbleUnit)middleEarthDuelist).strike((StrikeAbleUnit)mordorDuelist);
                    if (!mordorDuelist.isAlive()) {
                        mordorUnitArmy.release(mordorDuelist);
                        System.out.println(middleEarthDuelist.toString() + " strikes " + mordorDuelist.toString() + " and kills him");
                    }
                    else {
                        System.out.println(middleEarthDuelist.toString() + " strikes " + mordorDuelist.toString() + " and does not kill him");
                        ((StrikeAbleUnit)mordorDuelist).strike((StrikeAbleUnit)middleEarthDuelist);
                        if (!middleEarthDuelist.isAlive()) {
                            middleEarthUnitArmy.release(middleEarthDuelist);
                            System.out.println(mordorDuelist.toString() + " strikes " + middleEarthDuelist.toString() + " and kills him");
                        }
                        else {
                            System.out.println(mordorDuelist.toString() + " strikes " + middleEarthDuelist.toString() + " and does not kill him");
                        }
                    }
                }

            }
            if (mordorPhaseUnits.size() == 0) {
                System.out.println(String.format("\nArmy of MiddleEarthUnit has won the %d phase. The winners list:", phase));
                for (var i : middleEarthPhaseUnits) {
                    System.out.println(i.toString());
                }
            }
            else {
                System.out.println(String.format("\nArmy of MordorUnit has won the %d phase. The winners list:", phase));
                for (var i : mordorPhaseUnits) {
                    System.out.println(i.toString());
                }

            }
        }
        // 3 Фаза
        else {
            while (mordorUnitArmy.getArmy().size() > 0 && middleEarthUnitArmy.getArmy().size() > 0) {
                mordorDuelist = mordorUnitArmy.getRandomUnit();
                middleEarthDuelist = middleEarthUnitArmy.getRandomUnit();

                // Mordor turn
                //if (random.nextInt(2) == 0) {
                if (mordorDuelist instanceof Cavalry && middleEarthDuelist instanceof Infantry) {
                    ((StrikeAbleUnit)mordorDuelist).strike((StrikeAbleUnit)middleEarthDuelist);
                    if (!middleEarthDuelist.isAlive()) {
                        middleEarthUnitArmy.release(middleEarthDuelist);
                        System.out.println(mordorDuelist.toString() + " strikes " + middleEarthDuelist.toString() + " and kills him");
                    }
                    else {
                        System.out.println(mordorDuelist.toString() + " strikes " + middleEarthDuelist.toString() + " and does not kill him");
                        ((StrikeAbleUnit)middleEarthDuelist).strike((StrikeAbleUnit)mordorDuelist);
                        if (!mordorDuelist.isAlive()) {
                            mordorUnitArmy.release(mordorDuelist);
                            System.out.println(middleEarthDuelist.toString() + " strikes " + mordorDuelist.toString() + " and kills him");
                        }
                        else {
                            System.out.println(middleEarthDuelist.toString() + " strikes " + mordorDuelist.toString() + " and does not kill him");
                        }
                    }
                }
                else if (middleEarthDuelist instanceof Cavalry && mordorDuelist instanceof Infantry) {
                    ((StrikeAbleUnit)middleEarthDuelist).strike((StrikeAbleUnit)mordorDuelist);
                    if (!mordorDuelist.isAlive()) {
                        mordorUnitArmy.release(mordorDuelist);
                        System.out.println(middleEarthDuelist.toString() + " strikes " + mordorDuelist.toString() + " and kills him");
                    }
                    else {
                        System.out.println(middleEarthDuelist.toString() + " strikes " + mordorDuelist.toString() + " and does not kill him");
                        ((StrikeAbleUnit)mordorDuelist).strike((StrikeAbleUnit)middleEarthDuelist);
                        if (!middleEarthDuelist.isAlive()) {
                            middleEarthUnitArmy.release(middleEarthDuelist);
                            System.out.println(mordorDuelist.toString() + " strikes " + middleEarthDuelist.toString() + " and kills him");
                        }
                        else {
                            System.out.println(mordorDuelist.toString() + " strikes " + middleEarthDuelist.toString() + " and does not kill him");
                        }
                    }
                }
                else {
                    if (random.nextInt(2) == 0) {
                        ((StrikeAbleUnit)mordorDuelist).strike((StrikeAbleUnit)middleEarthDuelist);
                        if (!middleEarthDuelist.isAlive()) {
                            middleEarthUnitArmy.release(middleEarthDuelist);
                            System.out.println(mordorDuelist.toString() + " strikes " + middleEarthDuelist.toString() + " and kills him");
                        }
                        else {
                            System.out.println(mordorDuelist.toString() + " strikes " + middleEarthDuelist.toString() + " and does not kill him");
                            ((StrikeAbleUnit)middleEarthDuelist).strike((StrikeAbleUnit)mordorDuelist);
                            if (!mordorDuelist.isAlive()) {
                                mordorUnitArmy.release(mordorDuelist);
                                System.out.println(middleEarthDuelist.toString() + " strikes " + mordorDuelist.toString() + " and kills him");
                            }
                            else {
                                System.out.println(middleEarthDuelist.toString() + " strikes " + mordorDuelist.toString() + " and does not kill him");
                            }
                        }
                    }
                    //MiddleEarthUnit
                    else {
                        ((StrikeAbleUnit)middleEarthDuelist).strike((StrikeAbleUnit)mordorDuelist);
                        if (!mordorDuelist.isAlive()) {
                            mordorUnitArmy.release(mordorDuelist);
                            System.out.println(middleEarthDuelist.toString() + " strikes " + mordorDuelist.toString() + " and kills him");
                        }
                        else {
                            System.out.println(middleEarthDuelist.toString() + " strikes " + mordorDuelist.toString() + " and does not kill him");
                            ((StrikeAbleUnit)mordorDuelist).strike((StrikeAbleUnit)middleEarthDuelist);
                            if (!middleEarthDuelist.isAlive()) {
                                middleEarthUnitArmy.release(middleEarthDuelist);
                                System.out.println(mordorDuelist.toString() + " strikes " + middleEarthDuelist.toString() + " and kills him");
                            }
                            else {
                                System.out.println(mordorDuelist.toString() + " strikes " + middleEarthDuelist.toString() + " and does not kill him");
                            }
                        }
                    }
                }
            }
        }

        if (mordorUnitArmy.getArmy().size() == 0) {
            System.out.println("\nArmy of MiddleEarthUnit has won the battle. The winners list:");
            for (var i : middleEarthUnitArmy.getArmy()) {
                System.out.println(i.toString());
            }
            return true;
        }
        else if (middleEarthUnitArmy.getArmy().size() == 0){
            System.out.println("\nArmy of MordorUnit has won the battle. The winners list:");
            for (var i : mordorUnitArmy.getArmy()) {
                System.out.println(i.toString());
            }
            return true;
        }
        return false;
    }
}

class Army<T extends Unit> {
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
