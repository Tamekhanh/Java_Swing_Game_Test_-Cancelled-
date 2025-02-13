/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author hoang
 */
public class UseableItem {
    //Limited item
    int potionStr=1,potionMana=1,potionDef=1,potionHp=1;
    //Weapon or armor can use
    int UseWeapon=1;
    int UseArmor=1;
    
    //Weapons
    public int Stick () {
        if (UseWeapon==1) {
            UseWeapon--;
            return 1;
        }
        else return 0;
    }
    public int WoodenSword (String A) {
        if (UseWeapon==1) {
            UseWeapon--;
            return 1;
        }
        else return 0;
    }
    public int IronSword (String A) {
        if (UseWeapon==1) {
            UseWeapon--;
            return 1;
        }
        else return 0;
    }
    public int WoodenBow (String A) {
        if (UseWeapon==1) {
            UseWeapon--;
            return 1;
        }
        else return 0;
    }
    public int Knift(String A) {
        if (UseWeapon==1) {
            UseWeapon--;
            return 1;
        }
        else return 0;
    }
    //Armor
    public int Normal (String A){
        if (UseArmor==1) {
            UseArmor--;
            return 1;
        }
        else return 0;
    }
    public int Bunny(String A) {
        if (UseArmor==1) {
            UseArmor--;
            return 1;
        }
        else return 0;
    }
    public int IronPlate(String A) {
        if (UseArmor==1) {
            UseArmor--;
            return 1;
        }
        else return 0;
    }
    public int GiftHoodie(String A) {
        if (UseArmor==1) {
            UseArmor--;
            return 1;
        }
        else return 0;
    }

    public int getUseWeapon() {
        return UseWeapon;
    }

    public void setUseWeapon(int UseWeapon) {
        this.UseWeapon = UseWeapon;
    }

    public int getUseArmor() {
        return UseArmor;
    }

    public void setUseArmor(int UseArmor) {
        this.UseArmor = UseArmor;
    }

    public UseableItem() {
    }
    
    public int getPotionStr() {
        return potionStr;
    }

    public void setPotionStr(int potionStr) {
        this.potionStr = potionStr;
    }

    public int getPotionMana() {
        return potionMana;
    }

    public void setPotionMana(int potionMana) {
        this.potionMana = potionMana;
    }

    public int getPotionDef() {
        return potionDef;
    }

    public void setPotionDef(int potionDef) {
        this.potionDef = potionDef;
    }

    public int getPotionHp() {
        return potionHp;
    }

    public void setPotionHp(int potionHp) {
        this.potionHp = potionHp;
    }
    
    

}
