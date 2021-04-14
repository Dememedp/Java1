package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Main extends Application {
    private Group windowRoot;
    private Scene windowScene;

    private Timeline displayTimeline = null;

    ArrayList<RadioButton> radio1 = new ArrayList<>();

    public void TimeFunction() {
        if (displayTimeline == null) {
            displayTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
                private int i = 0;
                private int j = radio1.size() - 1;
                boolean bool = true;

                @Override
                public void handle(ActionEvent event) {
                    if (i == radio1.size() / 2) {
                        i = 0;
                        j = radio1.size() - 1;
                    }
                    if (bool) {
                        radio1.get(i).setSelected(true);
                        i++;
                        bool = false;
                    } else {
                        radio1.get(j).setSelected(true);
                        j--;
                        bool = true;
                    }
                }
            }));

            displayTimeline.setCycleCount(Animation.INDEFINITE);
            displayTimeline.play();
        } else {
            displayTimeline.play();
        }
    }

    private void TimeStop(){
        if (displayTimeline!=null){
            displayTimeline.pause();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        windowRoot = new Group();
        windowScene = new Scene(windowRoot, 300, 275);
        TextField textField = new TextField();
        Button btn1 = new Button("Button1");
        windowRoot.getChildren().add(btn1);
        windowRoot.getChildren().add(textField);
        btn1.setTranslateY(30);
        btn1.setOnAction(event->{
            String str = textField.getText();
            int amount = Integer.parseInt(str);
            int distance = 60;
            ToggleGroup group = new ToggleGroup();
            for (int i=1; i<=amount; i++) {
                RadioButton radio12 = new RadioButton(String.valueOf(i));
                radio1.add(radio12);
                radio12.setToggleGroup(group);
                windowRoot.getChildren().add(radio12);
                radio12.setTranslateY(distance);
                distance+=20;
            }
        });
        Button btn2 = new Button("Button2");
        windowRoot.getChildren().add(btn2);
        btn2.setTranslateY(30);
        btn2.setTranslateX(70);

        btn2.setOnAction(event -> TimeFunction());

        Button btn3 = new Button("Button3");
        btn3.setOnAction(event -> TimeStop());
        windowRoot.getChildren().add(btn3);
        btn3.setTranslateY(30);
        btn3.setTranslateX(150);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(windowScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
