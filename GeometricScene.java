import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 
 * @author Cordell Bonnieux
 *
 */
public class GeometricScene extends Application {
	private final int HEIGHT = 800;
	private final int WIDTH = 1200;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		// add program title
		stage.setTitle("Cordell's Geometric Scene");
		
		//create pane and add scene components
		Pane root = new Pane();
		root.getChildren().addAll(new Background(500, 300));
		
		// add root pane to a scene
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		
		// set the stage with the scene
		stage.setScene(scene);
		
		// display the scene on the stage
		stage.show();
		
	}
	
	private class Background extends Group{
		private Rectangle backdrop;
		private Polygon pyramidLeft;
		private Polygon pyramidRight;
		private Polygon foregroundLeft;
		private Polygon foregroundRight;
		
		public Background(double px, double py) {
			this.backdrop = new Rectangle((double)WIDTH, (double)HEIGHT);
			this.backdrop.setFill(Color.DARKBLUE);
			this.backdrop.setY(0);
			this.backdrop.setX(0);
			
			this.pyramidLeft = new Polygon();
			this.pyramidLeft.setFill(Color.DEEPPINK);
			this.pyramidLeft.getPoints().addAll(new Double[] {
					px, py,
					px+75, (double)HEIGHT,
					px-400, (double)HEIGHT,
			});
			
			this.pyramidRight = new Polygon();
			this.pyramidRight.setFill(Color.MEDIUMPURPLE);
			this.pyramidRight.getPoints().addAll(new Double[] {
					px, py,
					px+75, (double)HEIGHT,
					px+400, (double)HEIGHT,
			});
			
			this.foregroundLeft = new Polygon();
			this.foregroundLeft.setFill(Color.TAN);
			this.foregroundLeft.getPoints().addAll(new Double[] {
					0.00, 600.00,
					0.00, (double)HEIGHT,
					400.00, (double)HEIGHT,
			});

			this.foregroundRight = new Polygon();
			this.foregroundRight.setFill(Color.TAN);
			this.foregroundRight.getPoints().addAll(new Double[] {
					400.00, (double)HEIGHT,
					(double)WIDTH, 500.00,
					(double)WIDTH, (double)HEIGHT,
			});
			
			this.getChildren().addAll(backdrop, pyramidLeft, pyramidRight, foregroundLeft, foregroundRight);
		}
		
		
	}

}
