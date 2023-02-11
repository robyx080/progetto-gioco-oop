package model;

public enum SHIP{   //tipo di dato enum (può prendere un singolo valore tra quelli sottostanti)

    BLUE("model/resources/shipchooser/blue_ship.png", "model/resources/shipchooser/blue_life.png"),
    GREEN("model/resources/shipchooser/green_ship.png", "model/resources/shipchooser/green_life.png"),
    ORANGE("model/resources/shipchooser/orange_ship.png", "model/resources/shipchooser/orange_life.png"),
    RED("model/resources/shipchooser/red_ship.png", "model/resources/shipchooser/red_life.png");

    private String urlShip;
    private String urlLife;

    private SHIP(String urlShip, String urlLife){
        this.urlShip = urlShip;    //settiamo il percorso dell'immagine della nave
        this.urlLife = urlLife;    //settiamo il percorso dell'immagine della nave piccola utilizzata per la vita in game
    }

    public String getUrl(){        //metodo getUrl

        return this.urlShip;       //ritorniamo il percorso dell'immagine della nave

    }

    public String getUrlLife(){    //metodo getUrl

        return this.urlLife;       //ritorniamo il percorso dell'immagine della nave piccola utilizzata per la vita in game

    }

}
