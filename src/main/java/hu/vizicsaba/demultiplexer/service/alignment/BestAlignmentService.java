package hu.vizicsaba.demultiplexer.service.alignment;

import com.fasterxml.jackson.databind.JsonNode;
import hu.vizicsaba.demultiplexer.service.model.SequenceDetails;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BestAlignmentService implements BaseAlignmentService {

    @Override
    public List<SequenceDetails> processAlignment(List<Map.Entry<String, JsonNode>> entries, List<String> data) {
        return entries.stream()
            .map(element -> getSequenceDetails(data, element))
            .toList();
    }

    @Override
    public boolean isAlignmentSupported(String alignment) {
        return "bestAlignment".equalsIgnoreCase(alignment);
    }

    private static SequenceDetails getSequenceDetails(List<String> data, Map.Entry<String, JsonNode> element) {
        String infix = element.getValue().get("infix").asText();

        List<String> integerStringMap = data.stream()
            .map(text -> Map.of(LevenshteinDistance.getDefaultInstance().apply(text, infix), text))
            .min(Comparator.comparingInt(entry -> entry.keySet().stream().findFirst().orElse(null)))
            .stream()
            .findFirst()
            .orElse(null)
            .values()
            .stream()
            .toList();


        return new SequenceDetails(element.getKey(), integerStringMap);
    }
}
