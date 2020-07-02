package application.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FieldToStringConverterTest {
    Todo todo1;
    Todo todo2;
    LocalDate date = LocalDate.of(2019,5,19);
    @Before
    public void setUp() throws Exception {
        FieldToStringConverter converter = new FieldToStringConverter();
        todo1 = new Todo(1, "finish homework", true, date, 2, "school");
        todo2 = new Todo(2, "clean the house", false, null, null, null);
    }

    @Test
    public void processTodo() {
        List<String> expected1 = Arrays.asList("1", "finish homework", "true", "5/19/2019", "2", "school");
        List<String> expected2 = Arrays.asList("2", "clean the house", "false", "?", "?","?");
        assertEquals(expected1, FieldToStringConverter.processTodo(todo1));
        assertEquals(expected2, FieldToStringConverter.processTodo(todo2));
    }

}