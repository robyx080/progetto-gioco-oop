package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import model.*;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class GameViewManager {                     //classe gameViewManager

    private AnchorPane gamePane;                   //variabile che ci permette di ancorare immagini,testi ecc a video
    private Scene gameScene;                       //variabile che ci permette di creare la scena iniziale del gioco
    private Stage gameStage;                       //variabile che ci permette di creare la finestra del gioco
    private Stage menuStage;                       //variabile che conterrà la finestra del menu di gioco
    private ImageView ship;                        //variabile che conterrà l'immagine della nave selezionata

    private final int GAME_WIDTH = 600;     //altezza della finestra del gioco
    private final int GAME_HEIGHT = 800;    //larghezza della finestra del gioco


    private boolean isLeftKeyPressed;              //variabile che ci permetterà di capire se clicchiamo la freccia sinistra
    private boolean isRightKeyPressed;             //variabile che ci permetterà di capire se clicchiamo la freccia destra
    private boolean isUpKeyPressed;                //variabile che ci permetterà di capire se clicchiamo la freccia sopra
    private boolean isDownKeyPressed;              //variabile che ci permetterà di capire se clicchiamo la freccia sotto

    private int angle;                             //variabile che ci permetterà di angolare l'immagine della nave
    private AnimationTimer gameTimer;

    private GridPane gridPane1;                    //griglia per lo sfondo animato nel gioco
    private GridPane gridPane2;                    //griglia per lo sfondo animato nel gioco

    //percorsi del background
    private final String BACKGROUND_IMAGE = "model/resources/deep_blue.png";


    //variabile che ci conterrà le immagini delle vite
    private ImageView[] playerLifes;

    private SmallInfoLabel pointsLabel;             //label che conterrà il punteggio in game
    private int playerLife;                         //variabile che useremo come contatore di vite
    private int points;                             //variabile che useremo come contatore per il punteggio

    private final int SHIP_RADIUS=27;        //sorta di hitbox per la nave

    //variabili Music con percorsi dei vari effetti sonori
    private final Music musicPoints = new Music("src/model/resources/mixkit-retro-game-notification-212.mp3");
    private final Music musicHits = new Music("src/model/resources/mixkit-space-impact-774.mp3");
    private final Music gameOver = new Music("src/model/resources/game_over.mp3");
    private final Music life_plus = new Music("src/model/resources/life_plus.mp3");
    
    //array di tipo Oggetto che conterrà gli oggetti Star e Meteors
    private final Oggetto[] oggetti ={new Star(),new Meteors(0),new Meteors(0),new Meteors(0),new Meteors(0),new Meteors(0),new Meteors(1),new Meteors(1),new Meteors(1),new Meteors(1),new Meteors(1)};

    public GameViewManager(){                       //costruttore gameViewManager
        initializeStage();                          //richiamiamo il metodo initializaStage che ci permetterà di creare la finestra di gioco
        createKeyListeners();                       //richiamiamo il metodo createKeyListeners che ci permettera di usare le frecce direzionali in game
    }

    private void initializeStage(){               //metodo initializaStage
        gamePane = new AnchorPane();              //creazione variabile AnchorPane
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);    //creazione scena principale
        gameStage = new Stage();                  //creazione finestra del gioco
        gameStage.setScene(gameScene);            //all'interno della finestra mettiano la scena principale
        gameStage.setTitle("Mission BusTom");     //settiamo il titolo della finestra di gioco
    }

    private void createKeyListeners(){              //metodo createKyListeners
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {    //se una freccia è premuta setta la variabile corrispondente come true
            @Override
            public void handle(KeyEvent Event) {
                if(Event.getCode() == KeyCode.LEFT ){
                    isLeftKeyPressed=true;
                }else if (Event.getCode() == KeyCode.RIGHT){
                    isRightKeyPressed=true;
                }else if(Event.getCode() == KeyCode.UP){
                    isUpKeyPressed=true;
                }else if(Event.getCode() == KeyCode.DOWN){
                    isDownKeyPressed=true;
                }
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {   //se una freccia non è premuta setta la variabile corrispondente come false
            @Override
            public void handle(KeyEvent Event) {
                if(Event.getCode() == KeyCode.LEFT ){
                    isLeftKeyPressed=false;
                }else if (Event.getCode() == KeyCode.RIGHT){
                    isRightKeyPressed=false;
                }else if(Event.getCode() == KeyCode.UP){
                    isUpKeyPressed=false;
                }else if(Event.getCode() == KeyCode.DOWN){
                    isDownKeyPressed=false;
                }
            }
        });
    }

    public void createNewGame(Stage menuStage, SHIP choosenShip){     //metodo createNewGame
        this.menuStage =menuStage;                //salvo la schermata del menu
        this.menuStage.hide();                    //nascondo (chiudo provissoriamente) la schermata del menu
        createBackground();                       //richiamiamo il metodo createBackground() per far creare lo sfondo
        createShip(choosenShip);                  //richiamiamo il metodo createShip() per far creare la nave
        createGameElements(choosenShip);          //richiamiamo il metodo createGameElements() per far creare gli elementi del gioco
        createGameLoop();                         //richiamiamo il metodo createGameLoop() per far partire il gioco
        gameStage.show();                         //mostriamo la finestra di gioco
    }

    private void createBackground(){                            //metodo createBackground
        gridPane1 = new GridPane();                             //creiamo le variabili GridPane
        gridPane2 = new GridPane();
        /* gridpane:
         *   | 0 | 1 | 2 | 3
         * 0 |___|___|___|___
         * 1 |___|___|___|___
         * 2 |___|___|___|___
         */
        for(int i=0;i<12;i++){                                  //per ogni spazio del Grid mettiamo lo sfondo e poi lo aggiungiamo al gamePane
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            //System.out.println(i%3);
            //System.out.println(i/3);
            GridPane.setConstraints(backgroundImage1,i%3,i/3);
            GridPane.setConstraints(backgroundImage2,i%3,i/3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);

        }
        gridPane2.setLayoutY(-1024);
        gamePane.getChildren().addAll(gridPane1,gridPane2);
    }

    private void createShip(SHIP choosenShip){               //metodo createShip
        ship = new ImageView(choosenShip.getUrl());          //creiamo la nava
        ship.setLayoutX(GAME_WIDTH/2);                       //settiamo la coordinata x della nave
        ship.setLayoutY(GAME_HEIGHT-90);                     //settiamo la coordinata y della nave
        gamePane.getChildren().add(ship);                    //aggiungiamo la nava al gamePane

    }

    private void createGameElements(SHIP choosenShip){        //metodo createGameElements
        playerLife=2;                                         //inizializziamo le vite del giocatore (ne avrà 3 ovvero 0-1-2)

        pointsLabel=new SmallInfoLabel("POINTS : 0");    //creaiamo e settiamo la label che conterrà il punteggio in game
        pointsLabel.setLayoutX(450);                          //settiamo la coordinata x della label
        pointsLabel.setLayoutY(20);                           //settiamo la coordinata y della label
        gamePane.getChildren().add(pointsLabel);              //aggiungiamo la label al gamePane

        playerLifes=new ImageView[3];                         //creiamo l'array che conterra le immagini delle vite
        for(int i=0;i<playerLifes.length;i++){                //creaiamo le immagini le settiamo e le aggiungiamo al gamePane
            playerLifes[i]=new ImageView(choosenShip.getUrlLife());
            playerLifes[i].setLayoutX(455+(i*50));
            playerLifes[i].setLayoutY(80);
            gamePane.getChildren().add(playerLifes[i]);
        }

        for (Oggetto oggetto : oggetti) {                //settiamo la posizione degli oggetti star e meteors e li aggiungiamo al gamePane
            oggetto.setNewElementPosition(oggetto.getOggetto());
            gamePane.getChildren().add(oggetto.getOggetto());
        }

    }

    private void createGameLoop(){                           //metodo createGameLoop
        gameTimer = new AnimationTimer() {                   //creiamo la variabile AnimationTimer
            @Override
            public void handle(long l) {
                moveBackground();                            //richiamiamo il metodo moveBackground che farà muovere lo sfondo
                moveGameElements();                          //richiamiamo il metodo moveGameElements che farà muovere gli elementi del gioco
                checkIfElementsAreBehindTheShipAndRelocate();//richiamiamo il metodo checkIfElementsAreBehindTheShipAndRelocate che controllera se gli elementi sono finiti sotto lo schermo
                checkIfElementsCollide();                    //richiamiamo il metodo checkIfElementsCollide che controllerà se i vari elementi in game si sono scontrati con la nave
                moveShip();                                  //richiamiamo il metodo moveShip che farà muovere la nave
            }
        };
        gameTimer.start();                                   //startiamo l'animationTimer
    }

    private void moveBackground(){                              //metodo moveBackground
        gridPane1.setLayoutY(gridPane1.getLayoutY()+0.5);       //settiamo la cordinata y del gridPane1
        gridPane2.setLayoutY(gridPane2.getLayoutY()+0.5);       //settiamo la cordinata y del gridPane2

        if(gridPane1.getLayoutY()>=1024){                       //se la cordinata y è uguale a 1024 o superiore facciome risalire il gridPane1 alla posizione originale
            gridPane1.setLayoutY(-1024);
        }
        if(gridPane2.getLayoutY()>=1024){                       //se la cordinata y è uguale a 1024 o superiore facciome risalire il gridPane2 alla posizione originale
            gridPane2.setLayoutY(-1024);
        }
    }

    private void moveGameElements(){                          //metodo moveGameElements
        for (Oggetto oggetto : oggetti) {                //creaiamo le immagini le settiamo e le aggiungiamo al gamePane
            oggetto.move();
        }
    }

    private void checkIfElementsAreBehindTheShipAndRelocate(){   //metodo checkIfElementsAreBehindTheShipAnRelocate
        for (Oggetto oggetto : oggetti) {                //creaiamo le immagini le settiamo e le aggiungiamo al gamePane
            oggetto.checkIfElementsAreBehindTheShipAndRelocate(oggetto.getOggetto());
        }
    }

    private void checkIfElementsCollide(){                      //metodo checkIfElementsCollide
        for(int i=0;i< oggetti.length;i++){          //se la nave e un oggetto si colpiscono
            if(oggetti[i].getRadius() + SHIP_RADIUS > calculateDistance(ship.getLayoutX()+49,oggetti[i].getPositionX(oggetti[i].getOggetto())+20, ship.getLayoutY()+37, oggetti[i].getPositionY(oggetti[i].getOggetto())+20)){
                if(i==0){                            //se l'oggetto è una stella
                    /*System.out.print("x: ");
                    System.out.println(oggetti[i].getPositionX(oggetti[i].getOggetto()));
                    System.out.print("y: ");
                    System.out.println(oggetti[i].getPositionY(oggetti[i].getOggetto()));
                    System.out.print("nave x: ");
                    System.out.println(ship.getLayoutX());
                    System.out.print("nave y: ");
                    System.out.println(ship.getLayoutY());
                    System.out.println("nave colpita ");*/
                    oggetti[0].setNewElementPosition(oggetti[0].getOggetto());                 //settiamo la nuova posizione della stella
                    musicPoints.musicStart();                           //richiamiamo il metodo musicStart per far sentire l'effetto sonoro dell'aumento del punteggio
                    points++;                                           //aumentiamo il punteggio
                    if(points%10==0){                                   //ogni 10 punti aggiungiamo una vita se le vite sono minori di 3
                        addLife();                                      //richiamiamo il metodo addLife
                    }
                    pointsLabel.setText("POINTS : "+points);            //settiamo il punteggio
                }else {                             //se l'oggetto è una meteora
                    oggetti[i].setNewElementPosition(oggetti[i].getOggetto());             //settiamo la nuova posizione della meteora
                    musicHits.musicStart();                         //richiamiamo il metodo musicStart per far sentire l'effetto sonoro della collisione
                    removeLife();                                   //richiamiamo il metodo removeLife
                }
            }
        }
    }

    private void moveShip(){                                 //metodo moveShip
        if(isUpKeyPressed && !isDownKeyPressed){             //se solo la freccia in su è pressata muoviamo la nave verso sopra
            if(ship.getLayoutY()>20){
                ship.setLayoutY(ship.getLayoutY()-4);
            }
        }

        if(!isUpKeyPressed && isDownKeyPressed){             //se solo la freccia in giù è pressata muoviamo la nave verso sotto
            if(ship.getLayoutY()<710){
                ship.setLayoutY(ship.getLayoutY()+4);
            }
        }

        if(isLeftKeyPressed && !isRightKeyPressed){         //se solo la freccia sinistra è pressata muoviamo la nave verso sinistra e ruotiamo la nave verso sinistra
            if (angle > -30){
                angle-=5;
            }
            ship.setRotate(angle);
            if(ship.getLayoutX()>0){
                ship.setLayoutX(ship.getLayoutX()-4);
            }
        }

        if(!isLeftKeyPressed && isRightKeyPressed){          //se solo la freccia destra è pressata muoviamo la nave verso sinistra e ruotiamo la nave verso sinistra
            if (angle < 30){
                angle+=5;
            }
            ship.setRotate(angle);
            if(ship.getLayoutX()<522){
                ship.setLayoutX(ship.getLayoutX()+4);
            }
        }

        if(!isLeftKeyPressed && !isRightKeyPressed){          //se la freccia sinistra e destra sono pressate facciamo rimanere la nave ferma
            if(angle<0){
                angle+=5;
            }else if(angle>0){
                angle-=5;
            }
            ship.setRotate(angle);
        }

        if(isLeftKeyPressed && isRightKeyPressed){             //se ne la freccia sinistra e ne la destra sono pressate facciamo rimanere la nave ferma
            if(angle<0){
                angle+=5;
            }else if(angle>0){
                angle-=5;
            }
            ship.setRotate(angle);
        }


    }

    private void removeLife(){                                  //metodo removeLife
        gamePane.getChildren().remove(playerLifes[playerLife]); //rimuoviamo dal gamePane la singola vita
        playerLife--;                                           //leviamo una vita dal contatore della vita
        if(playerLife < 0){                                     //se abbiamo perso tutte le vite
            gameOver.musicStart();                              //richiamiamo il metodo musicStart per far sentire l'effetto sonoro del gameOver
            score();                                            //salviamo il punteggio
            gameTimer.stop();                                   //stoppiamo l'AnimetionTimer
            gameStage.close();                                  //chiudiamo la finestra del gioco
            menuStage.show();                                   //rimostriamo la finestra del gioco
        }
    }

    private void addLife(){                                     //metodo addLife
        if (playerLife < 2) {                                   //se abbiamo meno di 3 vite
            life_plus.musicStart();                             //musica aggiunta vita
            playerLife++;                                       //aggiungiamo una vita al contatore della vita
            gamePane.getChildren().add(playerLifes[playerLife]);//aggiungiamo al gamePane nuovamente una immagine della vita
        }
    }

    private void score(){                                       //metodo score
        try {                                                   //proviamo a scrivere su un file csv il punteggio ottenuto nella partita
            File file = new File("src/model/resources/score.csv");
            FileWriter w = new FileWriter(file,true);
            BufferedWriter b = new BufferedWriter(w);
            Date d = new Date();
            DateFormat formatoData = DateFormat.getDateInstance(DateFormat.LONG, Locale.ITALY);
            String s = formatoData.format(d);
            b.write("\n"+points+"," + s);
            b.flush();
            b.close();
        }catch(IOException e){                                  //se ci sono errori li rileva
            e.printStackTrace();
        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2){      //metodo calculateDistance
        return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
    }

}
