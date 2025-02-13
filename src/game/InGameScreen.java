/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package game;

import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.ImageIcon;

/**
 *
 * @author hoang
 */
public class InGameScreen extends javax.swing.JFrame{
    Dummy dummy=new Dummy();
    Player pl = new Player();
    int Helpme=0;
    int TurnEva=2;
    int FleeFromMe=0;
    int FindMe=0;
    double XSignal,YSignal;
    int turn=2;
    boolean canEvade=false;
    UseableItem UI=new UseableItem() ;
    
    // Setup picture
    ImageIcon ImageDummy=new ImageIcon("Dummy.png");
    ImageIcon AStick=new ImageIcon("Stick.png");
    ImageIcon UsedStick=new ImageIcon("UsedStick.png");
    ImageIcon WoodenSwordImage=new ImageIcon("WoodenSword.png");
    ImageIcon UsedWoodenSword=new ImageIcon("UsedWoodenSword.png");
    ImageIcon IronSwordImage=new ImageIcon("IronSword.png");
    ImageIcon UsedIronSword=new ImageIcon("UsedIronSword.png");
    Random ExtraStr =new Random();
    String WeaponName="";
    /**
     * Creates new form InGameScreen
     */
    public InGameScreen() {
        initComponents();
        showStat();
        setProgressBar();
        BattleScreen.setVisible(false);
        ItemUse.setVisible(false);
        MonsterName();
        setImage();
        setTextBattle();
        InfoHp();
        Floor1.setFocusable(true);
    }
    
    //show PL stat
    public void showStat(){
        lblStr.setText((pl.getCurrentStr()+AddWeapon())+"");
        lblAgi.setText(pl.getCurrentAgi()+"");
        lblInt.setText(pl.getCurrentIntel()+"");
        lblEva.setText(pl.getCurrentEva()+"");
        lblWis.setText(pl.getCurrentWis()+"");
    }
    
    //set Hp and Mp bar
    public void setProgressBar(){
        pbHp.setMaximum(pl.getMaxHp());
        pbMana.setMaximum(pl.getMaxMp());
    }
    
    public void Time (ActionListener AL) {
        Respond.setText(".");
    }
    
    //set Monster name
    public void MonsterName() {
        NameMonster1.setText(dummy.getName()+" LVL:"+dummy.getLvl());
    }
    
    //calculator know who will go first
    public int TurnPL() {
        int plturn;
        if (pl.getCurrentAgi()>dummy.getCurrentAgi()) 
            return 2;
        else 
            return 3;
    }
    
    //Battle
    public void Battle () {
        
        Interact.setVisible(false);
        
        if(pl.currentHp>0&&dummy.currentHp>0) {
            if (turn%TurnPL()==0) {
                Interact.setVisible(true);
            } else {
                Interact.setVisible(false);
                if (canEvade==true) {
                } else {
                    pl.currentHp=pl.currentHp-MonsterAttack();
                    TextBattle1.setText("Dummy Attack!");
                    TextBattle2.setText("Player -"+MonsterAttack()+"HP");
                    Interact.setVisible(true);
                }
            }
        } else if (dummy.currentHp<=0) {
            TextBattle1.setText("Exp +"+(dummy.lvl));
            //Exp receive from monster will plus in player exp
            pl.setCurrentSauvetage(pl.currentSauvetage+(dummy.lvl));
            if (pl.getCurrentSauvetage()>=pl.getMaxSauvetage()) {
                do {
                    pl.LvlUp();
                    TextBattle2.setText("LVL UP");
                    pl.currentSauvetage=pl.currentSauvetage-pl.maxSauvetage;
                }
                while (pl.getCurrentSauvetage()>=pl.getMaxSauvetage());
            }
            BattleScreen.setVisible(false);
            Floor1.setVisible(true);
            Interaction.setVisible(true);
            Floor1.setFocusable(true);
            ResetMonster();
        } else {
            TextBattle1.setText("You Died");
        }
    }
    
    //reset monster
    public void ResetMonster() {
        dummy.Regenaration();
            TurnEva=2;
            InfoHp();
    }
    
