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

public class QuickSort extends Application {

	private HBox pane = new HBox(5);
	private int[] list = {5, 2, 8,78,99,55, 100, 3, 7, 10, 1};
	private int[] savedState = list.clone();

	private int low = 0;
	private int high = list.length-1;
	private int i = 0;
	private int j = list.length-1;
	int middle = low + (high - low) / 2;
	int pivot = list[middle];

	Label status = new Label();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Quick Sort");

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
		low = 0;
		high = list.length-1;
		j = 0;
		i = 0;
		status.setText("");
		pane.getChildren().clear();
		setNumbers();
	}

	// Next step in bubble sort algorithm
	public void nextStep() {
		if (list == null || list.length == 0)
			return;
 
		if (low >= high)
			return;

		// make left < pivot and right > pivot
		if (i <= j) {
			while (list[i] < pivot) {
				i++;
			}
 
			while (list[j] > pivot) {
				j--;
			}
 
			if (i <= j) {
				int temp = list[i];
				list[i] = list[j];
				list[j] = temp;
				i++;
				j--;
				pane.getChildren().clear();
				setNumbers();
			}	
			

			if (low < j)
				quickSort(list, low, j);
			pane.getChildren().clear();
			setNumbers();
	 
			if (high > i)
				quickSort(list, i, high);
			pane.getChildren().clear();
			setNumbers();

		}
	}
	
	public void quickSort(int[] list, int low, int high) {
		if (this.list == null || this.list.length == 0)
			return;
 
		if (low >= high)
			return;
 
		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = this.list[middle];
		this.i = low;
		this.j = high;
		
		// make left < pivot and right > pivot
		if (i <= j) {
			while (this.list[i] < pivot) {
				i++;
			}
 
			while (this.list[j] > pivot) {
				j--;
			}
 
			if (i <= j) {
				int temp = this.list[i];
				this.list[i] = this.list[j];
				this.list[j] = temp;
				i++;
				j--;
				pane.getChildren().clear();
				setNumbers();
			}	
	
		}
	}

	// Step automatically through array with bubble sort algorithm (speed 200ms)
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0 ; i < list.length ; i++) {
					try {
						Thread.sleep(200);
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
		
	}

	// Display the numbers in GUI
	public void setNumbers() {
		for (int i = 0; i < list.length; i++) {
			Label label = new Label(Integer.toString(list[i]));
			label.setFont(Font.font(20));
			pane.getChildren().add(label);
			
			/*
			if (i == j) {
				label.setStyle("-fx-background-color:yellow");
			}
			*/
		}
	}

}
