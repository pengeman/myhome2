package org.pengwt.myhome.myhome2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Myhome2Application {

	public static void main(String[] args) {
		new NewMan().firstone(1);
		SpringApplication.run(Myhome2Application.class, args);
	}

}
