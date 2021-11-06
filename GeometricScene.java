import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Gemometric Scene
 * A simple gemotric scene with scale-able randomly placed kitties.
 * @author Cordell Bonnieux
 * 
 */

//FXML
// used to refactor javafx!


public class GeometricScene extends Application {
	private final int HEIGHT = 800;
	private final int WIDTH = 1200;
	// top controls
	private CheckBox pyramidCheckBox;
	private CheckBox moonCheckBox;
	private CheckBox starsCheckBox;
	// bottom right controls
	private RadioButton tall;
	private RadioButton smol;
	private RadioButton thicc;
	private RadioButton hungry;
	// bottom right control groups
	private ToggleGroup kittyWidth;
	private ToggleGroup kittyHeight;
	// Keep track of spawned kitties
	private ArrayList<Kitty> kittyTracker = new ArrayList<Kitty>();
	// create a kitty temp variables
	private boolean isTall;
	private boolean isThicc;
	
	/**
	 * Launch Application
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	/**
	 * Start
	 * JavaFX program logic
	 * @param stage
	 */
	@Override
	public void start(Stage stage) throws Exception {
		

		// 1. Create a border pane
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		
		HBox bottomArea = new HBox(get_bgControls(), get_kittyControls());
		
		// add some components to display
		Ground foreground = new Ground();
		
		// create random coordinates for kitty
		Kitty sickie = new Kitty(Color.rgb(139, 197, 82), Color.rgb(238, 111, 158), randomX(), randomY(foreground), randomSize(), randomSize());
		Kitty moro = new Kitty(Color.BLACK, Color.YELLOW, randomX(), randomY(foreground), randomSize(), randomSize());
		Kitty sylvester = new Kitty(Color.DARKGREY, Color.RED, randomX(), randomY(foreground), randomSize(), randomSize());
		
		kittyTracker.add(sickie);
		kittyTracker.add(moro);
		kittyTracker.add(sylvester);
		
		// add components to pane
		Pane center = new Pane();
		center.getChildren().addAll(new Background(300, 300), foreground, sickie, moro, sylvester); //
		center.setPrefHeight(HEIGHT);
		center.setPrefWidth(WIDTH);
		Rectangle clip = new Rectangle(0,0,WIDTH, HEIGHT);
		center.setClip(clip);
		center.setPadding(new Insets(10));
		
		// add components to border pane
		root.setCenter(center);
		root.setRight(get_currentKitties());
		root.setBottom(bottomArea);
		
		// wierd but ok, align the bottom
		// cannot align double nested items
		bottomArea.setAlignment(Pos.CENTER_RIGHT);
		
		
		Scene scene = new Scene(root);
		stage.setTitle("Cordell's Geometric Scene");
		stage.setScene(scene);
		stage.show();
		
	}
	
	private VBox get_currentKitties() {
		Text heading = new Text("Current Kitties");
		VBox kittyHolder = new VBox();
		for (int i = 0; i < kittyTracker.size(); i++) {
			CheckBox visible = new CheckBox("visible");
			visible.setSelected(true);
			Button delete = new Button("delete");
			HBox info = new HBox(kittyTracker.get(i).getCopyUI(), visible, delete); // add visibility and deletion
			kittyHolder.getChildren().add(info);
		}
		
		VBox container = new VBox(heading, kittyHolder);
		return container;
	}
	
	/**
	 * Background Controls
	 * Checkboxes to control background elements via GUI
	 * @return HBox containing control UI elements
	 */
	private HBox get_bgControls() {
		Text bgText = new Text("Toggle Background Elements:");
		pyramidCheckBox = new CheckBox("Pyramid");
		moonCheckBox = new CheckBox("Moon");
		starsCheckBox = new CheckBox("Stars");
		HBox bgControls = new HBox(bgText, pyramidCheckBox, moonCheckBox, starsCheckBox);
		return bgControls;
	}
	
	/**
	 * Kitty Maker Controls
	 * Controls for instantiating new kitties
	 * @return
	 */
	private VBox get_kittyControls() {
		kittyHeight = new ToggleGroup();
		tall = new RadioButton("tall");
		smol = new RadioButton("smol");
		smol.setToggleGroup(kittyHeight);
		tall.setToggleGroup(kittyHeight);
		
		kittyWidth = new ToggleGroup();
		thicc = new RadioButton("thicc");
		hungry = new RadioButton("hungry");
		thicc.setToggleGroup(kittyWidth);
		hungry.setToggleGroup(kittyWidth);
		
		VBox kittyMakerL = new VBox(tall, smol);
		VBox kittyMakerR = new VBox(thicc, hungry);

		HBox top = new HBox(kittyMakerL, kittyMakerR);
		Button createBtn = new Button("Create A Kitty");
		VBox container = new VBox(top,createBtn);
		return container;
	}
	
