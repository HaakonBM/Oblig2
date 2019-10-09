package no.oslomet.cs.algdat;


////////////////// class DobbeltLenketListe //////////////////////////////


import com.sun.org.apache.xpath.internal.objects.XBoolean;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

////////////////////////     Oppgave - 1 ////////////////////////////////////
    public DobbeltLenketListe() {
        this.hode= null;
        this.hale= null;
        antall=0; }

    public DobbeltLenketListe(T[] a) {
            Objects.requireNonNull(a, "null objekt er ikke tillat!");
        // Finner første verdi i arrayet som ikke er null
        if(a.length > 0){
            int i = 0;
            for (;i < a.length; i++){
                if(a[i] != null){
                    hode = new Node<>(a[i], null, null);
                    antall++;
                    break;
                }
            }// end for

            hale = hode;
            if(hode != null){
                i++;
                for(;i < a.length; i++){
                    if(a[i] != null){
                        hale.neste = new Node<>(a[i], hale, null);
                        hale = hale.neste;
                        antall++;
                    }
                }
            }
        }// end outer if
    }// DobbeltLenketListe konstrukør

    @Override
    public int antall() {
        if (antall < 0)
            throw new NotImplementedException();
        return antall;
    }// end antall

    @Override
    public boolean tom() {
        new NotImplementedException();
        return antall==0;
    }// end tom

    ///////////////// Oppgave 2/////////////////////////////////////////
   @Override
   public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi," Null verdier er ikke tillat.");
       // tilfelle 1- om listen var tom liste
           if (tom()) {
               // kan skrives også som if (hode==null && hale==null)
               hode = hale = new Node<T>(verdi, null, null);
               antall++;
           } else {
               hale.neste = new Node<T>(verdi, hale, null);
               hale = hale.neste;
               antall++;
               endringer++;
           }// end else
           return true;
   }// end leggInn
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> curr = hode;
        if (antall > 0) {
            sb.append(curr.verdi);
        }
        for (int i = 1; i < antall; i++) {
            curr = curr.neste;
            sb.append(", ");
            sb.append(curr.verdi);
        }
        sb.append("]");
        return sb.toString();
    }// end String

    public String omvendtString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        Node<T> curr = hale;
        while (curr != null) {
            sj.add(curr.verdi.toString());
            curr = curr.forrige;
        }
        return sj.toString();
    }// end OmvendtString


    //////////////////////////// Oppgave -3 //////////////////////////////////////////////////////////////
    private Node<T> finnNode(int indeks){
        Node<T> curr;
        // begin på hode
        if (indeks < antall/2){
            curr= hode;
            for ( int i=0; i<indeks; i++){
                curr= curr.neste;
            }// end for
        }// end if
        else {
            curr= hale;
            for (int i = antall-1; i>indeks; i--){
                curr= curr.forrige;
            }// end for
        }// end else
        return curr;
    }// end finnNode

    @Override
    public T hent (int indeks){
        indeksKontroll(indeks, false);
        return  finnNode(indeks).verdi;
    }// end hent

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi);
        indeksKontroll(indeks, false);
        // bruk finnNode metoden for å finne noden, metoden tar inn indeksen
        Node<T> k= finnNode(indeks);
        T verdi = k.verdi;
        // oppdater verdien til node k
        k.verdi= nyverdi;
        endringer++;
        return  verdi;
    }// end oppdater

    public Liste<T> subliste(int fra, int til){

        fraTilKontroll(fra, til, antall);
        if (fra==til){
            return new DobbeltLenketListe<>();
        } else {

            Node<T> curr = finnNode(fra);
            DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();
            while (fra < til ) {
                subliste.leggInn(curr.verdi);
                curr = curr.neste;
                fra++;
            }// end while
            return subliste;
        }
    }// end subliste

    private static void fraTilKontroll(int fra, int til, int antall){
        if (fra < 0) throw new IndexOutOfBoundsException("fra ("+ fra +"er negaativ ");
        if (til> antall) throw new IndexOutOfBoundsException("til ( "+ til +") > antall ("+ antall+ ")");
        if (fra> til) throw new IllegalArgumentException(" Illegalt intervalt");
    }// end fratilkontrioll


    ////////////////////////////////////// Oppgave 4 //////////////////////////////////////
    @Override
    public int indeksTil(T verdi) {
        int indeks=0;
        Node<T> curr= hode;
        for (; curr!=null; curr= curr.neste, indeks++){
            if (curr.verdi.equals(verdi)){
                return indeks;

            }// end if

        }// end for
        return -1;
    }// end indeksTil

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }// end inneholder

    /////////////////////////////////  Oppgave 5 ///////////////////////////////////////////////////////

    @Override

    public void leggInn(int indeks, T verdi) {
        if (verdi == null){
            throw new NullPointerException(); }
        if (indeks < 0){
            throw new IndexOutOfBoundsException(); }
        if (indeks > (antall-1)){
            throw new IndexOutOfBoundsException(); }

        Node<T> newNode = new Node<T>(verdi);
        if (hode == null ){ // Hvis Listen er Tom
            hode = newNode;
            hale = newNode;
        }
        else if (indeks == 0){
            newNode.neste = hode;
            hode.forrige = newNode;
            hode = newNode;
        }
        else if (indeks == (antall - 1)){
            newNode.forrige = hale;
            hale.forrige = newNode;
            hale = newNode;
        }
        else {
            Node<T> nodeTemp = hode;
            for (int i = 1; i < indeks; i++){
                nodeTemp = nodeTemp.neste;
                newNode.neste = nodeTemp.neste;
                nodeTemp.neste = newNode;
                newNode.forrige = nodeTemp;
                newNode.neste.forrige = newNode;
            }
        }
        antall++;
        endringer++;
    }

