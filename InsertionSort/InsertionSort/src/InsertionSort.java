import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

public class InsertionSort extends Application {

	private HBox pane = new HBox(5);
	private int[] list = { 30, 20, 3, 5, 4, 10, 100, 6, 8, 7 };
	private int[] savedState = list.clone();

	private int c = 1;
	private int j = 0;

	Label status = new Label();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("InsertionSort");

		Button btStep = new Button("Next Step");
		Button btReset = new Button("Reset");
		Button btMode = new Button("200ms Mode");

		HBox hbox = new HBox(5);
		hbox.getChildren().addAll(btStep, btMode, btReset);
		hbox.setAlignment(Pos.CENTER);

		pane.setAlignment(Pos.CENTER);

		setNumbers();

		BorderPane pane2 = new BorderPane();
		pane2.setCenter(pane);
		pane2.setTop(status);
		pane2.setBottom(hbox);

		Scene scene = new Scene(pane2, 400, 100);
		primaryStage.setScene(scene);
		primaryStage.show();

		btReset.setOnAction(e -> reset());
		btMode.setOnAction(e -> start());
		btStep.setOnAction(e -> nextStep());
	}

	// reset the list to the original order
	public void reset() {
		list = savedState.clone();
		c = 1;
		j = 0;
		status.setText("");
		pane.getChildren().clear();
		setNumbers();

	}

	// Next step in bubble sort algorithm
	public void nextStep() {
		int temp;
		if (c >= list.length) {
			status.setText("Array has been sorted");
			status.setFont(Font.font("Arial", FontWeight.BOLD, 13));
			status.setTextFill(Color.GREEN);

		} else {
			j = c; 
			if (j > 0 && list[j -1] > list[j]) {
						temp = list[j];
						list[j] = list[j-1];
						list[j-1] = temp;
						c--;
						pane.getChildren().clear();
						setNumbers();
			}
			
			else {
				c++;
				j--;
			}

		}
	}

	// Step automatically through array with bubble sort algorithm (speed 200ms)
	public void start() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (c < list.length) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							nextStep();
						}
					});
				}
			}
		}).start();

		if (c >= list.length) {
			status.setText("Array has been sorted");
			status.setFont(Font.font("Arial", FontWeight.BOLD, 13));
			status.setTextFill(Color.GREEN);
			nextStep();
		}

	}

	// Display the numbers in GUI
	public void setNumbers() {
		for (int i = 0; i < list.length; i++) {
			Label label = new Label(Integer.toString(list[i]));
			label.setFont(Font.font(20));
			pane.getChildren().add(label);
		}
	}

}
