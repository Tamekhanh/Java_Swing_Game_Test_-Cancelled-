/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author hoang
 */
public class Player extends Entity{
    int maxHp,maxMp,maxAgi,maxEva,maxStr,maxIntel,maxWis,maxDef;
    int currentHp,currentMp,currentAgi,currentEva,currentStr,currentIntel,currentWis,currentDef;
    int maxSauvetage, currentSauvetage=0; //EXP
    
    // if player lvl up
    public void LvlUp() {
        currentSauvetage = 0;
        maxSauvetage++;
        maxHp++;
        maxMp++;
        maxAgi++;
        maxEva++;
        maxStr++;
        maxIntel++;
        maxWis++;
        maxDef++;
        currentHp=maxHp;
        currentMp=maxMp;
        currentAgi=maxAgi;
        currentEva=maxEva;
        currentStr=maxStr;
        currentIntel=maxIntel;
        currentWis=maxWis;
        currentDef=maxDef;
    }
    
    //Set Status for player
    //Set current if have debuff
    public Player() {
        this.maxHp = 100;
        this.maxMp = 100;
        this.maxAgi = 1;
        this.maxEva = 1;
        this.maxStr = 1;
        this.maxIntel = 1;
        this.maxWis = 1;
        this.maxSauvetage = 1;
        this.currentHp = 100;
        this.currentMp = 100;
        this.currentAgi = 1;
        this.currentEva = 1;
        this.currentStr = 1;
        this.currentIntel = 1;
        this.currentWis = 1;
        this.currentSauvetage = 0;
        
    }
    
    public Player(int maxHp, int maxMp, int maxAgi, int maxEva, int maxStr, int maxIntel, int maxWis, int maxDef, int maxLvl, int Lvl, int hp, int mp, int agi, int eva, int str, int intel, int wis, int def, int maxSauvetage,int currentSauvetage) {
        super(hp, mp, agi, eva, str, intel, wis, def);
        this.currentSauvetage = Lvl;
        this.maxHp = maxHp;
        this.maxSauvetage = maxLvl;
        this.maxMp = maxMp;
        this.maxAgi = maxAgi;
        this.maxEva = maxEva;
        this.maxStr = maxStr;
        this.maxIntel = maxIntel;
        this.maxWis = maxWis;
        this.maxDef = maxDef;
    }

    public int getMaxSauvetage() {
        return maxSauvetage;
    }

    public void setMaxSauvetage(int maxSauvetage) {
        this.maxSauvetage = maxSauvetage;
    }

    public int getCurrentSauvetage() {
        return currentSauvetage;
    }

    public void setCurrentSauvetage(int currentSauvetage) {
        this.currentSauvetage = currentSauvetage;
    }
    
    
    
    public int getMaxDef() {
        return maxDef;
    }

    public void setMaxDef(int maxDef) {
        this.maxDef = maxDef;
    }

    public int getCurrentDef() {
        return currentDef;
    }

    public void setCurrentDef(int currentDef) {
        this.currentDef = currentDef;
    }
    
    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public int getMaxAgi() {
        return maxAgi;
    }

    public void setMaxAgi(int maxAgi) {
        this.maxAgi = maxAgi;
    }

    public int getMaxEva() {
        return maxEva;
    }

    public void setMaxEva(int maxEva) {
        this.maxEva = maxEva;
    }

    public int getMaxStr() {
        return maxStr;
    }

    public void setMaxStr(int maxStr) {
        this.maxStr = maxStr;
    }

    public int getMaxIntel() {
        return maxIntel;
    }

    public void setMaxIntel(int maxIntel) {
        this.maxIntel = maxIntel;
    }

    public int getMaxWis() {
        return maxWis;
    }

    public void setMaxWis(int maxWis) {
        this.maxWis = maxWis;
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
