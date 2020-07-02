package application.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TodoIncompleteIteratorTest {
  TodoIncompleteIterator incompleteIterator;
  TodoIncompleteIterator sameIncompleteIterator;
  Todo todo1;
  Todo todo2;
  LocalDate date;
  List<Todo> todos;

  @Before
  public void setUp() throws Exception {
    date = LocalDate.parse("2020-06-29");
    todo1 = new Todo(1, "Cleaning", true, date, 3, "Work");
    todo2 = new Todo(1, "Cleaning", false, date, 3, "School");
    todos = new ArrayList<>(Arrays.asList(todo1, todo2));
    incompleteIterator = new TodoIncompleteIterator(todos);
    sameIncompleteIterator = new TodoIncompleteIterator(todos);
  }

  @Test
  public void hasNext() {
    assertTrue(incompleteIterator.hasNext());
  }

  @Test
  public void next() {
    Todo newTodo = new Todo(1, "Cleaning", false, date, 3, "School");
    assertEquals(incompleteIterator.next(), newTodo);
  }

  @Test
  public void testEquals() {
    assertTrue(incompleteIterator.equals(incompleteIterator));
    assertFalse(incompleteIterator.equals(null));
    assertTrue(incompleteIterator.equals(sameIncompleteIterator));
  }

  @Test
  public void testHashCode() {
    assertTrue(incompleteIterator.hashCode() == sameIncompleteIterator.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("TodoIncompleteIterator{list=[Todo{id=1, text='Cleaning', completed=false, due=2020-06-29, priority=3, category='School'}], current=0}", incompleteIterator.toString());
  }
}