package application.command;

import static org.junit.Assert.*;

import application.command.CommandLineOption.Builder;
import application.model.UserSetting.TaskType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CommandLineOptionTest {
  Builder builder;
  CommandLineOption option;
  CommandLineOption newOption;

  @Before
  public void setUp() throws Exception {
    builder = new Builder(TaskType.ADD_TODO, TaskType.ADD_TODO_TEXT);
    option = builder.build();
    newOption = builder.setHasArgs().setMinArgs(2).setMaxArgs(2).build();
  }

  @Test
  public void noSubArgs() {
    assertFalse(option.hasSubArgs());
  }

  @Test
  public void getMainTask() {
    assertEquals(option.getMainTask(), TaskType.ADD_TODO);
  }

  @Test
  public void getName() {
    assertEquals(option.getName(), TaskType.ADD_TODO_TEXT);
  }

  @Test
  public void getArgs() {
    assertNull(option.getArgs());
  }

  @Test
  public void setArgs() throws InvalidCommandException {
    List<String> args = new ArrayList<>();
    args.add("add_new_todo");
    option.setArgs(args);
    assertEquals(option.getArgs(), args);
  }

  @Test (expected = InvalidCommandException.class)
  public void validateMinArgs() throws InvalidCommandException {
    List<String> args = new ArrayList<>();
    args.add("add_new_todo");
    newOption.setArgs(args);
  }

  @Test (expected = InvalidCommandException.class)
  public void validateMaxArgs() throws InvalidCommandException {
    List<String> args = new ArrayList<>();
    args.add("add_new_todo");
    args.add("add_new_todo");
    args.add("add_new_todo");
    newOption.setArgs(args);
  }

  @Test
  public void testEquals() throws Exception{
    assertTrue(option.equals(option));
    assertFalse(option.equals(null));
    assertFalse(option.equals("option"));
    assertTrue(option.equals(new Builder(TaskType.ADD_TODO, TaskType.ADD_TODO_TEXT).build()));
    assertFalse(option.equals(new Builder(TaskType.ADD_CATEGORY, TaskType.ADD_TODO_TEXT).build()));
    assertFalse(option.equals(new Builder(TaskType.ADD_TODO, TaskType.ADD_PRIORITY).build()));
    assertFalse(option.equals(new Builder(TaskType.ADD_TODO, TaskType.ADD_TODO_TEXT).setHasArgs().build()));
    assertFalse(option.equals(new Builder(TaskType.ADD_TODO, TaskType.ADD_TODO_TEXT).setMaxArgs(5).build()));
    assertFalse(option.equals(new Builder(TaskType.ADD_TODO, TaskType.ADD_TODO_TEXT).setMinArgs(5).build()));
    newOption = new Builder(TaskType.ADD_TODO, TaskType.ADD_TODO_TEXT).build();
    newOption.setArgs(Arrays.asList("args"));
    assertFalse(option.equals(newOption));

  }

  @Test
  public void testHashCode() {
    assertEquals(new Builder(TaskType.ADD_TODO, TaskType.ADD_TODO_TEXT).build().hashCode(), option.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("CommandLineOption{mainTask=ADD_TODO, name=ADD_TODO_TEXT, hasArgs=false, args=null, minArgs=0, maxArgs=30}", option.toString());
  }
}