    //Monster Evade
    //Monster will Evade if Hp<50%
    public void DummyEva() {
        if (dummy.currentHp<=dummy.getHp()*0.5 && TurnEva!=0) {
            TextBattle1.setText(dummy.getName()+" Turn on Perfect Eva");
            TurnEva--;
            canEvade= true;
        } else canEvade= false;
    }
    
    //Monter Attack
    public int MonsterAttack() {
        int DefPL=pl.getCurrentDef()*pl.currentSauvetage;
        if (DefPL<=1) {
            DefPL=1;
        }
        int damage=(dummy.getCurrentStr()*(2+dummy.getLvl()))-DefPL;
        if (damage<=1) {
            damage=1;
        }
        turn++;
        return damage;
        
    }
    
    //Set image for monster
    public void setImage () {
        ImageMonster1.setIcon(ImageDummy);
        StickWeapon.setIcon(AStick);
        WoodenSword.setIcon(WoodenSwordImage);
        IronSword.setIcon(IronSwordImage);
    }
    
    //First in battle text
    public void setTextBattle() {
        TextBattle1.setText(dummy.getName()+" Attack!");
        TextBattle2.setText("THE STRONGEST ENEMY APPEAR!");
    }
    
    //Update Monter Hp
    public void InfoHp() {
        InfoHp.setText(dummy.currentHp+"/"+dummy.getHp());
    }
    
    //Monter Attack
    public int AttackPL () {
        int DefM=dummy.getCurrentDef()*dummy.lvl;
        if (DefM<=2) DefM=2;
        int damage=(pl.getCurrentStr()*(2+pl.getCurrentSauvetage()))-DefM+ExtraStr.nextInt(5);
        if (damage<=1) {
            damage=1;
        }
        return damage;
    }
    
    //Reset Button
    public void ReNew() {
        AtkButton.setText("ATK");
        ItemButton.setText("ITEM");
        SkillButton.setText("SKILL");
        OtherButton.setText("OTHER");
        InfoButton.setText("INFO");
    }
    
    //Add weapon to character
    public int AddWeapon() {
        if (WeaponName.equalsIgnoreCase("")) {
          return 0;  
        }
        else {
            switch (WeaponName) {
            case "Stick": return 2;
            case "WoodenSword": return 5;
            case "IronSword": return 10;
            }
        }
        return 0;
    }
    
