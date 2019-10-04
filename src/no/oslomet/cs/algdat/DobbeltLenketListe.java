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
        throw new NotImplementedException();
    }

    public DobbeltLenketListe(T[] a) {
        if (a.length <= 0) {
            throw new NullPointerException("Tabellen er null.");
        }

        // anta at det finnes verdi i arrayet og legges de som ikke er null inni i listen. Da må vi løpe
        // gjennom arrayet
        if (a.length > 0) {
            for (int i = 0; i < a.length; i++) {
                // de som ikke lik null blir med
                if (a[i] != null) {
                    // opprett en ny node til elementet fra arrayet og hode peker til denne, for denne er
                    // den første node i listen
                    hode = new Node<>(a[i], null, null);
                    antall++;


                }// end inner if
            }// end for
            // nå har vi i hvert fall en node, og vi skal legge den neste etter den første.
            // hode=hale , men hode ikke er null, dvs begge peker på første node.
            hode= hale; // finnes kun en node i listen
            if (hode!=null){
                for (int i=0; i<a.length; i++){
                    if (a[i]!=null){
                        hale= new Node<>(a[i],null, hale);// nynod sin frg er tidligere hale
                        hale= hale.neste; // hale flytter et skritt til høyre
                        antall++;
                    }// end inner if
                } // end for
            }// end outer if

        }// end outer if

    }// DobbeltLenketListe konstrukør

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

    @Override
    public boolean leggInn(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T hent(int indeks) {
        throw new NotImplementedException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new NotImplementedException();
    }

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

    @Override
    public String toString() {
        throw new NotImplementedException();
    }

    public String omvendtString() {
        throw new NotImplementedException();
    }

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