///////////////////////////////////// Oppgave 6 /////////////////////////////////////////

@Override
public boolean fjern(T verdi) {
    Node<T> curr = hode;
    while (curr != null) {
        if (curr.verdi.equals(verdi)) {
            if (antall == 1) {
                hode = hale = null;
            } else if (curr == hode) {
                hode = hode.neste;
                hode.forrige = null;
            } else if (curr == hale) {
                hale = hale.forrige;
                hale.neste = null;
            } else {
                curr.forrige.neste = curr.neste;
                curr.neste.forrige = curr.forrige;
            }
            antall--;
            endringer++;
            return true;
        }
        curr = curr.neste;
    }
    return false;
}
    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);
        T verdi;
        if (antall == 1) {
            verdi = hode.verdi;
            hode = hale = null;
        } else if (indeks == 0) {
            verdi = hode.verdi;
            hode = hode.neste;
            hode.forrige = null;
        } else if (indeks == antall - 1) {
            verdi = hale.verdi;
            hale = hale.forrige;
            hale.neste = null;
        } else {
            Node<T> curr = finnNode(indeks);
            verdi = curr.verdi;
            curr.forrige.neste = curr.neste;
            curr.neste.forrige = curr.forrige;
        }
        antall--;
        endringer++;
        return verdi;
    }



    ////////////////////////  Oppgave 7  /////////////////////////////////////////////////

    @Override
    public void nullstill() {
       Node<T> curr= hode;
       while (curr!=null){
       Node<T> p= curr.neste;
       curr.forrige=curr.neste= null;
       curr.verdi= null;
       curr= p;
       }
       hode= hale= null;
       endringer++;
       antall=0;
    }

    ///////////////////////////// Oppgave - 8 //////////////////////////////////////////////

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        public DobbeltLenketListeIterator(){
            denne= hode;
            fjernOK=false;
            iteratorendringer= endringer;
        }

        private DobbeltLenketListeIterator(int indeks){
            denne=finnNode(indeks);
            fjernOK=false;
            iteratorendringer=endringer;

        }

        @Override
        public boolean hasNext(){
            return denne!=null;
        }

        @Override
        public T next(){
            if (endringer!=iteratorendringer)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();
            T resultat = denne.verdi;
            denne= denne.neste;
            fjernOK= true;
            return resultat;
        }

        @Override
        public void remove(){

            if (!fjernOK) throw new IllegalStateException();
            if (endringer != iteratorendringer) throw new ConcurrentModificationException();
            fjernOK = false;
            if (antall == 1) {
                hode = hale = null;
            } else if (denne == null) {
                hale = hale.forrige;
                hale.neste = null;
            } else if (denne.forrige == hode) {
                hode = hode.neste;
                hode.forrige = null;
            } else {
                denne.forrige.forrige.neste = denne;
                denne.forrige = denne.forrige.forrige;
            }
            antall--;
            endringer++;
            iteratorendringer++;
        }

    } // class DobbeltLenketListeIterator

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }


    ////////////////////////// Opg 10  ////////////////////////////////////
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        if (liste.tom()){
            return;
        }

        int antallListe = liste.antall();

        if (antallListe == 1){
            return;
        }


        /*public T compareTo(char a, char b){



        }*/// Metode for å Sammenligne både int og String,   får det ikke til



        char a;
        char b;


        for (int i = 0; i <antallListe; i++){
            for (int j = i +1; j <antallListe; j++ ) {


                /*if (liste.hent(i) compareTo  liste.hent(j) ) {

                    liste.leggInn(i,(liste.hent(j)));
                    liste.leggInn(j,(liste.hent(i)));
                    liste.fjern(i+1);
                    liste.fjern(j+1);
                }*/////
            }
        }
    }

} // class DobbeltLenketListe


