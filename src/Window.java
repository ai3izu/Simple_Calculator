import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


public class Window extends Application {
    static final String[][] BUTTONS = {
            {"On", "Off", "X", "C"},
            {"1/x", "x^2", "sqrt", "/"},
            {"7", "8", "9", "*"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"},
            {"+/-", "0", ".", "="}
    };
    static final TextField CALCULATOR_DISPLAY = new TextField();
    private final CalculatorController calculatorController = new CalculatorController();
    private boolean isCalculatorOn = true;

    private GridPane gridPane;

    @Override
    public void start(Stage primaryStage) throws Exception {

        calculatorDisplay();
        gridPane = createButtonGrid();

        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(CALCULATOR_DISPLAY, gridPane);
        vBox.setStyle("-fx-background-color: #d3d3d3;");


        Scene scene = new Scene(vBox, 400, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    private void calculatorDisplay() {
        CALCULATOR_DISPLAY.setMinSize(100, 80);
        CALCULATOR_DISPLAY.setBorder(new Border(new BorderStroke(Paint.valueOf("#000000"), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1.4), null)));
        CALCULATOR_DISPLAY.setStyle("-fx-background-color: #ffffff; -fx-font-size: 20px; -fx-text-fill: #000000;");
        CALCULATOR_DISPLAY.setAlignment(Pos.CENTER_RIGHT);
        CALCULATOR_DISPLAY.setMaxWidth(350);

        CALCULATOR_DISPLAY.setOnKeyPressed(Event::consume);
        CALCULATOR_DISPLAY.setOnKeyTyped(Event::consume);
    }

    private GridPane createButtonGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(6);
        gridPane.setHgap(4);
        gridPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < BUTTONS.length; i++) {
            for (int j = 0; j < BUTTONS[i].length; j++) {
                Button button = createButton(BUTTONS[i][j]);
                gridPane.add(button, j, i);

            }
        }
        return gridPane;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMinSize(80, 80);
        button.setStyle("-fx-background-color: #a1a1a1; -fx-font-size: 25px; -fx-text-fill: #000000;");
        button.setBorder(new Border(new BorderStroke(Paint.valueOf("#000000"), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1.4), null)));

        if (text.matches("[0-9]")) {
            button.setOnAction(event -> CALCULATOR_DISPLAY.setText(calculatorController.processNumberInput(CALCULATOR_DISPLAY.getText(), text)));
        } else if (text.equals("=")) {
            button.setOnAction(event -> CALCULATOR_DISPLAY.setText(calculatorController.executeCalculation(CALCULATOR_DISPLAY.getText())));
        } else if (text.equals("C")) {
            button.setOnAction(event -> {
                calculatorController.reset();
                CALCULATOR_DISPLAY.clear();
            });
        } else if (text.equals("Off")) {
            button.setOnAction(event -> toggle(false));
        } else if (text.equals("On")) {
            button.setOnAction(event -> toggle(true));
        } else if (text.equals("X")) {
            button.setOnAction(event -> System.exit(0));
        } else {
            button.setOnAction(event -> CALCULATOR_DISPLAY.setText(calculatorController.processOperatorInput(CALCULATOR_DISPLAY.getText(), text)));
        }
        return button;
    }

    private void toggle(boolean isOn) {
        isCalculatorOn = isOn;
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (!button.getText().equals("On") && !button.getText().equals("Off")) {
                    button.setDisable(!isOn);
                }
            }
        }
    }
}
