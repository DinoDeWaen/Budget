package consoleApplication;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardErrorStreamLog;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import consoleArgumentParser.Option;
import consoleArgumentParser.OptionList;
import consoleArgumentParser.OptionType;
import consoleapplication.BudgetTool;

public class BudgetToolTest {
  @Rule
  public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
  @Rule
  public final StandardOutputStreamLog log = new StandardOutputStreamLog();

  @Test
  public void testAddCategoryWithName() {
    final String [] args = {"-addCategory"};
    BudgetTool.main(args);
    
    assertEquals("add category",log.getLog());
  }

}
