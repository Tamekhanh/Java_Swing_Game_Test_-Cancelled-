/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class SemiMain {
    public void ReadFileST() {
        File f=new File ("Main.txt");
        try {
            FileReader fr= new FileReader (f);
            BufferedReader br= new BufferedReader (fr) ;
            String line;
            Scanner scanD=new Scanner (fr);
            while ((line=br.readLine())!=null) {
                if (line.trim().equals("")) continue;
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
