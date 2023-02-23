package hu.vizicsaba.demultiplexer.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.vizicsaba.demultiplexer.exception.FileParserException;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

@Log4j2
public final class FileParserUtil {

    private FileParserUtil() {}

    public static <T> T getParsedConfiguration(String configurationPath, Class<T> type) {
        try {
            var file = ResourceUtils.getFile(configurationPath);

            return new ObjectMapper().readValue(file, type);
        } catch (IOException exception) {
            log.error(exception.getMessage());

            throw new FileParserException(exception.getMessage(), exception);
        }
    }

}
