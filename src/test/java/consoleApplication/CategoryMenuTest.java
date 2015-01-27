package consoleApplication;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

public class CategoryMenuTest {
	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();
	
	private static final CategoryMenu menu = new CategoryMenu(); 
	private String categoryName = "name";


}
