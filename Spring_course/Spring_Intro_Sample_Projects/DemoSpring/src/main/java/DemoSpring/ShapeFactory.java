package DemoSpring;

import shapes.Circle;
import shapes.Shape;
import shapes.Triangle;

public class ShapeFactory {

	
	public Shape getShape(String shapeName){
		if(shapeName.equals("Circle")){
			return new Circle();
		}else if (shapeName.equals("Triangle")){
			return new Triangle();
		}else {
			System.out.println("Enter Valid Shape!");
			return null;
		}
	}
}