    public void Terran () {
        int y=Floor1.getHeight();
        int x=Floor1.getWidth();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ItemUse = new javax.swing.JPanel();
        StickWeapon = new javax.swing.JLabel();
        WeaponInfo = new javax.swing.JPanel();
        ExtraPlus = new javax.swing.JLabel();
        InfoText = new javax.swing.JLabel();
        WoodenSword = new javax.swing.JLabel();
        IronSword = new javax.swing.JLabel();
        WoodenBow = new javax.swing.JLabel();
        Floor1 = new javax.swing.JPanel();
        Signal2 = new javax.swing.JPanel();
        Stat = new javax.swing.JPanel();
        pbMana = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        pbHp = new javax.swing.JProgressBar();
        lbl1 = new javax.swing.JLabel();
        lblStr = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lblAgi = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lblInt = new javax.swing.JLabel();
        lblEva = new javax.swing.JLabel();
        lblWis = new javax.swing.JLabel();
        Interaction = new javax.swing.JPanel();
        Respond = new javax.swing.JLabel();
        Help = new javax.swing.JToggleButton();
        Flee = new javax.swing.JToggleButton();
        Find = new javax.swing.JToggleButton();
        Respond2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Inventory = new javax.swing.JToggleButton();
        BattleScreen = new javax.swing.JPanel();
        Text = new javax.swing.JPanel();
        TextBattle1 = new javax.swing.JLabel();
        TextBattle2 = new javax.swing.JLabel();
        V = new javax.swing.JLabel();
        TextBattle3 = new javax.swing.JLabel();
        NameMonster1 = new javax.swing.JLabel();
        Interact = new javax.swing.JPanel();
        AtkButton = new javax.swing.JButton();
        SkillButton = new javax.swing.JButton();
        ItemButton = new javax.swing.JButton();
        OtherButton = new javax.swing.JButton();
        InfoButton = new javax.swing.JButton();
        ImageMonster1 = new javax.swing.JLabel();
        InfoHp = new javax.swing.JLabel();
        Exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ItemUse.setBackground(new java.awt.Color(0, 0, 0));
        ItemUse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10));
        ItemUse.setFocusable(false);

        StickWeapon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        StickWeapon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StickWeaponMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StickWeaponMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                StickWeaponMouseExited(evt);
            }
        });

        WeaponInfo.setBackground(new java.awt.Color(0, 0, 0));
        WeaponInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        WeaponInfo.setAlignmentX(0.0F);
        WeaponInfo.setAlignmentY(0.0F);
        WeaponInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        ExtraPlus.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ExtraPlus.setForeground(new java.awt.Color(255, 255, 255));

        InfoText.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        InfoText.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout WeaponInfoLayout = new javax.swing.GroupLayout(WeaponInfo);
        WeaponInfo.setLayout(WeaponInfoLayout);
        WeaponInfoLayout.setHorizontalGroup(
            WeaponInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WeaponInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ExtraPlus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(WeaponInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(WeaponInfoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(InfoText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        WeaponInfoLayout.setVerticalGroup(
            WeaponInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WeaponInfoLayout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(ExtraPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(WeaponInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(WeaponInfoLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(InfoText, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE)))
        );

        WoodenSword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        WoodenSword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WoodenSwordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                WoodenSwordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                WoodenSwordMouseExited(evt);
            }
        });

        IronSword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        IronSword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IronSwordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IronSwordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IronSwordMouseExited(evt);
            }
        });

        WoodenBow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        WoodenBow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WoodenBowMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                WoodenBowMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                WoodenBowMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ItemUseLayout = new javax.swing.GroupLayout(ItemUse);
        ItemUse.setLayout(ItemUseLayout);
        ItemUseLayout.setHorizontalGroup(
            ItemUseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ItemUseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StickWeapon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(WoodenSword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IronSword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(WoodenBow, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
            .addComponent(WeaponInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ItemUseLayout.setVerticalGroup(
            ItemUseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ItemUseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ItemUseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StickWeapon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WoodenSword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IronSword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WoodenBow, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
                .addComponent(WeaponInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(ItemUse, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 710, 490));

        Floor1.setBackground(new java.awt.Color(0, 0, 0));
        Floor1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Floor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Floor1KeyPressed(evt);
            }
        });

        Signal2.setBackground(new java.awt.Color(255, 0, 51));
        Signal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SignalKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout Signal2Layout = new javax.swing.GroupLayout(Signal2);
        Signal2.setLayout(Signal2Layout);
        Signal2Layout.setHorizontalGroup(
            Signal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        Signal2Layout.setVerticalGroup(
            Signal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Floor1Layout = new javax.swing.GroupLayout(Floor1);
        Floor1.setLayout(Floor1Layout);
        Floor1Layout.setHorizontalGroup(
            Floor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Floor1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Signal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(755, Short.MAX_VALUE))
        );
        Floor1Layout.setVerticalGroup(
            Floor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Floor1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Signal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(406, 596, Short.MAX_VALUE))
        );

        getContentPane().add(Floor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 784, -1));

        Stat.setFocusable(false);

        pbMana.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("PLAYER STAT");

        pbHp.setBackground(new java.awt.Color(255, 0, 51));
        pbHp.setForeground(new java.awt.Color(0, 0, 0));
        pbHp.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pbHpStateChanged(evt);
            }
        });

        lbl1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl1.setText("STR:");

        lblStr.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lbl2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl2.setText("WIS:");

        lblAgi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lbl4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl4.setText("EVA:");

        lbl5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl5.setText("INT:");

        lbl6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl6.setText("AGI:");

        lblInt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lblEva.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lblWis.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout StatLayout = new javax.swing.GroupLayout(Stat);
        Stat.setLayout(StatLayout);
        StatLayout.setHorizontalGroup(
            StatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StatLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(108, 108, 108))
                    .addGroup(StatLayout.createSequentialGroup()
                        .addGroup(StatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, StatLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(StatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl1)
                                    .addComponent(lbl6)
                                    .addComponent(lbl5)
                                    .addComponent(lbl4)
                                    .addComponent(lbl2))
                                .addGap(18, 18, 18)
                                .addGroup(StatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(StatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblStr, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                        .addComponent(lblAgi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblInt, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEva, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblWis, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(pbMana, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(pbHp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        StatLayout.setVerticalGroup(
            StatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(StatLayout.createSequentialGroup()
                        .addGroup(StatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(StatLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pbHp, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pbMana, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(lbl1))
                            .addComponent(lblStr, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(StatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAgi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(StatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl5)
                            .addComponent(lblInt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl4))
                    .addComponent(lblEva, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(StatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblWis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(Stat, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 205, -1));

        Interaction.setFocusable(false);

        Respond.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N

        Help.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Help.setText("Help");
        Help.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HelpMouseClicked(evt);
            }
        });
        Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpActionPerformed(evt);
            }
        });

        Flee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Flee.setText("Flee");
        Flee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FleeMouseClicked(evt);
            }
        });
        Flee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FleeActionPerformed(evt);
            }
        });

        Find.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Find.setText("Find");
        Find.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FindMouseClicked(evt);
            }
        });
        Find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindActionPerformed(evt);
            }
        });

        Respond2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Battle test");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Inventory.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Inventory.setText("Inventory");
        Inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventoryMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout InteractionLayout = new javax.swing.GroupLayout(Interaction);
        Interaction.setLayout(InteractionLayout);
        InteractionLayout.setHorizontalGroup(
            InteractionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InteractionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InteractionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Inventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(Help, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Respond, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Flee, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Find, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Respond2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        InteractionLayout.setVerticalGroup(
            InteractionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InteractionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Help)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Flee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Find)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Inventory, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Respond, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Respond2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        getContentPane().add(Interaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 297, -1, 321));

        BattleScreen.setBackground(new java.awt.Color(255, 255, 255));
        BattleScreen.setFocusable(false);

        Text.setBackground(new java.awt.Color(0, 0, 0));
        Text.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 10));
        Text.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextMouseClicked(evt);
            }
        });

        TextBattle1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TextBattle1.setForeground(new java.awt.Color(255, 255, 255));

        TextBattle2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TextBattle2.setForeground(new java.awt.Color(255, 255, 255));

        V.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        V.setForeground(new java.awt.Color(255, 255, 255));
        V.setText("V");

        TextBattle3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TextBattle3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout TextLayout = new javax.swing.GroupLayout(Text);
        Text.setLayout(TextLayout);
        TextLayout.setHorizontalGroup(
            TextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TextLayout.createSequentialGroup()
                .addComponent(TextBattle3, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(V))
            .addComponent(TextBattle1, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(TextBattle2, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        TextLayout.setVerticalGroup(
            TextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TextLayout.createSequentialGroup()
                .addComponent(TextBattle1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextBattle2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TextLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(V, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TextLayout.createSequentialGroup()
                        .addComponent(TextBattle3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 19, Short.MAX_VALUE))))
        );

        NameMonster1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NameMonster1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        Interact.setBackground(new java.awt.Color(0, 0, 0));
        Interact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 10));

        AtkButton.setBackground(new java.awt.Color(0, 0, 0));
        AtkButton.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        AtkButton.setForeground(new java.awt.Color(255, 255, 255));
        AtkButton.setText("ATK");
        AtkButton.setBorder(null);
        AtkButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AtkButtonMouseClicked(evt);
            }
        });

        SkillButton.setBackground(new java.awt.Color(0, 0, 0));
        SkillButton.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SkillButton.setForeground(new java.awt.Color(255, 255, 255));
        SkillButton.setText("SKILL");
        SkillButton.setBorder(null);
        SkillButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SkillButtonMouseClicked(evt);
            }
        });

        ItemButton.setBackground(new java.awt.Color(0, 0, 0));
        ItemButton.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ItemButton.setForeground(new java.awt.Color(255, 255, 255));
        ItemButton.setText("ITEM");
        ItemButton.setBorder(null);
        ItemButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ItemButtonMouseClicked(evt);
            }
        });

        OtherButton.setBackground(new java.awt.Color(0, 0, 0));
        OtherButton.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        OtherButton.setForeground(new java.awt.Color(255, 255, 255));
        OtherButton.setText("OTHER");
        OtherButton.setBorder(null);

        InfoButton.setBackground(new java.awt.Color(0, 0, 0));
        InfoButton.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        InfoButton.setForeground(new java.awt.Color(255, 255, 255));
        InfoButton.setText("INFO");
        InfoButton.setBorder(null);
        InfoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InfoButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout InteractLayout = new javax.swing.GroupLayout(Interact);
        Interact.setLayout(InteractLayout);
        InteractLayout.setHorizontalGroup(
            InteractLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InteractLayout.createSequentialGroup()
                .addGroup(InteractLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(InfoButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SkillButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                    .addComponent(AtkButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InteractLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OtherButton, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                    .addComponent(ItemButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        InteractLayout.setVerticalGroup(
            InteractLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InteractLayout.createSequentialGroup()
                .addGroup(InteractLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AtkButton)
                    .addComponent(ItemButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InteractLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SkillButton)
                    .addComponent(OtherButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InfoButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ImageMonster1.setForeground(new java.awt.Color(255, 255, 255));

        InfoHp.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        InfoHp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        Exit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Exit.setText("Exit");
        Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BattleScreenLayout = new javax.swing.GroupLayout(BattleScreen);
        BattleScreen.setLayout(BattleScreenLayout);
        BattleScreenLayout.setHorizontalGroup(
            BattleScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BattleScreenLayout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addGroup(BattleScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NameMonster1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(ImageMonster1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BattleScreenLayout.createSequentialGroup()
                .addGroup(BattleScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BattleScreenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Interact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BattleScreenLayout.createSequentialGroup()
                        .addGap(346, 346, 346)
                        .addComponent(InfoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(BattleScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BattleScreenLayout.setVerticalGroup(
            BattleScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BattleScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BattleScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BattleScreenLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(NameMonster1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ImageMonster1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(InfoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Interact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(BattleScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1001, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Control PL (BAN)
    /*public void ControlSignal (KeyEvent e) {
        int XNow,YNow;
        XNow=Signal2.getLocation().x;
        YNow=Signal2.getLocation().y;
        if (e.getKeyCode()==KeyEvent.VK_W) {
            Signal2.setLocation(XNow,YNow+5);
        } else if (e.getKeyCode()==KeyEvent.VK_S) {
            Signal2.setLocation(XNow, YNow-5);
        } else if (e.getKeyCode()==KeyEvent.VK_D) {
            Signal2.setLocation(XNow+5, YNow);
        } else if (e.getKeyCode()==KeyEvent.VK_A) {
            Signal2.setLocation(XNow-5, YNow);
        }
    }*/
    
    // Releash Control Key
    public void ReleashSignal (KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_S||e.getKeyCode()==KeyEvent.VK_W) {
            YSignal=0;
        } else if (e.getKeyCode()==KeyEvent.VK_A||e.getKeyCode()==KeyEvent.VK_D) {
            XSignal=0;
        }
    }
    
    //Test Change of Hp Bar (BAN)
    private void pbHpStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pbHpStateChanged
        int event=evt.hashCode();
        while (event<=0) {
            event--;
        }
        
    }//GEN-LAST:event_pbHpStateChanged

    private void HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HelpActionPerformed

    private void FleeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FleeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FleeActionPerformed

    private void FindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FindActionPerformed
    
    //Some weird button
    private void HelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HelpMouseClicked
        if (Helpme<3) {
        Respond.setText("You call for help");
        Respond2.setText("But...Nobody came");
        Helpme++;
    } else { 
        if (Helpme<6) {
            Respond.setText("You call for help");
            Respond2.setText("But...Nobody care");
            Helpme++;
        } else { 
            if (Helpme<10) {
                Respond.setText("You shout for help");
                Respond2.setText("But...Nobody hear");
                Helpme++;
            }
        }
    }
    
    }//GEN-LAST:event_HelpMouseClicked
    //Some weird button
    private void FleeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FleeMouseClicked
    if (FleeFromMe<3) {
        Respond.setText("You want flee away");
        Respond2.setText("You can't");
        FleeFromMe++;
    } else { 
        if (FleeFromMe<6) {
            Respond.setText("You want escape");
            Respond2.setText("This nightmare never over");
            FleeFromMe++;
        } else { 
            if (FleeFromMe<10) {
                Respond.setText("You can't escape");
                Respond2.setText("You can't end here");
                FleeFromMe++;
            }
        }
    }
    
    }//GEN-LAST:event_FleeMouseClicked
        //Some weird button
    private void FindMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FindMouseClicked
        if (FindMe<3) {
        Respond.setText("You look in dark");
        Respond2.setText("You find nothing");
        FindMe++;
    } else { 
        if (FindMe<6) {
            Respond.setText("You try to find something");
            Respond2.setText("You forgot something");
            FindMe++;
        } else { 
            if (FindMe<10) {
                Respond.setText("You found nothing");
                Respond2.setText("Nothing you can found");
                FindMe++;
            }
        }
    }
    }//GEN-LAST:event_FindMouseClicked
    
    //Press Key
    private void SignalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SignalKeyPressed
        //ControlSignal(evt);
    }//GEN-LAST:event_SignalKeyPressed
    
    //Active Battle Screen
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Floor1.setFocusable(false);
        Floor1.setVisible(false);
        Stat.setVisible(false);
        Interaction.setVisible(false);
        Interact.setVisible(true);
        BattleScreen.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked
    
    //PL move
    private void Floor1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Floor1KeyPressed
        int key=evt.getKeyCode();
        int y=Floor1.getHeight();
        int x=Floor1.getWidth();
        switch (key) {
            case KeyEvent.VK_W: 
                if (Signal2.getLocation().y<7) {
                    
                }
                else {
                Signal2.setLocation(Signal2.getLocation().x, Signal2.getLocation().y-10);
                }
                break;
            case KeyEvent.VK_S: 
                if (Signal2.getLocation().y>y-30) {
                    
                }
                else {
                Signal2.setLocation(Signal2.getLocation().x, Signal2.getLocation().y+10);
                }
                break;
            case KeyEvent.VK_D: 
                if (Signal2.getLocation().x>x-30) {
                    
                }
                else {
                Signal2.setLocation(Signal2.getLocation().x+10, Signal2.getLocation().y);
                }
                break;
            case KeyEvent.VK_A: 
                if (Signal2.getLocation().x<7) {
                    
                }
                else {
                Signal2.setLocation(Signal2.getLocation().x-10, Signal2.getLocation().y);
                }
                break;
        }
    }//GEN-LAST:event_Floor1KeyPressed
    
    //Exit
    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_ExitMouseClicked
    
    //Atk button click
    private void AtkButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtkButtonMouseClicked
        if (AtkButton.getText().equalsIgnoreCase("Atk")) {
            TextBattle1.setText("You attack "+dummy.getName());
            int damage=AttackPL()+AddWeapon();
            Battle();
            // Dummy Evade
            DummyEva();
            if (canEvade==true) {
                damage=0;
                canEvade=false;
                TextBattle2.setText("");
            } else
            if (damage<=dummy.getCurrentHp()*0.1) {
                TextBattle2.setText("Not very effect");
            } else TextBattle2.setText("");

            dummy.currentHp=dummy.getCurrentHp()-damage;
            InfoHp();
            TextBattle3.setText(dummy.getName()+" -"+damage+"Hp");
            turn++;
            Interact.setVisible(false);
        } else if (AtkButton.getText().equalsIgnoreCase("Potion of Str")){
            
        }
    }//GEN-LAST:event_AtkButtonMouseClicked
    
    //Reset Text box
    private void TextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextMouseClicked
        TextBattle1.setText("");
        TextBattle2.setText("");
        TextBattle3.setText("");
        Battle();
        
    }//GEN-LAST:event_TextMouseClicked
    
    //see your info
    private void InfoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InfoButtonMouseClicked
        if (InfoButton.getText().equalsIgnoreCase("Info")) {
            TextBattle1.setText("Hp: "+pl.currentHp);
            TextBattle2.setText("Mp: "+pl.currentMp);
        }
        else if (InfoButton.getText().equalsIgnoreCase("back")) {
            ReNew();
        }
        
    }//GEN-LAST:event_InfoButtonMouseClicked
    
    //choose item to use
    private void ItemButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ItemButtonMouseClicked
        if (ItemButton.getText().equalsIgnoreCase("Item")) {
            AtkButton.setText("");
            ItemButton.setText("Mana Potion");
            SkillButton.setText("Healing Potion");
            OtherButton.setText("");
            InfoButton.setText("BACK");
        } else if (ItemButton.getText().equalsIgnoreCase("mana potion")) {
            if (UI.potionMana>0) {
                    if (pl.currentMp==pl.getMaxMp()) {
                    TextBattle1.setText("Your Mp is full");
                    TextBattle2.setText("");
                    TextBattle3.setText("");
                } else {
                    pl.currentMp=pl.currentMp+100;
                    if (pl.currentMp>pl.getMaxMp()) {
                        pl.currentMp=pl.getMaxMp();
                    }
                    UI.potionMana--;
                }
            }
            else {
                TextBattle1.setText("You run out Potion");
                TextBattle2.setText("");
                TextBattle3.setText("");
            }
        ReNew();
        }
        
        
    }//GEN-LAST:event_ItemButtonMouseClicked
    
    //open Inventory
    private void InventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryMouseClicked
        if (ItemUse.isVisible()==false) {
            ItemUse.setVisible(true);
        } else {
            ItemUse.setVisible(false);
        }
        
    }//GEN-LAST:event_InventoryMouseClicked

    private void StickWeaponMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StickWeaponMouseEntered
        InfoText.setText("It a Stick !!!!!");
        ExtraPlus.setText("+2 Str");
    }//GEN-LAST:event_StickWeaponMouseEntered

    private void StickWeaponMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StickWeaponMouseExited
        InfoText.setText("");
        ExtraPlus.setText("");
    }//GEN-LAST:event_StickWeaponMouseExited
    
    //Choose Stick Weapon
    //Set Choosed weapon
    private void StickWeaponMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StickWeaponMouseClicked
        if (UI.Stick()==1) {
            WeaponName="Stick";
            StickWeapon.setIcon(UsedStick);
            WoodenSword.setIcon(WoodenSwordImage);
            IronSword.setIcon(IronSwordImage);
            showStat();
        } else {
            WeaponName="";
            StickWeapon.setIcon(AStick);
            WoodenSword.setIcon(WoodenSwordImage);
            IronSword.setIcon(IronSwordImage);
            UI.setUseWeapon(1);
            showStat();
        }
    }//GEN-LAST:event_StickWeaponMouseClicked

    //Choose Wooden sword weapon
    //Set Choosed weapon
    private void WoodenSwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WoodenSwordMouseClicked
        if (UI.Stick()==1) {
            WeaponName="WoodenSword";
            WoodenSword.setIcon(UsedWoodenSword);
            StickWeapon.setIcon(AStick);
            IronSword.setIcon(IronSwordImage);
            showStat();
        } else {
            WeaponName="";
            WoodenSword.setIcon(WoodenSwordImage);
            StickWeapon.setIcon(AStick);
            IronSword.setIcon(IronSwordImage);
            UI.setUseWeapon(1);
            showStat();
        }
    }//GEN-LAST:event_WoodenSwordMouseClicked
    
    //Wooden Sword Info
    private void WoodenSwordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WoodenSwordMouseEntered
        InfoText.setText("Wooden Sword Very nice for training");
        ExtraPlus.setText("+5 Str");
    }//GEN-LAST:event_WoodenSwordMouseEntered
    
    //Remove wooden Sword Info
    private void WoodenSwordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WoodenSwordMouseExited
        InfoText.setText("");
        ExtraPlus.setText("");
    }//GEN-LAST:event_WoodenSwordMouseExited
    
    //Choose Iron sword weapon
    //Set Choosed weapon
    private void IronSwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IronSwordMouseClicked
        if (UI.Stick()==1) {
            WeaponName="IronSword";
            IronSword.setIcon(UsedIronSword);
            StickWeapon.setIcon(AStick);
            WoodenSword.setIcon(WoodenSwordImage);
            showStat();
        } else {
            WeaponName="";
            IronSword.setIcon(IronSwordImage);
            StickWeapon.setIcon(AStick);
            WoodenSword.setIcon(WoodenSwordImage);
            UI.setUseWeapon(1);
            showStat();
        }
    }//GEN-LAST:event_IronSwordMouseClicked
    
    //Iron Sword Info
    private void IronSwordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IronSwordMouseEntered
        InfoText.setText("Iron Sword");
        ExtraPlus.setText("+10 Str");
    }//GEN-LAST:event_IronSwordMouseEntered
    
    //Remove Iron Sword Info
    private void IronSwordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IronSwordMouseExited
        InfoText.setText("");
        ExtraPlus.setText("");
    }//GEN-LAST:event_IronSwordMouseExited
    
    //
    private void WoodenBowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WoodenBowMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_WoodenBowMouseClicked

    private void WoodenBowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WoodenBowMouseEntered
        InfoText.setText("Wooden Bow");
        ExtraPlus.setText("+3 Str, +1 Agi, +1 Eva");
    }//GEN-LAST:event_WoodenBowMouseEntered

    private void WoodenBowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WoodenBowMouseExited
        InfoText.setText("");
        ExtraPlus.setText("");
    }//GEN-LAST:event_WoodenBowMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    //Skill Button
    private void SkillButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SkillButtonMouseClicked
        if (SkillButton.getText().equalsIgnoreCase("skill")) {
            AtkButton.setText("");
            ItemButton.setText("");
            SkillButton.setText("");
            OtherButton.setText("");
            InfoButton.setText("BACK");
        } else if (ItemButton.getText().equalsIgnoreCase("Healing potion")) {
            if (UI.potionHp>0) {
                    if (pl.currentHp==pl.getMaxMp()) {
                    TextBattle1.setText("Your Hp is full");
                    TextBattle2.setText("");
                    TextBattle3.setText("");
                } else {
                    pl.currentHp=pl.currentHp+100;
                    if (pl.currentHp>pl.getMaxHp()) {
                        pl.currentHp=pl.getMaxHp();
                    }
                    UI.potionHp--;
                }
            }
            else {
                TextBattle1.setText("You run out Potion");
                TextBattle2.setText("");
                TextBattle3.setText("");
            }
        ReNew();
        }
    }//GEN-LAST:event_SkillButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InGameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InGameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InGameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InGameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InGameScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AtkButton;
    private javax.swing.JPanel BattleScreen;
    private javax.swing.JButton Exit;
    private javax.swing.JLabel ExtraPlus;
    private javax.swing.JToggleButton Find;
    private javax.swing.JToggleButton Flee;
    private javax.swing.JPanel Floor1;
    private javax.swing.JToggleButton Help;
    private javax.swing.JLabel ImageMonster1;
    private javax.swing.JButton InfoButton;
    private javax.swing.JLabel InfoHp;
    private javax.swing.JLabel InfoText;
    private javax.swing.JPanel Interact;
    private javax.swing.JPanel Interaction;
    private javax.swing.JToggleButton Inventory;
    private javax.swing.JLabel IronSword;
    private javax.swing.JButton ItemButton;
    private javax.swing.JPanel ItemUse;
    private javax.swing.JLabel NameMonster1;
    private javax.swing.JButton OtherButton;
    private javax.swing.JLabel Respond;
    private javax.swing.JLabel Respond2;
    private javax.swing.JPanel Signal2;
    private javax.swing.JButton SkillButton;
    private javax.swing.JPanel Stat;
    private javax.swing.JLabel StickWeapon;
    private javax.swing.JPanel Text;
    private javax.swing.JLabel TextBattle1;
    private javax.swing.JLabel TextBattle2;
    private javax.swing.JLabel TextBattle3;
    private javax.swing.JLabel V;
    private javax.swing.JPanel WeaponInfo;
    private javax.swing.JLabel WoodenBow;
    private javax.swing.JLabel WoodenSword;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lblAgi;
    private javax.swing.JLabel lblEva;
    private javax.swing.JLabel lblInt;
    private javax.swing.JLabel lblStr;
    private javax.swing.JLabel lblWis;
    private javax.swing.JProgressBar pbHp;
    private javax.swing.JProgressBar pbMana;
    // End of variables declaration//GEN-END:variables
}
