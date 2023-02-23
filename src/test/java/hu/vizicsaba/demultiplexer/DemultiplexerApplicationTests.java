package hu.vizicsaba.demultiplexer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args = {"--confFilePath=test", "--dataFilePath=test", "--outputFilePath=test","--alignment=test",})
class DemultiplexerApplicationTests {

	@Test
	void contextLoads() {
	}

}
