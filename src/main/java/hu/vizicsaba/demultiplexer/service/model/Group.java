package hu.vizicsaba.demultiplexer.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    private String prefix;

    private String postFix;

    private String infix;

}
