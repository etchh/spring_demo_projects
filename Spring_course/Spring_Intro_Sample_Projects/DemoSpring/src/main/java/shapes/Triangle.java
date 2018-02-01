package shapes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class Triangle implements Shape{

	private String type;
	@Autowired
	@Qualifier("myCircle")
	private Circle circle;
	
	public Triangle(){
		
	}
//	
//	public Triangle(Circle circle){
//		System.out.println("inside triangle constructor");
//		this.circle = circle;
//	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Circle getCircle() {
		return circle;
	}
	
	@Required
	@Autowired
	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("Inside Triangle.draw() " + type);
		circle.draw();
	}
	
	public void myInit(){
		System.out.println("inside init triangle myInit method");
	}
	
	public void myDestroy(){
		System.out.println("inside destroy method of triangle");
	}
}
