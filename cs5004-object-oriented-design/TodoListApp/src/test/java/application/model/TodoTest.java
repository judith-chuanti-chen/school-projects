package application.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TodoTest {
    Todo todo;
    LocalDate due;
    String text, category;
    @Before
    public void setUp() throws Exception {
        text = "Clean the house.";
        category = "home";
        due = LocalDate.of(2020,3,14);
        todo = new Todo(1, text, false, due,1, category);
    }

    @Test
    public void getId() {
        assertTrue(todo.getId() == 1);
    }

    @Test
    public void getText() {
        assertEquals(text, todo.getText());
    }

    @Test
    public void getCompleted() {
        assertEquals(false, todo.getCompleted());
    }

    @Test
    public void getDue() {
        assertEquals(LocalDate.of(2020,3,14), todo.getDue());
    }

    @Test
    public void getPriority() {
        assertTrue(todo.getPriority() == 1);
    }

    @Test
    public void getCategory() {
        assertEquals("home", todo.getCategory());
    }

    @Test
    public void setCompleted() {
        todo.setCompleted();
        assertEquals(true, todo.getCompleted());
    }

    @Test
    public void testToString() {
        assertEquals("Todo{id=1, text='Clean the house.', completed=false, due=2020-03-14, priority=1, " +
                "category='home'}", todo.toString());
    }

    @Test
    public void testEquals() {
        assertTrue(todo.equals(todo));
        assertFalse(todo.equals(null));
        assertFalse(todo.equals("todo"));
        assertTrue(todo.equals(new Todo(1, text, false, due,1, category)));
        assertFalse(todo.equals(new Todo(2, text, false, due,1, category)));
        assertFalse(todo.equals(new Todo(1, "Do homework", false, due,1, category)));
        assertFalse(todo.equals(new Todo(1, text, true, due,1, category)));
        assertFalse(todo.equals(new Todo(1, text, false, null,1, category)));
        assertFalse(todo.equals(new Todo(1, text, false, due,3, category)));
        assertFalse(todo.equals(new Todo(1, text, false, due,1, null)));
    }

    @Test
    public void testHashCode() {
        assertEquals(new Todo(1, text, false, due,1, category).hashCode(), todo.hashCode());
    }
}