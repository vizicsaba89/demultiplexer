package hu.vizicsaba.demultiplexer.service;

import hu.vizicsaba.demultiplexer.service.model.InputDetails;
import hu.vizicsaba.demultiplexer.service.model.SequenceDetails;

import java.util.List;

public interface DeMultiplexerService {

    List<SequenceDetails> processInput(InputDetails inputDetails);

}
