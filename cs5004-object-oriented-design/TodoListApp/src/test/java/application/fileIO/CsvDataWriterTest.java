package application.fileIO;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CsvDataWriterTest {
  CsvDataWriter dataWriter;
  List<List<String>> contents;
  CsvDataReader dataReader;

  @Before
  public void setUp() throws Exception {
    dataWriter = new CsvDataWriter();
    dataReader = new CsvDataReader();
    List<String> line = new ArrayList<>(
        Arrays.asList("1", "Finish HW9", "false", "3/22/2020", "1", "school"));
    contents = new ArrayList<>();
    contents.add(line);
  }

  @Test
  public void write() throws Exception {
    dataWriter.write("testWriteTodos.csv", contents);
    dataReader.read("testWriteTodos.csv");
    assertEquals(dataReader.getHeader(), contents.get(0));
  }
}