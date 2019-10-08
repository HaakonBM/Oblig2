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



    /////////////////////////// Oppgave 5 /////////////////////////////////////////////////////
    @Override
    public void leggInn(int indeks, T verdi) {
        if (verdi == null){
            throw new NullPointerException();
        }

        if (indeks < 0){
            throw new IndexOutOfBoundsException();
        }
        if (indeks > (antall-1)){
            throw new IndexOutOfBoundsException();
        }


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
    ///////////// oppgave 5 slutt

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



    ///////////////////// Oppgave 6 /////////////////////////////////////
    @Override
    public boolean fjern(T verdi) {
        if (verdi == null){
            return false;
        }
        int a;
        for(int i = 0; i < antall; i++){
            Node (i)

        }


        throw new NotImplementedException();


    }

    @Override
    public T fjern(int indeks) {

        Node<T> q = finnNode(indeks);
        Node<T> p = finnNode(indeks - 1);
        Node<T> r = finnNode(indeks + 1);

        if (q == null){
            throw new IndexOutOfBoundsException();
        }
        if (indeks> (antall-1)){
            throw new IndexOutOfBoundsException();
        }
        if(indeks<0){
            throw new IndexOutOfBoundsException();
        }

        if (p == null && r == null){
            hode = null;
            hale = null;
        }
        else if (p == null){
            r.forrige = null;
            hode = r;
        }
        else {
            p.neste = r;
            r.forrige = p;
        }
        endringer++;
        antall--;

        return (T) q;

    }
    //////////////////oppgave 6 slutt

    @Override
    public void nullstill() {
        throw new NotImplementedException();
    }
    ////////////// Oppgave- 2 /////////////////////////////////////////////////////////////////////////////////
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


