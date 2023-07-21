package service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.openclassrooms.safetinet.service.HelloService;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

@SpringBootTest
public class HelloServiceTest {
	
	  @Autowired
	    private HelloService helloService;
	  
	   @Test
	    public void testGetHelloMessage() {
     

	        // Appel de la méthode à tester
	        String message = helloService.getHelloMessage();

	        // Vérification
	        assertEquals("Hello, World!", message);
	    }
}
