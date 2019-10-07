package no.oslomet.cs.algdat;


////////////////// class DobbeltLenketListe //////////////////////////////


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
        //throw new NotImplementedException();
    }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull("null objekt er ikke tillat!");

        Objects.requireNonNull("null objekt er ikke tillat!");

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
                        hale.neste = new Node<>(a[i], null, hale);
                        hale = hale.neste;
                        antall++;
                    }
                }
            }
        }// end outer if






    }// DobbeltLenketListe konstrukør
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Liste<T> subliste(int fra, int til){
        throw new NotImplementedException();
    }

    @Override
    public int antall() {
        if (antall < 0)
            throw new NotImplementedException();
        return antall;
    }

    @Override
    public boolean tom() {
        new NotImplementedException();
        return antall==0;
    }
   ///////////////// Oppgave 2/////////////////////////////////////////
    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(" Null verdier er ikke tillat.");
        // tilfelle 1- om listen var tom liste
        if (tom()){
            // kan skrives også som if (hode==null && hale==null)
         hode= hale= new Node<T>(verdi, null,null);
         antall++;
        }
        else {
        hale.neste= new Node<T>(verdi, hale, null);
        hale= hale.neste;
            antall++;
            endringer++;
        }// end else
        return true;
    }// end leggInn
    ///////////////////////////////////////////////////////////////////

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new NotImplementedException();
    }
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

    private static void fraTilKontroll(int fra, int til, int antall){
        if (fra < 0) throw new IndexOutOfBoundsException("fra ("+ fra +"er negaativ ");
        if (til> antall) throw new IndexOutOfBoundsException("til ( "+ til +") > antall ("+ antall+ ")");
        if (fra> til) throw new IllegalArgumentException(" Illegalt intervalt");
    }// end fratilkontrioll

    public Liste<T> subListe(int fra, int til){
        fraTilKontroll(fra, til, antall);
        if (fra==til){
            return new DobbeltLenketListe<>();
        }
        Node<T> curr = finnNode(fra);
        DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();
        while (fra < til){
            subliste.leggInn(curr.verdi);
            curr= curr.neste;
        }// end while
        return subliste;
    }// end subliste

    @Override
    public T hent (int indeks){
        indeksKontroll(indeks, false);
        return  finnNode(indeks).verdi;
    }// end hent


    @Override
    public int indeksTil(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false);
            // bruk finnNode metoden for å finne noden, metoden tar inn indeksen
            T verdi= finnNode(indeks).verdi;
            // oppdater verdien til noden
            verdi = nyverdi;
            endringer++;
            return  verdi;
        }// end oppdater


    @Override
    public boolean fjern(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T fjern(int indeks) {
        throw new NotImplementedException();
    }

    @Override
    public void nullstill() {
        throw new NotImplementedException();
    }
    ////////////// Oppgave- 2 /////////////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        String resultat = "[";
        Node<T> curr = hode;
        while (curr != null) {
            resultat+= " "+curr.verdi;
            curr= curr.neste;
        }// end while
        return resultat+= "]";
    }// end String

    public String omvendtString() {
        String resultat = " [";
        Node<T> curr= hale;
        //if (tom()) return "[]";

        while (curr!=null){
            resultat+= " "+curr.verdi;
            curr= curr.forrige;
        }
        return resultat+= "]";
    }// end OmvendtString
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<T> iterator() {
        throw new NotImplementedException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new NotImplementedException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            throw new NotImplementedException();
        }

        private DobbeltLenketListeIterator(int indeks){
            throw new NotImplementedException();
        }

        @Override
        public boolean hasNext(){
            throw new NotImplementedException();
        }

        @Override
        public T next(){
            throw new NotImplementedException();
        }

        @Override
        public void remove(){
            throw new NotImplementedException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new NotImplementedException();
    }

} // class DobbeltLenketListe