	/**
	 * RandomX
	 * @return double - a random valid X coordinate
	 */
	private double randomX() {
		double num = 50 + Math.random() * ((WIDTH-75) - 50 + 1);
		for (int i = 0; i < kittyTracker.size(); i++) {
			double loc = kittyTracker.get(i).getX();
			if (num == loc) {
				return randomX();
			} else if ((num - loc) <= 50.00 || (num - loc) >= -50.00) {
				return randomX();
			} else if ((loc - num) <= 50.00 || (loc - num) >= -50.00) {
				return randomX();
			}
		}
		return num;
	}
	
	/**
	 * RandomY
	 * @param g - Ground object
	 * @return double - a random valid Y coordinate
	 */
	private double randomY(Ground g) {
		double num = g.getHeight() + Math.random() * ((HEIGHT-75) - g.getHeight() + 1);
		for (int i = 0; i < kittyTracker.size(); i++) {
			double loc = kittyTracker.get(i).getY();
			if (num == loc) {
				return randomY(g);
			} else if ((num - loc) <= 50.00 || (num - loc) >= -50.00) {
				return randomX();
			} else if ((loc - num) <= 50.00 || (loc - num) >= -50.00) {
				return randomX();
			}
		}
		return num;
	}
	
	/**
	 * Random Size
	 * @return double - a random valid size scale
	 */
	private int randomSize() {
		return (int)(0.5 + Math.random() * (3 - 0.5 + 1));
	}
	
