package DemoSpring;

import java.util.ArrayList;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import drawing.Drawings;
import shapes.Shape;
import shapes.Triangle;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
    	Drawings drawing = new Drawings();
//    	ShapeFactory shapeFactory = new ShapeFactory();
//    	Triangle t = (Triangle) shapeFactory.getShape("Triangle");
//    	drawing.setShape(t);
    	
    	AbstractApplicationContext context = new ClassPathXmlApplicationContext("springBeans.xml");
    	Shape shape = (Triangle) context.getBean("shape");
//    	Triangle triangle1 = (Triangle) factory.getBean("triangle");
//    	Triangle triangle2 = (Triangle) factory.getBean("triangle");
//    	ArrayList<String> x = new ArrayList<String>(){};
//    	System.out.println(triangle +"\n");
//    	System.out.println(triangle1 +"\n");
//    	System.out.println(triangle2 +"\n");
    	
    	String message = context.getMessage( /*name of property*/ "greeting",
    			/*string of objects in case of message taking parameters*/new Object[]{"Ahmed" , "Hisham"},
    			/*default message*/ "Default Greeting" ,
    			/*locale*/ null);
    	
    	System.out.println(message);
    	
    	drawing.setShape(shape);
        drawing.drawShape();
        
        context.registerShutdownHook();
    }
}
