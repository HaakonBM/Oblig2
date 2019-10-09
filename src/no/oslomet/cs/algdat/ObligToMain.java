package no.oslomet.cs.algdat;

import java.util.List;

public class ObligToMain {


    public static void main (String[] args) {

        String[] arro = {"Stian", "Arne", "Goddo", "Hegnar", "Sissner"};
        Liste<String> navnListe = new DobbeltLenketListe<>(arro);
        //navnListe.leggInn(null);
        System.out.println(navnListe);
        System.out.println(((DobbeltLenketListe<String>) navnListe).omvendtString());

        Integer[] arrOInt = {1, 2, 3 ,4, 5};
        Liste<Integer> intListe = new DobbeltLenketListe<Integer>(arrOInt);
        System.out.println(intListe);
        System.out.println(((DobbeltLenketListe<Integer>) intListe).omvendtString());
        intListe.leggInn(3);
        //intListe.leggInn(null);
        System.out.println(intListe);
        System.out.println(((DobbeltLenketListe<Integer>) intListe).omvendtString());

        String[] s1 = {};
        String [] s2 = {"A"};
        String [] s3 = {null, "A", null, "B", null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);
//        l1.leggInn(null);
        System.out.println(l1.toString() + " " + l2.toString() + " " + l3.toString() + " " + l3.omvendtString() + " " + l2.omvendtString() + " " +
                l3.omvendtString() );

        int antallFeil = 0;
        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

        if (!liste.toString().equals("[]")) {
            antallFeil++;
            System.out.println("Oppgave 2e: Tom liste skal gi []!");
        }

        if (!liste.omvendtString().equals("[]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 2f: Tom liste skal gi []!");
        }

        liste.leggInn(1);

        String s = liste.toString();
        if (!s.equals("[1]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 2g: Du har " + s + ", skal være [1]!");
        }

        s = liste.omvendtString();
        if (!s.equals("[1]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 2h: Du har " + s + ", skal være [1]!");
        }

        liste.leggInn(2);

        s = liste.toString();
        if (!s.equals("[1, 2]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 2i: Du har " + s + ", skal være [1, 2]!");
        }

        s = liste.omvendtString();
        if (!s.equals("[2, 1]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 2j: Du har " + s + ", skal være [2, 1]!");
        }

        System.out.println("2, 1" + s);
        liste.leggInn(3);
        liste.leggInn(4);

        s = liste.toString();
        if (!s.equals("[1, 2, 3, 4]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 2k: Du har " + s + ", skal være [1, 2, 3, 4]!");
        }

        s = liste.omvendtString();
        if (!s.equals("[4, 3, 2, 1]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 2l: Du har " + s + ", skal være [4, 3, 2, 1]!");
        }
        System.out.println(s + "4 , 3, 2, 1");

    }

}
