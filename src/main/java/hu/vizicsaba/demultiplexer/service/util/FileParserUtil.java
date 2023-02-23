package hu.vizicsaba.demultiplexer.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.vizicsaba.demultiplexer.exception.FileParserException;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.ResourceUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static List<String> getDataFileContent(String filePath) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.collect(Collectors.toList());
        } catch (IOException exception) {
            throw new FileParserException(exception.getMessage(), exception);
        }
    }

    public static void WriteObjectToFile(String path, String text) {
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            byte[] strToBytes = text.getBytes();
            outputStream.write(strToBytes);

            outputStream.close();
        } catch (Exception exception) {
            throw new FileParserException(exception.getMessage(), exception);
        }
    }

}
