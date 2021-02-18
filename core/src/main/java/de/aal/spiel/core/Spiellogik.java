package de.aal.spiel.core;

import java.util.ArrayList;
import java.util.List;

public class Spiellogik {

    private List<Spieler> spielerList = new ArrayList<>();

    public void addSpieler(Spieler p) throws Exception{
       try{
           if(spielerList.size()<=4){
               spielerList.add(p);
           }
           else {
               throw new Exception();
           }
        }
       catch (Exception e){
           System.out.println("FEHLER!");
        }
    }

    public List<Spieler> getSpielerList() {
        return spielerList;
    }
}
