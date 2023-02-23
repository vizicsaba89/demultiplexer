package hu.vizicsaba.demultiplexer.service.util;

import hu.vizicsaba.demultiplexer.service.model.SequenceDetails;

import java.util.List;

public final class AlignmentUtil {

    private AlignmentUtil() {}


    public static List<SequenceDetails> getNonMatchingEntries(List<SequenceDetails> matchingSequenceDetails, List<String> data) {
        List<String> matchingElements = matchingSequenceDetails.stream()
            .map(SequenceDetails::getElements)
            .flatMap(List::stream)
            .toList();

        List<String> nonMatchingElements = data.stream()
            .filter(element -> !matchingElements.contains(element))
            .toList();

        return List.of(new SequenceDetails("unmatched", nonMatchingElements));
    }

}
