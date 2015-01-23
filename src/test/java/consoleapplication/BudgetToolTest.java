package consoleapplication;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class BudgetToolTest {
	
  @Rule
  public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

  @Test
  public void test() {
    systemInMock.provideText("name\nsomething else\n");
    BudgetTool.main(null);
    //assertSomething
  }

}
