package hu.vizicsaba.demultiplexer.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputDetails {

    private String dataFilePath;
    private String confFilePath;
    private String outputFilePath;
    private String alignment;

}
