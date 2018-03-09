import java.util.Comparator;
import java.util.function.Consumer;

public class Interfaces {

	public static void main(String[] args) {
		
		Employee 
			mike = new Employee("Mike", 2000),
			louise = new Employee("Louise", 2500);
		
		// Anonymous function
		
		Comparator<Employee> byName = new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
		
		System.out.println(byName.compare(mike, louise));
		
		Comparator<Employee> byNameThenNull = Comparator.nullsLast(byName);
		
		System.out.println(byNameThenNull.compare(mike, louise));
		System.out.println(byNameThenNull.compare(mike, null));
		
		
		// LAMBDA
		
		Comparator<String> byLength = (String a, String b) -> {
			return Integer.compare(a.length(), b.length());
		};
		
		
		Comparator<Employee> byNameLambda1 = 
				(Employee a, Employee b) -> { return a.getName().compareTo(b.getName()); };
				
		Comparator<Employee> byNameLambda2 = 
				(a, b) -> { return a.getName().compareTo(b.getName()); };
		
		Comparator<Employee> byNameLambda3 = 
				(a, b) -> a.getName().compareTo(b.getName());

//		not allowed to remove braces and leave return				
//		Comparator<Employee> byNameLambda4 = 
//				(a, b) -> return a.getName().compareTo(b.getName());
				
				
		Runnable r = () -> {
			System.out.println("A compact Runnable!");
		};
		Thread t1 = new Thread(r);
		
		// No need to even mention Runnable
		Thread t2 = new Thread(() -> {System.out.println("A compact Runnable!");});
		
		// No need for braces here (or semikolon at end) )
		Thread t3 = new Thread(() -> System.out.println("A compact Runnable!"));
		
		
		Consumer<String> lengthPrinter = s -> System.out.println(s.length());
		
		
		// LAMBDA EXPRESSION SYNTAX
		
		/*
			parameters -> body
			
			parameters								body
			
			(int a, int b)							{ block; }
			
			(a,b)									expr
			
			(a)
			
			()
			
			a
		*/
		
		
		// Context must contain enough info to identify the receiving FunctionalInterface
		
		// RightHandSide of assignment
		//  *	Consumer<String> = lambda
		// Actual parameter of a method or constructor
		//  *   new Thread(lambda)
		// Argument of 'return'
		//	*	return lambda
		// Argument of cast
		//  *	(Consumer<String>)
		
		
			
		
		
	}
	
	// GENERIC / PARAMETRIC METHODS
	public static <T> T getFirst(T[] array) {
		return array[0];
	}
	
	public static void f1() {
		String[] strarray = {"one","two","three"};
		
		// It understands that the returns type is String, snice we pass a String[]
		String one = getFirst(strarray);
	}
	
	
}
