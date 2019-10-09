package no.oslomet.cs.algdat;

import java.util.List;

public class ObligToMain {


    public static void main (String[] args) {


        /*
        //Oppgave 0
        Liste<String> liste = new DobbeltLenketListe<>();
        System.out.println(liste.antall() + " + " + liste.tom());

        //Oppgave 1
        String[] arro = {"Stian", "Arne", "Goddo", "Hegnar", "Sissner"};
        Liste<String> navnListe = new DobbeltLenketListe<>(arro);
        System.out.println(navnListe.antall() + " + " + navnListe.tom());
        Liste<Integer> listeEn = new DobbeltLenketListe<>(new Integer[]{1});
        System.out.println(listeEn.antall() + " + " + listeEn.tom());

        Liste<Integer> listeTom = new DobbeltLenketListe<>();
        System.out.println(listeTom.antall() + " + " + listeTom.tom());

        Liste<Integer> listeToTall = new DobbeltLenketListe<>(new Integer[]{1, 2});
        System.out.println("Liste med to tall: " + listeToTall.antall() + " + " + listeToTall.tom());

        Liste<Integer> listeNull = new DobbeltLenketListe<>(new Integer[]{null});
        System.out.println("Liste null: " + listeNull.antall() + " + " + listeNull.tom());

        //String[] s = {​"Ole"​, ​null, ​"Per"​, ​"Kari​", ​null};
       // Liste<String> listeTest = ​new DobbeltLenketListe<>(s);
        //System.​out ​ .println(liste.​antall ​ () + ​"​ ​"​ + liste.​tom ​ ())

        String[] test = {"Ole", null, "Per", "Kari", null};
        Liste<String> listeTest = new DobbeltLenketListe<>(test);
        System.out.println(listeTest.antall() + " + " + listeTest.tom());

        //Oppgave 2
        System.out.println(navnListe.antall() + " + " + navnListe.tom() + " + " +navnListe.toString());
        */
        String[] arro = {"Stian", "Arne", "Goddo", "Hegnar", "Sissner"};
        Liste<String> navnListe = new DobbeltLenketListe<>(arro);
        navnListe.leggInn(null);
        System.out.println(navnListe);
        //System.out.println(navnListe);


    }

}
