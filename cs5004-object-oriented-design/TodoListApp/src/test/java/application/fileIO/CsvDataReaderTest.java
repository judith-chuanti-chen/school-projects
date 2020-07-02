package application.fileIO;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CsvDataReaderTest {
  CsvDataReader dataReader;

  @Before
  public void setUp() throws Exception {
    dataReader = new CsvDataReader();
    dataReader.read("testTodos.csv");
  }

  @Test
  public void getHeader() throws Exception {
    List<String> header = new ArrayList<>(Arrays.asList("id", "text", "completed", "due", "priority", "category"));
    assertEquals(header, dataReader.getHeader());
  }

  @Test
  public void getContents() {
    List<String> line = new ArrayList<>(Arrays.asList("1", "Finish HW9", "false", "3/22/2020", "1", "school"));
    List<List<String>> contents = new ArrayList<>();
    contents.add(line);
    assertEquals(contents, dataReader.getContents());
  }

  @Test (expected = IllegalArgumentException.class)
  public void read() throws Exception {
    dataReader.read("testTodos");
  }

  @Test (expected = FileNotFoundException.class)
  public void fileNotFound() throws Exception {
    dataReader.read("new.csv");
  }

}