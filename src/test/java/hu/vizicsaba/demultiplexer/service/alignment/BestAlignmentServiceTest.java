package hu.vizicsaba.demultiplexer.service.alignment;

import hu.vizicsaba.demultiplexer.service.DeMultiplexerService;
import hu.vizicsaba.demultiplexer.service.model.InputDetails;
import hu.vizicsaba.demultiplexer.service.model.SequenceDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(args = {"--confFilePath=test", "--dataFilePath=test", "--outputFilePath=test","--alignment=test",})
public class BestAlignmentServiceTest {

    @Autowired
    private DeMultiplexerService deMultiplexerService;

    @Test
    void givenMethodToGetBestAlignment_whenMatchingElementFound_GroupShouldHaveElements() {
        List<SequenceDetails> bestAlignment = deMultiplexerService.processInput(
            new InputDetails("./src/test/resources", "./src/test/resources", "./src/test/resources", "bestAlignment")
        );

        Assertions.assertEquals(bestAlignment.size(), 1);
    }

}
