/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author hoang
 */
public class Dummy extends Entity {
    int currentHp,currentMp,currentAgi,currentEva,currentStr,currentIntel,currentWis,currentDef;
    String name;
    int lvl;
    
    //regen hp for monster
    public void Regenaration() {
        currentHp=hp;
    }
    
    public Dummy() {
        this.lvl=1;
        this.name="Dummy";
        this.currentHp=100;
        this.currentMp=100;
        this.currentAgi=1;
        this.currentEva=1;
        this.currentStr=1;
        this.currentIntel=1;
        this.currentWis=1;
        this.currentDef=1;
        super.hp=100;
        super.mp=100;
        super.agi=1;
        super.eva=1;
        super.str=1;
        super.intel=1;
        super.wis=1;
        super.def=1;
    }
    
    

    public Dummy(int currentHp, int currentMp, int currentAgi, int currentEva, int currentStr, int currentIntel, int currentWis, int currentDef, String name, int hp, int mp, int agi, int eva, int str, int intel, int wis, int def) {
        super(hp, mp, agi, eva, str, intel, wis, def);
        this.currentHp = currentHp;
        this.currentMp = currentMp;
        this.currentAgi = currentAgi;
        this.currentEva = currentEva;
        this.currentStr = currentStr;
        this.currentIntel = currentIntel;
        this.currentWis = currentWis;
        this.currentDef = currentDef;
        this.name = name;
    }

    public int getCurrentDef() {
        return currentDef;
    }

    public void setCurrentDef(int currentDef) {
        this.currentDef = currentDef;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    
    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getCurrentMp() {
        return currentMp;
    }

    public void setCurrentMp(int currentMp) {
        this.currentMp = currentMp;
    }

    public int getCurrentAgi() {
        return currentAgi;
    }

    public void setCurrentAgi(int currentAgi) {
        this.currentAgi = currentAgi;
    }

    public int getCurrentEva() {
        return currentEva;
    }

    public void setCurrentEva(int currentEva) {
        this.currentEva = currentEva;
    }

    public int getCurrentStr() {
        return currentStr;
    }

    public void setCurrentStr(int currentStr) {
        this.currentStr = currentStr;
    }

    public int getCurrentIntel() {
        return currentIntel;
    }

    public void setCurrentIntel(int currentIntel) {
        this.currentIntel = currentIntel;
    }

    public int getCurrentWis() {
        return currentWis;
    }

    public void setCurrentWis(int currentWis) {
        this.currentWis = currentWis;
    }



}
