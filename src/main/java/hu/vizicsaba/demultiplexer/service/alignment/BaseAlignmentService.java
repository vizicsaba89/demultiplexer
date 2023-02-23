package hu.vizicsaba.demultiplexer.service.alignment;

import com.fasterxml.jackson.databind.JsonNode;
import hu.vizicsaba.demultiplexer.service.model.SequenceDetails;

import java.util.List;
import java.util.Map;

public interface BaseAlignmentService {

    List<SequenceDetails> processAlignment(List<Map.Entry<String, JsonNode>> entries, List<String> data);

    boolean isAlignmentSupported(String alignment);

}