	private class kittyChoices implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			
		}
	}
	
	private class kittyCreator implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			
		}
	}
	
	/**
	 * Background
	 * Used to create background group of shapes
	 */
	private class Background extends Group{
		private Rectangle backdrop;
		private Ellipse moon;
		private Polygon pyramidLeft;
		private Polygon pyramidRight;
		 
		/**
		 * Class Constructor
		 * @param px double - Y-intersect of the top triangle points
		 * @param py double - X-intercept of the top triangle points
		 */
		public Background(double px, double py) {
			backdrop = new Rectangle((double)WIDTH, (double)HEIGHT);
			backdrop.setFill(Color.rgb(0, 20, 64));
			backdrop.setY(0);
			backdrop.setX(0);
			
			moon = new Ellipse((double)WIDTH-250, 250.00, 125.00, 125.00);
			moon.setFill(Color.ORANGE);
			
			pyramidLeft = new Polygon();
			pyramidLeft.setStroke(Color.rgb(19, 82, 171));
			pyramidLeft.setStrokeWidth(2);
			pyramidLeft.setFill(Color.rgb(150, 111, 214));
			pyramidLeft.getPoints().addAll(new Double[] {
					px, py,
					px+75, (double)HEIGHT,
					px-400, (double)HEIGHT,
			});
			
			pyramidRight = new Polygon();
			pyramidRight.setStroke(Color.rgb(19, 82, 171));
			pyramidRight.setStrokeWidth(2);
			pyramidRight.setFill(Color.rgb(183, 165, 217));
			pyramidRight.getPoints().addAll(new Double[] {
					px, py,
					px+75, (double)HEIGHT,
					px+400, (double)HEIGHT,
			});
			
			getChildren().addAll(backdrop, moon, pyramidLeft, pyramidRight);
		}
	}
	
	/**
	 * Ground
	 * Used to create a foreground
	 */
	private class Ground extends Group {
		private Polygon groundLeft;
		private Polygon groundRight;
		private final double heightLeft = 600.00;
		private final double heightRight = 500.00;
		
		/**
		 * Ground
		 * Class Constructor
		 */
		public Ground() {
			groundLeft = new Polygon();
			groundLeft.setFill(Color.rgb(255, 243, 222));
			groundLeft.getPoints().addAll(new Double[] {
					0.00, heightLeft,
					0.00, (double)HEIGHT,
					400.00, (double)HEIGHT,
			});

			groundRight = new Polygon();
			groundRight.setFill(Color.rgb(255, 243, 222));
			groundRight.getPoints().addAll(new Double[] {
					00.00, (double)HEIGHT,
					(double)WIDTH, heightRight,
					(double)WIDTH, (double)HEIGHT,
			});
			
			getChildren().addAll(groundLeft, groundRight);
		}
		
		public double getHeight() {
			return (this.heightLeft > this.heightRight) ? this.heightRight : this.heightLeft;
		}
	}
	
	/**
	 * Kitty
	 * Used to create foreground objects
	 * @author cordell
	 *
	 */
	private class Kitty extends Group {
		private Ellipse paw1;
		private Ellipse paw3;
		private Ellipse tail;
		private Ellipse body;
		private Ellipse head;
		private Ellipse paw2;
		private Ellipse paw4;
		private Ellipse eyeLeft;
		private Ellipse eyeRight;
		private Polygon earLeft;
		private Polygon earRight;
		private Line innerEyeRight;
		private Line innerEyeLeft;
		// Store User Preferences
		private Color colorOne;
		private Color colorTwo;
		private double posX;
		private double posY;
		private double sizeX;
		private double sizeY;

		/**
		 * Kitty
		 * Class Constructor
		 * @param mainColor Color - main color
		 * @param eyeColor Color - eye color
		 * @param centerX double - center X position for the kitty
		 * @param centerY double - center Y position for the kitty
		 * @param scale int - scale of the kitty 
		 */
		public Kitty(Color mainColor, Color eyeColor, double centerX, double centerY, double scaleX, double scaleY) {
			colorOne = mainColor;
			colorTwo = eyeColor;
			posX = centerX;
			posY = centerY;
			sizeX = scaleX;
			sizeY = scaleY;
			
			double radiusX = 90 * scaleX;
			double radiusY = 40 * scaleY;
			double[] range = new double[] {centerX-radiusX, centerX+radiusX, centerY-radiusY, centerY+radiusY};
			
			body = new Ellipse(centerX, centerY, radiusX, radiusY);
			paw1 = new Ellipse(range[0] + radiusX/4, range[3] - radiusY/4, scaleX*15, scaleY*10);
			paw3 = new Ellipse(range[1] - radiusX, range[3] - radiusY/10, scaleX*15, scaleY*10);
			tail = new Ellipse(range[1] - radiusX/8, range[2], scaleX*12, scaleY*52);
			head = new Ellipse(range[0] + radiusX/3, range[2] + radiusY/2, scaleX*40, scaleY*30);
			paw2 = new Ellipse(range[0] + radiusX/1.75, range[3] - radiusY/8, scaleX*15, scaleY*10);
			paw4 = new Ellipse(range[1] - radiusX/3, range[3] - radiusY/6, scaleX*15, scaleY*10);
			eyeLeft = new Ellipse(range[0] + radiusX/8, range[2] + radiusY/2, scaleX*15, scaleY*10);
			eyeRight = new Ellipse(range[0] + radiusX/2, range[2] + radiusY/2, scaleX*15, scaleY*10);
			innerEyeLeft = new Line(range[0] + radiusX/8, range[2] + radiusY/3.5, range[0] + radiusX/8, range[2] + radiusY/1.5);
			innerEyeRight = new Line(range[0] + radiusX/2, range[2] + radiusY/3.5, range[0] + radiusX/2, range[2] + radiusY/1.5);
			earLeft = new Polygon();
			earRight = new Polygon();
			
			innerEyeRight.setStrokeWidth(scaleX*4);
			innerEyeLeft.setStrokeWidth(scaleX*4);
			
			earLeft.getPoints().addAll(new Double[] {
					centerX-(105*scaleX), centerY-(58*scaleY),
					centerX-(75*scaleX), centerY-(48*scaleY),
					centerX-(95*scaleX), centerY-(28*scaleY)
			});
			
			earRight.getPoints().addAll(new Double[] {
					centerX-(15*scaleX), centerY-(58*scaleY),
					centerX-(45*scaleX), centerY-(48*scaleY),
					centerX-(20*scaleX), centerY-(28*scaleY)
			});
			
			paw1.setFill(mainColor);
			paw3.setFill(mainColor);
			tail.setFill(mainColor);
			body.setFill(mainColor);
			head.setFill(mainColor);
			paw2.setFill(mainColor);
			paw4.setFill(mainColor);
			earLeft.setFill(mainColor);
			earRight.setFill(mainColor);
			eyeLeft.setFill(eyeColor);
			eyeRight.setFill(eyeColor);
			
			getChildren().addAll(paw1,paw3,tail,body,head,paw2,paw4,earLeft,earRight,eyeLeft,eyeRight,innerEyeLeft,innerEyeRight);			
		}
		
		public Kitty getCopyUI() {
			Kitty copy = new Kitty(colorOne, colorTwo, posX, posY, 0.5, 0.5);
			return copy;
		}
		
		public double getX() {
			return posX;
		}
		
		public double getY() {
			return posY;
		}
	}
}
