/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author hoang
 */
public class Entity {
    int hp,mp,agi,eva,str,intel,wis,def;

    public Entity() {
    }

    public Entity(int hp, int mp, int agi, int eva, int str, int intel, int wis, int def) {
        this.hp = hp;
        this.mp = mp;
        this.agi = agi;
        this.eva = eva;
        this.str = str;
        this.intel = intel;
        this.wis = wis;
        this.def=def;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
    
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getAgi() {
        return agi;
    }

    public void setAgi(int agi) {
        this.agi = agi;
    }

    public int getEva() {
        return eva;
    }

    public void setEva(int eva) {
        this.eva = eva;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getIntel() {
        return intel;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public int getWis() {
        return wis;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }
    
    @Override
    public String toString () {
        return "("+hp+","+mp+","+agi+","+eva+","+str+","+intel+","+wis+")";
    }
}
