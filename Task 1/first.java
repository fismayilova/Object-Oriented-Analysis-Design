import java.util.ArrayList;

/*
 * Tester class 
 */
public class first implements MyConstants {
	static ArrayList<Shape> myList = new ArrayList<Shape>();

	public static void main(String[] args) {
		/*
		 * I decided to use loop and ArrayList just to create more instances of
		 * the shapes and add into the list in parallel
		 */
		for (int i = 1; i <= times; i++) {
			/*
			 * instantiating the shapes and adding them into the arraylist
			 */
			myList.add(new Rectangle(S * i, H * i));
			myList.add(new Triangle(P * i, H * i));
			myList.add(new Square(side * i));
		}
		/*
		 * Composition
		 */
		Notebook notebook = new Notebook(myList);
		System.out.println("Number of shapes in the notebook: " + notebook.countNumOfShapes(myList));

		displayArea(myList); // Method for displaying Areas of my shapes
	}

	/*
	 * Method for displaying Areas of my shapes
	 */
	public static void displayArea(ArrayList<Shape> myArList) {
		for (Shape shape : myArList) {
			System.out.print("Area of ");
			if (shape.getClass() == Triangle.class) {
				System.out.println("Triangle is: " + shape.calculateSurfaceArea(shape.getWidth(), shape.getHeight()));
			} else if ((shape.getClass() == Rectangle.class)) {
				System.out.println("Rectangle is: " + shape.calculateSurfaceArea(shape.getWidth()));
			} else if ((shape.getClass() == Square.class)) {
				System.out.println("Square is: " + shape.calculateSurfaceArea(shape.getWidth()));
			}
		}
	}
}

/*
 * Interface for Constants which are used for the widths and hights of my
 * shapes. They all have final keyword
 */
interface MyConstants {
	int side = 2;
	int times = 3;
	int P = 3;
	int S = 2;
	int H = 4;
}

/*
 * The abstract parent class
 */
abstract class Shape {
	private double width; // Private class variables for encapsulation
	private double height;

	/*
	 * Three constructors:
	 */
	public Shape(double Width, double Height) {
		setWidth(Width);
		setHeight(Height);
	}

	public Shape(double Side) {
		setWidth(Side);
		setHeight(Side);
	}

	/*
	 * Method which is overloaded
	 */
	public double calculateSurfaceArea(double Width, double Height) {
		return (getWidth() * getHeight());
	}

	/*
	 * Method which is overloaded
	 */
	public double calculateSurfaceArea(double Width) {
		return (getWidth() * getWidth());
	}

	/*
	 * Getters and Setters for encapsulation
	 */
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}

/*
 * Child class Rectangle inherits from the parent abstract class Shape
 */
class Rectangle extends Shape {
	/*
	 * Construct for rectangle
	 */
	public Rectangle(double Width, double Height) {
		super(Width, Height);
	}
}

/*
 * Child class Square inherits from the parent abstract class Shape
 */
class Square extends Shape {
	/*
	 * Construct for Square
	 */
	Square(double Side) {
		super(Side);
	}
}

/*
 * Child class Triangle inherits from the parent abstract class Shape
 */
class Triangle extends Shape {
	public Triangle(double Base, double Height) {
		super(Base, Height);
	}

	/*
	 * Method Overridden
	 * 
	 * @see Shape#calculateSurfaceArea(double, double)
	 */
	@Override
	public double calculateSurfaceArea(double Base, double Height) {
		return (super.calculateSurfaceArea(Base, Height) / 2);
	}
}

/*
 * Final Class used for Composition
 */
final class Notebook {
	public ArrayList<Shape> draw = new ArrayList<Shape>();

	public Notebook(ArrayList<Shape> draw) {
		this.draw = draw;
	}

	public int countNumOfShapes(ArrayList<Shape> draw) {
		return draw.size();
	}
}

/*
 * 1) I achieved abstraction by using Abstract class, in my case it is my Shape
 * class. 2) For encapsulation i made my class variables in the Shape class,
 * private, and used getters and setter, so those variables are accessed only by
 * using getters and setters. 3) My 3 child classes, namely Triangle,Square and
 * Rectangle inherit features of the Shape class and use its methods. 4) My
 * method "calculateSurfaceArea" is overridden in the child class Triangle, and
 * overloaded in the parent class. 5&6) There are some static and final class
 * variables. 7) I created a Notebook class which has no relation with the main
 * Shape class and for this task i used composition (The shapes are drawn inside
 * the notebook) 8)I have: 3 main classes(1 parent, 2 child), 1 tester class, 1
 * class used for composition and 1 class for saving my constants in it. About
 * cohesion, you can see that non of my classes does any extra tasks, each class
 * is focused on one single task which it was created for, thus the program has
 * high cohesion. In case of coupling non of the child classes depend on each
 * other they all inherit from the parent class. And the tester class
 * instantiates the child classes. In general the relationship between classes
 * is not very trivial, except for the inheritance case,thus the program has
 * lose coupling. 9) I declared my notebook class as a final class as it is not
 * inherited by any other class.
 */
