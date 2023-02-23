package hu.vizicsaba.demultiplexer.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SequenceDetails implements Serializable {

    private String name;

    private List<String> elements;

    @Override
    public String toString() {
        return String.join(" ", this.elements);
    }
}
