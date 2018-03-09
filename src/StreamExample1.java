import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.xml.validation.Schema;

public class StreamExample1 {

	private String serverName = "192.168.10.124";
	private String dbms = "mysql";
	private String userName  = "alpha";
	private String password = "alpha";
	private int portNumber = 3306;
	private String dbName = "stocktips_dev";
	
	
	public void ex1() {
		List<String> myList =
			    Arrays.asList("a1", "a2", "b1", "c2", "c1");

			myList
			    .stream()
			    .filter(s -> s.startsWith("b"))
			    .filter(s -> s.endsWith("1"))
			    .map(String::toUpperCase)				// map => operate on each item!
			    .map(String::toLowerCase)				// map => operate on each item!
			    .sorted()
			    .forEach(System.out::println);          // Call this method with item as argument

			// C1
			// C2		
			
			
			new Thread(() -> System.out.println("Hello World!")).start();
			
			List<String> memberNames = new ArrayList<>();
			memberNames.add("Amitabh");
			memberNames.add("Shekhar");
			memberNames.add("Aman");
			memberNames.add("Rahul");
			memberNames.add("Shahrukh");
			memberNames.add("Salman");
			memberNames.add("Yana");
			memberNames.add("Lokesh");
			
			// f1();
			f2();
			f3();
			f4();
			f5();
			f6();
			
	}
	
	private void f1() {
		List<Schema> result = new ArrayList<>();
		try (Connection c = getConnection()) {
		    String sql = "select schema_name, is_default " +
		                 "from information_schema.schemata " +
		                 "order by schema_name";
		 
		    try (PreparedStatement stmt = c.prepareStatement(sql);
		         ResultSet rs = stmt.executeQuery()) {

		        while (rs.next()) {
		            System.out.println(rs.getString("SCHEMA_NAME") + rs.getBoolean("IS_DEFAULT"));
		        }
		    }
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private void f2() {
		System.out.println("---- F2 -----");
		Collection<String> words = Arrays.asList("a","collection","of", "words");
		Stream<String> wordstream1 = words.stream();
		Stream<String> wordstream2 = words.stream();
		wordstream2.forEach(System.out::println);
		
		Stream<String> uppercasewords = wordstream1.map(s -> s.toUpperCase());		
		String allWords = uppercasewords.reduce("", (x, y) -> x+y );
		System.out.println(allWords);
	}
	
	private void f3() {
		System.out.println("---- F3 -----");
		List<String> words = Arrays.asList("one","two","three");
		words.replaceAll((x) -> x.toUpperCase());
		words.forEach(System.out::println);
	}
	
	private void f4() {
        PrintWriter writer;
		try {
			writer = new PrintWriter("filename.txt");
	        Consumer<String> logger = writer::println; // instance method reference!
	        Consumer<String> screener = System.out::println;  // System.out object
	        Consumer<String> both = screener.andThen(logger);
	        both.accept("Program Started");
	        
	        writer.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Composing Functions, part 1
	private void f5() {
        Function<Integer,Integer> round10 = (i1) -> (i1 / 10) * 10;
        Function<Integer,Integer> addOne = (i1) -> i1 + 1;   // Adds more formatting or something

        Function<Integer,Integer> both = round10.andThen(addOne);

        Integer result = both.apply(23);
        System.out.println("Result is " + result);
	}
	
	// Composing Functions, part 2
	private void f6() {
        Function<Integer,Integer> round10 = (i1) -> (i1 / 10) * 10;
        Function<Integer,Integer> addOne = (i1) -> i1 + 1;   // Adds more formatting or something

        Function<Integer,Integer> both = addOne.compose(round10);

        Integer result = both.apply(23);
        System.out.println("Result is " + result);
	}
	
	private void f7() {
		Function<Employee, String>
			getName = Employee::getName; // Class method reference
		Function<String, Character>
			getFirstLetter = name -> name.charAt(0);
		Function<Employee, Character>
			initial = getName.andThen(getFirstLetter);
	}
	
	public Connection getConnection() throws SQLException {

	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);

	    if (this.dbms.equals("mysql")) {
	        conn = DriverManager.getConnection("jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber + "/", connectionProps);
	    } else if (this.dbms.equals("derby")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + this.dbms + ":" +
	                   this.dbName +
	                   ";create=true",
	                   connectionProps);
	    }
	    System.out.println("Connected to database");
	    return conn;
	}
	
	
	public static void main(String[] args) {
		new StreamExample1().ex1();
	}
}

