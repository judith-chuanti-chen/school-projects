package application.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StringToFieldConverterTest {
    StringToFieldConverter converter;
    Map<String, Integer> headers;
    List<String> todoData;
    LocalDate due;
    Todo todo;
    final String NULL = "?";
    String validID;
    String invalidID;
    String validText;
    String invalidText;
    String validCompleted;
    String invalidCompleted;
    String validDue;
    String invalidDue;
    String priority;
    String invalidPriority;
    String category;

    @Before
    public void setUp() throws Exception {
        converter = new StringToFieldConverter();
        validID = "1";
        invalidID = "ID";
        validText = "Clean the house.";
        invalidText = NULL;
        validCompleted = "true";
        invalidCompleted = "completed";
        due = LocalDate.of(2020,3,14);
        validDue = "3/14/2020";
        invalidDue = "14/25/20999";
        priority ="2";
        invalidPriority = "4";
        category = "home";
        todoData = Arrays.asList(validID, validText, validCompleted, validDue, priority, category);
        headers = UserSetting.getDefaultHeaders();
        todo = new Todo(1, validText, true, due, 2, "home");
    }

    @Test
    public void processTodo() throws Exception{
        assertEquals(todo, StringToFieldConverter.processTodo(todoData, headers));
    }

    @Test(expected=Exception.class)
    public void processInvalidTodo() throws Exception{
        todoData = Arrays.asList(invalidID, validText, validCompleted, validDue, priority, category);
        StringToFieldConverter.processTodo(todoData, headers);
    }

    @Test(expected=InvalidTodoException.class)
    public void testCheckEmptyFieldNull() throws InvalidTodoException{
        StringToFieldConverter.processId(null);
    }

    @Test(expected=InvalidTodoException.class)
    public void testCheckEmptyField() throws InvalidTodoException{
        StringToFieldConverter.processId("");
    }

    @Test
    public void processId() throws InvalidTodoException{
        assertTrue(StringToFieldConverter.processId(validID).equals(1));
        assertNull(StringToFieldConverter.processId(NULL));
    }

    @Test(expected=InvalidTodoException.class)
    public void processInvalidId() throws InvalidTodoException{
        StringToFieldConverter.processId(invalidID);
    }

    @Test
    public void processText() throws InvalidTodoException {
        assertTrue(StringToFieldConverter.processText(validText).equals(validText));
    }

    @Test(expected=InvalidTodoException.class)
    public void processInvalidText() throws InvalidTodoException {
        StringToFieldConverter.processText(NULL);
    }

    @Test
    public void processCompleted() throws InvalidTodoException{
        assertTrue(StringToFieldConverter.processCompleted(validCompleted));
        validCompleted = "false";
        assertFalse(StringToFieldConverter.processCompleted(validCompleted));
    }

    @Test(expected=InvalidTodoException.class)
    public void processInvalidCompleted() throws InvalidTodoException{
        StringToFieldConverter.processCompleted(invalidCompleted);
    }


    @Test
    public void processDueDate() throws Exception{
        assertTrue(StringToFieldConverter.processDueDate(validDue).equals(due));
        assertNull(StringToFieldConverter.processDueDate(NULL));
    }

    @Test(expected=DateTimeParseException.class)
    public void processInvalidDueDate() throws Exception{
        StringToFieldConverter.processDueDate(invalidDue);
    }

    @Test
    public void processPriority() throws Exception{
        assertTrue(StringToFieldConverter.processPriority(priority).equals(2));
        assertNull(StringToFieldConverter.processPriority(NULL));
    }

    @Test(expected=InvalidTodoException.class)
    public void processInvalidPriority1() throws Exception {
        StringToFieldConverter.processPriority(invalidPriority);
    }

    @Test(expected=InvalidTodoException.class)
    public void processInvalidPriority2() throws Exception {
        invalidPriority = "0";
        StringToFieldConverter.processPriority(invalidPriority);
    }

    @Test(expected=NumberFormatException.class)
    public void processInvalidPriority3() throws Exception {
        StringToFieldConverter.processPriority("priority");
    }

    @Test
    public void processCategory() throws InvalidTodoException {
        assertTrue(StringToFieldConverter.processCategory(category).equals("home"));
        category = NULL;
        assertNull(StringToFieldConverter.processCategory(category));
    }
}