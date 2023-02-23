package hu.vizicsaba.demultiplexer;

import hu.vizicsaba.demultiplexer.service.DeMultiplexerService;
import hu.vizicsaba.demultiplexer.service.model.InputDetails;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DemultiplexerApplication implements ApplicationRunner {

	private final DeMultiplexerService deMultiplexerService;

	public DemultiplexerApplication(DeMultiplexerService deMultiplexerService) {
		this.deMultiplexerService = deMultiplexerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemultiplexerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String dataFilePath = getArgument(args, "dataFilePath");
		String confFilePath = getArgument(args,"confFilePath");
		String outputFilePath = getArgument(args,"outputFilePath");
		String alignment = getArgument(args,"alignment");

		deMultiplexerService.processInput(new InputDetails(dataFilePath, confFilePath, outputFilePath, alignment));
	}

	private String getArgument(ApplicationArguments args, String argumentName) {
		return args.getOptionValues(argumentName)
			.stream()
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(argumentName + " cannot be null"));
	}

}
