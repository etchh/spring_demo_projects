package shapes;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
@Qualifier("myCircle")
public class Circle implements Shape , InitializingBean, ApplicationEventPublisherAware{
	
	private ApplicationEventPublisher publisher;
	
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("Inside Circle.draw()");
		
		DrawEvent event = new DrawEvent(this);
		publisher.publishEvent(event);
		
	}
	
	
	public void afterPropertiesSet() throws Exception {
		System.out.println("inside spring's initialization of Circle!!!!!");
	}
	
	
	public void myInit(){
		System.out.println("inside init circle myInit method");
	}
	
	public void myDestroy(){
		System.out.println("inside destroy method of circle");
	}


	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		publisher = applicationEventPublisher;
		
	}
}
