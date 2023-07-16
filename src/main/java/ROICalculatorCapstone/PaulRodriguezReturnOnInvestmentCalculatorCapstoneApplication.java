package ROICalculatorCapstone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PaulRodriguezReturnOnInvestmentCalculatorCapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaulRodriguezReturnOnInvestmentCalculatorCapstoneApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner2() {
		return args -> System.out.println("Welcome to the Return On Investment Calculator!!");
	}
}
