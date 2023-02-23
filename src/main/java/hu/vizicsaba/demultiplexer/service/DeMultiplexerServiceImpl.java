package hu.vizicsaba.demultiplexer.service;

import com.fasterxml.jackson.databind.JsonNode;
import hu.vizicsaba.demultiplexer.exception.UnsupportedAlignmentException;
import hu.vizicsaba.demultiplexer.service.alignment.BaseAlignmentService;
import hu.vizicsaba.demultiplexer.service.model.InputDetails;
import hu.vizicsaba.demultiplexer.service.model.SequenceDetails;
import hu.vizicsaba.demultiplexer.service.util.AlignmentUtil;
import hu.vizicsaba.demultiplexer.service.util.FileParserUtil;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Log4j2
public class DeMultiplexerServiceImpl implements DeMultiplexerService {

    private final List<BaseAlignmentService> baseAlignmentServices;

    public DeMultiplexerServiceImpl(List<BaseAlignmentService> baseAlignmentServices) {
        this.baseAlignmentServices = baseAlignmentServices;
    }

    @Override
    public List<SequenceDetails> processInput(InputDetails inputDetails) {
        JsonNode parsedConfiguration = FileParserUtil.getParsedConfiguration(inputDetails.getConfFilePath(), JsonNode.class);

        var alignmentService = baseAlignmentServices
            .stream()
            .filter(baseAlignmentService -> baseAlignmentService.isAlignmentSupported(inputDetails.getAlignment()))
            .findFirst()
            .orElseThrow(() -> new UnsupportedAlignmentException(inputDetails.getAlignment() + " alignment is not supported"));

        JsonNode alignmentNode = parsedConfiguration.get(inputDetails.getAlignment());
        List<String> data = FileParserUtil.getDataFileContent(inputDetails.getDataFilePath());

        List<Map.Entry<String, JsonNode>> entries = StreamSupport.stream(Spliterators.spliteratorUnknownSize(alignmentNode.fields(), Spliterator.ORDERED), false)
            .toList();
        List<SequenceDetails> matchingEntries = alignmentService.processAlignment(entries, data);
        List<SequenceDetails> nonMatchingEntries = AlignmentUtil.getNonMatchingEntries(matchingEntries, data);

        List<SequenceDetails> sequenceDetails = Stream.concat(matchingEntries.stream(), nonMatchingEntries.stream())
            .toList();

        log.info(sequenceDetails);

        sequenceDetails.forEach(sequenceDetail ->
            FileParserUtil.WriteObjectToFile(
                inputDetails.getOutputFilePath() + sequenceDetail.getName() + ".seq",
                sequenceDetail.toString()
            )
        );

        return sequenceDetails;
    }

}
