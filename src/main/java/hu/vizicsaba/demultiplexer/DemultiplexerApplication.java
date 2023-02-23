package hu.vizicsaba.demultiplexer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemultiplexerApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemultiplexerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<String> dataFilePath = args.getOptionValues("dataFilePath");
		List<String> confFilePath = args.getOptionValues("confFilePath");
	}

}
