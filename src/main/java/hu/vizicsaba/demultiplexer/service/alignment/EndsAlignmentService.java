package hu.vizicsaba.demultiplexer.service.alignment;

import com.fasterxml.jackson.databind.JsonNode;
import hu.vizicsaba.demultiplexer.service.model.SequenceDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EndsAlignmentService implements BaseAlignmentService {

    @Override
    public List<SequenceDetails> processAlignment(List<Map.Entry<String, JsonNode>> entries, List<String> data) {
        return entries.stream()
            .map(element -> getSequenceDetails(data, element))
            .toList();
    }

    @Override
    public boolean isAlignmentSupported(String alignment) {
        return "endsAlignment".equalsIgnoreCase(alignment);
    }

    private static SequenceDetails getSequenceDetails(List<String> data, Map.Entry<String, JsonNode> element) {
        String prefix = element.getValue().get("prefix").asText();
        String postfix = element.getValue().get("postfix").asText();

        var result = data.stream()
            .filter(text -> text.startsWith(prefix) && text.endsWith(postfix) )
            .toList();

        return new SequenceDetails(element.getKey(), result);
    }

}
