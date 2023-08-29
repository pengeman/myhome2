package org.pengwt.myhome.myhome2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Myhome2Application {
	@Autowired
	Environment environment;
//	@LocalServerPort
//	private int port1;
//	@Value("${local.server.port}")
//	private int port2;

	public String postrun(){
		return environment.getProperty("server.port");
	}
	public static void main(String[] args) {
		new NewMan().firstone(1);
		System.out.println("before appliation...");
		SpringApplication.run(Myhome2Application.class, args);

//		String port = new Myhome2Application().postrun();
		System.out.println("程序已经启动，访问端口:" );

	}

}
