package br.com.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class ProjetoBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoBankApplication.class, args);
	}

	@RequestMapping
	@ResponseBody
	public String projectName(){
		return "Projeto Bank " + "Igor Luiz e Leonardo Dourado";
	}

}
