package hu.vizicsaba.demultiplexer.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationFile {

    private Alignment endsAlignment;

    private Alignment midAlignment;

    private Alignment bestAlignment;

}
