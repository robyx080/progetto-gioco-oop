package model;

public class csv implements Comparable<csv>{    //classe csv che implementerà l'interfaccia Comparable

    private int points;                         //variabile che conterrà il punteggio ottenuto da un utente in una singola partita
    private String date;                        //variabile che conterrà la data in cui è stato effettuato quel punteggio

    public csv(int points, String date) {       //costruttore csv
        this.points = points;
        this.date = date;
    }

   @Override
    public int compareTo (csv com) {            //metodo compareTo della classe comparable cambiato di implementazione
        return com.points - this.points;
    }

    @Override
    public String toString() {                  //metodo toString
        return points+","+date;                 //ritorna il punteggio e la data come singola stringa
    }
}
