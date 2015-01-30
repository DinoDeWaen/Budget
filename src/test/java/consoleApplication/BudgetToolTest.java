package consoleApplication;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardErrorStreamLog;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import consoleApplication.BudgetTool;
import consoleArgumentParser.Option;
import consoleArgumentParser.OptionList;
import consoleArgumentParser.OptionType;

public class BudgetToolTest {
  @Rule
  public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

  @Test
  public void testAddCategoryWithName() {
   
  }

}
