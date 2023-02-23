package hu.vizicsaba.demultiplexer.service.alignment;

import com.fasterxml.jackson.databind.JsonNode;
import hu.vizicsaba.demultiplexer.service.model.SequenceDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class MidAlignmentService implements BaseAlignmentService {

    @Override
    public List<SequenceDetails> processAlignment(List<Map.Entry<String, JsonNode>> entries, List<String> data) {
        return entries.stream()
            .map(element -> getSequenceDetails(data, element))
            .toList();
    }

    @Override
    public boolean isAlignmentSupported(String alignment) {
        return "midAlignment".equalsIgnoreCase(alignment);
    }

    private static SequenceDetails getSequenceDetails(List<String> data, Map.Entry<String, JsonNode> element) {
        String infix = element.getValue().get("infix").asText();

        var result = data.stream()
            .filter(text -> text.contains(infix))
            .toList();

        return new SequenceDetails(element.getKey(), result);
    }
}
