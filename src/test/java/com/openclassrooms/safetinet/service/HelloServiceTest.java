package com.openclassrooms.safetinet.service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

@SpringBootTest
//@ComponentScan(basePackages = "com.openclassrooms.safetinet.service")
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
