package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ViewManager {                                  //classe ViewManager

    private static final int HEIGHT = 768;                  //altezza della finestra del menu
    private static final int WIDTH  = 1024;                 //larghezza della finestra del menu
    private final AnchorPane mainPane;                            //variabile che ci permette di ancorare immagini,testi ecc a video
    private final Scene mainScene;                                //variabile che ci permette di creare la scena iniziale del menu
    private final Stage mainStage;                                //variabile che ci permette di creare la finestra del menu di gioco

    private final static int MENU_BUTTONS_START_X = 100;    //coordinata x dei bottoni del menu
    private final static int MENU_BUTTONS_START_Y = 150;    //coordinata y dei bottoni del menu

    private SpaceSubScene creditsSubScene;            //variabile che ci permette di creare la sottoscena per il pulsante credits
    private SpaceSubScene helpSubScene;               //variabile che ci permette di creare la sottoscena per il pulsante help
    private SpaceSubScene shipChooserSubScene;        //variabile che ci permette di creare la sottoscena per la scelta delle navi
    private SpaceSubScene scoresSubScene;             //variabile che ci permette di creare la sottoscena per vedere la classifica del gioco
    private SpaceSubScene sceneToHide;                //variabile che ci permette di andare a cambiare sottoscena


    //stringhe di testo che andremo ad inserire nelle sottoscene helpSubScene e creditsSubScene
    private final static String textHelp="freccia su per salire.\n\nFreccia giu per scendere.\n\nFreccia sinistra per girare a sinistra.\n\nFreccia destra per andare a destra";
    private final static String textCredit="Progetto\nProgrammazione ad Oggetti\n\nAnno 2020/2021\n\nRoberto Tomasello & Alessandro Busà";

    //file csv che ci permettono di creare la classifica del gioco
    private static final File input = new File("src/model/resources/score.csv");     //file contenente la classifica non ordinata
    private static final File output = new File("src/model/resources/scoreOrd.csv");    //file contenente la classifica ordinata

    private static List<SpaceButton> menuButtons;                    //lista contenente tutti i bottoni del menu (array dinamici)
    private static List<ShipPicker> shipList;                              //lista contenente tutte le navi da scegliere  (array dinamici)

    private static SHIP choosenShip;                               //variabile che ci permette di capire quale nave è stata scelta

    //variabili Music con percorsi dei vari effetti sonori
    private static final Music soundtrack= new Music("src/model/resources/soundtrack.mp3");
    private static final Music clickButton= new Music("src/model/resources/clickButton.mp3");

    public ViewManager(){                                   //costruttore ViewManager()
        menuButtons = new ArrayList<>();                    //creazione lista bottoni menu
        mainPane  = new AnchorPane();                       //creazione variabile AnchorPane
        mainScene = new Scene(mainPane,WIDTH,HEIGHT);       //creazione scena principale
        mainStage = new Stage();                            //creazione finestra del menu
        mainStage.setScene(mainScene);                      //all'interno della finestra mettiano la scena principale
        mainStage.setTitle("Mission BusTom");               //settiamo il titolo della finestra di gioco
        soundtrack.musicStartLoop();                        //richiamiamo il metodo musicStartLoop() per far partire la soundtrack
        createSubScene();                                   //richiamiamo il metodo createSubScene() per far creare le sottoscene
        createButtons();                                    //richiamiamo il metodo createButtons() per far creare i bottoni
        createBackground();                                 //richiamiamo il metodo createBackground() per far creare lo sfondo
        createLogo();                                       //richiamiamo il metodo createLogo() per far creare il logo
    }

    private void createSubScene(){                          //metodo createSubScene().    Creazioni sottoscene
        createHelpSubScene();
        createScoreSubScene();
        createCreditsSubScene();
        createShipChooserSubScene();
    }

    private void createButtons(){                          //metodo createButtons
        createStartButton();                               //chiamata metodo createStartButton
        createScoresButton();                              //chiamata metodo createScoresButton
        createHelpButton();                                //chiamata metodo createHelpButton
        createCreditsButton();                             //chiamata metodo createCreditsButton
        createExitButton();                                //chiamata metodo createExitButton
    }

    private void createBackground(){                         //metodo createBackground
        Image backgroundImage = new Image("model/resources/deep_blue.png",256,256,false,true);    //variabile che contiene lo sfondo
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));  //aggiunta dello sfondo al mainPane
    }

    private void createLogo(){                               //metodo createLogo
        ImageView logo = new ImageView("model/resources/logo2.png");   //variabile che contiene il logo
        logo.setFitHeight(146.5);                            //settiamo l'altezza del logo
        logo.setFitWidth(366);                               //settiamo la larghezza del logo
        logo.setLayoutX(470);                                //settiamo la coordinata x del logo
        logo.setLayoutY(10);                                 //settiamo la coordinata y del logo
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {       //se passiamo il puntatore del mouse sul logo ci sarà un effetto di ombra
            @Override
            public void handle(MouseEvent Event) {
                logo.setEffect(new DropShadow());
            }
        });
        logo.setOnMouseExited(new EventHandler<MouseEvent>() {        //se non passiamo il puntatore del mouse sul logo ci sarà un effetto di ombra
            @Override
            public void handle(MouseEvent Event) {
                logo.setEffect(null);
            }
        });
        mainPane.getChildren().add(logo);                    //aggiunta del logo al mainPane
    }

    private void showSubScene(SpaceSubScene subScene){  //metodo showSubScene
        if(sceneToHide != null){
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide=subScene;
    }

    private void createHelpSubScene(){                      //metodo createHelpSubScene()
        helpSubScene = new SpaceSubScene();                 //creazione sottoscena
        mainPane.getChildren().add(helpSubScene);           //aggiunta della sottoscena al mainPane
        infoLabel2 Label = new infoLabel2(textHelp);        //creazione label con testo da mostrare
        Label.setLayoutX(110);                              //settiamo la coordinata x del label
        Label.setLayoutY(25);                               //settiamo la coordinata y del label
        helpSubScene.getPane().getChildren().add(Label);    //aggiungiamo alla sottoscena il label con il testo
    }

    private void createScoreSubScene(){                     //metodo createScoreSubScene()
        scoresSubScene = new SpaceSubScene();         //creazione sottoscena
        mainPane.getChildren().add(scoresSubScene);         //aggiunta della sottoscena al mainPane
        sortFile(input,output);                                         //richiamiamo il metodo sortFile() per andare ad ordinare il file csv non ordinato
        infoLabel2 Label = new infoLabel2(score(output));   //creazione label con classifica da mostrare
        Label.setLayoutX(110);                              //settiamo la coordinata x del label
        Label.setLayoutY(25);                               //settiamo la coordinata y del label
        scoresSubScene.getPane().getChildren().add(Label);  //aggiungiamo alla sottoscena il label con il testo
    }

    private void createCreditsSubScene(){     //metodo createCreditSubScene
        creditsSubScene = new SpaceSubScene();     //creazione sottoscena
        mainPane.getChildren().add(creditsSubScene);     //aggiunta della sottoscena al mainPane
        infoLabel2 Label = new infoLabel2(textCredit);   //creazione label con classifica da mostrare
        Label.setLayoutX(110);                           //settiamo la coordinata x del label
        Label.setLayoutY(25);                            //settiamo la coordinata y del label
        creditsSubScene.getPane().getChildren().add(Label);   //aggiungiamo alla sottoscena il label con il testo
    }

    private void createShipChooserSubScene(){            //metodo createShipChooserSubScene
        shipChooserSubScene = new SpaceSubScene(); //creazione sottoscena
        mainPane.getChildren().add(shipChooserSubScene); //aggiunta della sottoscena al mainPane
        infoLabel chooseShipLabel = new infoLabel("SCEGLI  LA  NAVE");  //creazione chooseShiplabel con classifica da mostrare
        chooseShipLabel.setLayoutX(110);                 //settiamo la coordinata x del label
        chooseShipLabel.setLayoutY(25);                  //settiamo la coordinata y del label
        shipChooserSubScene.getPane().getChildren().add(chooseShipLabel);       //aggiungiamo alla sottoscena il label con il testo
        shipChooserSubScene.getPane().getChildren().add(createShipToChoosen()); //aggiungiamo alla sottoscena le navi da scegliere
        shipChooserSubScene.getPane().getChildren().add(createButtonToStart()); //aggiungiamo alla sottoscena il bottone per cominciare a giocare
    }

    private void createStartButton(){                      //metodo createStartButton
        SpaceButton startButton = new SpaceButton("GIOCA");    //creazione startButton
        addMenuButton(startButton);                        //chiamata metodo addMenuButton

        startButton.setOnAction(new EventHandler<ActionEvent>() {    //se clicchiamo sul bottone ci mostrerà la sottoscena corrispondente
            @Override
            public void handle(ActionEvent Event) {
                clickButton.musicStart();                  //facciamo partire l'effetto del bottone cliccato
                showSubScene(shipChooserSubScene);
            }
        });
    }

    private void createScoresButton(){                     //metodo createScoreButton
        SpaceButton scoresButton = new SpaceButton("CLASSIFICA");   //creazione scoresButton
        addMenuButton(scoresButton);                       //chiamata metodo addMenuButton

        scoresButton.setOnAction(new EventHandler<ActionEvent>() {   //se clicchiamo sul bottone ci mostrerà la sottoscena corrispondente
            @Override
            public void handle(ActionEvent Event) {
                clickButton.musicStart();                  //facciamo partire l'effetto del bottone cliccato
                showSubScene(scoresSubScene);
            }
        });
    }

    private void createHelpButton(){                       //metodo createHelpButton
        SpaceButton helpButton = new SpaceButton("HELP");   //creazione HelpButton
        addMenuButton(helpButton);                         //chiamata metodo addMenuButton

        helpButton.setOnAction(new EventHandler<ActionEvent>() {    //se clicchiamo sul bottone ci mostrerà la sottoscena corrispondente
            @Override
            public void handle(ActionEvent Event) {
                clickButton.musicStart();                  //facciamo partire l'effetto del bottone cliccato
                showSubScene(helpSubScene);
            }
        });
    }

    private void createCreditsButton(){                    //metodo createCreditsButton
        SpaceButton creditsButton = new SpaceButton("CREDITS");    //creazione creditsButton
        addMenuButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {    //se clicchiamo sul bottone ci mostrerà la sottoscena corrispondente
            @Override
            public void handle(ActionEvent Event) {
                clickButton.musicStart();                  //facciamo partire l'effetto del bottone cliccato
                showSubScene(creditsSubScene);
            }
        });
    }

    private void createExitButton(){                        //metodo createExitButton
        SpaceButton exitButton = new SpaceButton("EXIT");    //creazione exitButton
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {    //se clicchiamo sul bottone ci chiuderà il gioco
            @Override
            public void handle(ActionEvent Event) {
                clickButton.musicStart();                   //facciamo partire l'effetto del bottone cliccato
                mainStage.close();
            }
        });
    }

    private SpaceButton createButtonToStart(){      //metodo createButtonToStart
        SpaceButton startButton = new SpaceButton("GIOCA");   //creazione bottone startButton
        startButton.setLayoutX(350);                      //settiamo la coordinata x del bottone
        startButton.setLayoutY(300);                      //settiamo la coordinata x del bottone

        startButton.setOnAction(new EventHandler<ActionEvent>() {   //se clicchiamo sul bottone e la nave sarà stata scelta comincerà il gioco
            @Override
            public void handle(ActionEvent Event) {
                clickButton.musicStart();                 //facciamo partire l'effetto del bottone cliccato
                if (choosenShip != null){
                    GameViewManager gameManager= new GameViewManager();
                    gameManager.createNewGame(mainStage,choosenShip);
                }
            }
        });
        return startButton;
    }

    private HBox createShipToChoosen(){                  //metodo createShipToChoosen
        HBox box = new HBox();                           //creazione variabile Hbox
        box.setSpacing(60);                              //settiamo lo spazio del Hbox
        shipList=new ArrayList<>();                      //creazione lista contenente le navi da scegliere
        for(SHIP ship : SHIP.values()){                  //creazione lista navi da scegliere
            ShipPicker shipToPick = new ShipPicker(ship);
            shipList.add(shipToPick);
            box.getChildren().add(shipToPick);
            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {   //se clicchiamo una nave con il mouse ce la sceglierà
                @Override
                public void handle(MouseEvent Event) {
                    clickButton.musicStart();            //facciamo partire l'effetto del bottone cliccato
                    for(ShipPicker ship : shipList){
                        ship.setCircleChoosen(false);
                    }
                    shipToPick.setCircleChoosen(true);
                    choosenShip=shipToPick.getShip();
                }
            });
        }
        box.setLayoutX(64);
        box.setLayoutY(100);
        return box;                                       //ritorno box
    }

    private void addMenuButton(SpaceButton button){  //metodo addMenuButton
        button.setLayoutX(MENU_BUTTONS_START_X);           //settiamo la coordinata x del bottone
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);    //settiamo la coordinata y del bottone
        menuButtons.add(button);                           //aggiungiamo il bottone alla lista menuButtuns
        mainPane.getChildren().add(button);                //aggiunta del bottone al mainPane
    }

    private void sortFile(File input,File output){                                //metodo sortFile()
        List<csv> CSV = sort(input);                        //lista csv contenente la classifa ordinata
        write(output,CSV);                                  //scriviamo questa classifica su un altro file
    }

    private void write(File output, List<csv> CSV) {       //metodo write
        try {                                              //andiamo a scrivere sul file la classifica ordinata
            Path path = Paths.get(output.toURI());
            List<String> listCSV = new ArrayList<>(CSV.size());
            for (csv s : CSV) {
                listCSV.add(s.toString());
            }
            Files.write(path, listCSV);
        }catch(IOException e){                             //se ci sono errori li rivela
            e.printStackTrace();
        }
    }

    private List<csv> sort(File f){                        //metodo sort
        try {                                              //andiamo ad ordinare la lista csv
            Path path = Paths.get(f.toURI());              //prendiamo il percorso del file
            List<String> allLines = Files.readAllLines(path);    //creiamo una lista di stringhe contenente tutte le righe del file
            List<csv> CSV = new ArrayList<>(allLines.size());    //creiamo una lista csv
            for (int i = 0; i < allLines.size(); i++) {          //andiamo a riempire la lista CSV
                String line = allLines.get(i);
                String[] columns = line.split(",");
                csv s = new csv(Integer.parseInt(columns[0]), columns[1]);
                CSV.add(s);
            }
            Collections.sort(CSV);                        //andiamo ad ordinare la lista CSV
            return CSV;                                   //ritorniamo la lista CSV
        }catch(IOException e){                            //se ci sono errori li rivela
            e.printStackTrace();
        }
        return null;
    }

    private String score(File f){                         //metodo score
        try {                                             //andiamo a creare il testo contenente la classifica ordinata
            Path path = Paths.get(f.toURI());             //prendiamo il percorso del file contenente la classifica ordinata
            List<String> allLines = Files.readAllLines(path);   //creiamo una lista di stringhe contenente tutte le righe del file
            String text="";                               //creazione stringa text
            for(int i = 0; i < 5; i++) {                  //andiamo a inserire nella stringa i primi 5 classificati
                String line = allLines.get(i);
                String[] columns = line.split(",");
                text+= columns[0]+" punti : "+columns[1]+"\n\n";
            }
            return text;                                  //ritorniamo text
        }catch(IOException e){                            //se ci sono errori li rivela
            e.printStackTrace();
        }
        return null;
    }

    public Stage getMainStage(){                           //metodo getMainStage

        return mainStage;                                  //ritorno mainStage

    }

}
