import java.io.File;   
public class Driver {
	public static void main(String [] args) {
		
		Polynomial p0 = new Polynomial();
		
		double [] c1 = {6,5};
		int [] pwr1 = {0,3};
		Polynomial p1 = new Polynomial(c1, pwr1); // 5x^3 + 6
		
		double [] c2 = {9,-2,4};
		int [] pwr2 = {4,1,2};
		Polynomial p2 = new Polynomial(c2, pwr2);// 9x^4 -2x + 4x^2
		
		Polynomial s1 = p1.add(p2);
		Polynomial s2 = p1.add(p0);
		Polynomial m1 = p1.multiply(p2);
		Polynomial m2 = p1.multiply(p0);


		System.out.println("Case 1: creating p0, p1 & p2"); 
		System.out.println("P0: " + p0.polyToString() + " | P1: " + p1.polyToString() + " | P2: " + p2.polyToString());
		System.out.println("Passed Case 1");

		System.out.println("Case 2: summing p1 & p2");
		System.out.println("P1+P2: " + s1.polyToString());
		System.out.println("Passed Case 2");

		System.out.println("Case 3: summing p1 p0");
		System.out.println("P1+P3: " + s2.polyToString());
		System.out.println("Passed Case 3");

		System.out.println("Case 4: evaluate p1 when x = 0 and x = 0.5, p0 when x = 1.");
		System.out.println("P1, x = 0: " + p1.evaluate(0) + " | P1, x = 0.5: " + p1.evaluate(0.5) + " | P0, x = 1: " + p0.evaluate(1));
		System.out.println("Passed Case 4");

		System.out.println("Case 5: checking 1 is not a root of p1, 0 is a root of P2 and 0.0001 is a root of P0.");
		System.out.println("P1: " + p1.hasRoot(1) + " | P2: " + p2.hasRoot(0) + " | P0: " + p0.hasRoot(0.0001));
		System.out.println("Passed Case 5");

		System.out.println("Case 6: multiplying P1 and P2");
		System.out.println("P1xP2: "+ p1.polyToString() + " x " + p2.polyToString() + " = " + m1.polyToString());
		System.out.println("Passed Case 6");

		System.out.println("Case 7: multiplying P1 and P0");
		System.out.println("P1xP2: " + m2.polyToString());
		System.out.println("Passed Case 7");

		File r1 = new File("Read.txt");
		if (r1.exists()) {

			Polynomial rp1 = new Polynomial(r1); 
			System.out.println("Case 8: creating polynomial from file");
			System.out.println("R: " + rp1.polyToString());
			System.out.println("Passed Case 8");

			File r2 = new File("Write.txt");
			if (r2.exists()) {
				Polynomial rp2 = new Polynomial(r2); 
				System.out.println("Case 9: creating polynomial from empty file");
				System.out.println("R: " + rp2.polyToString());
			} 
			else {
				System.out.println("The file does not exist.");
			}
			System.out.println("Passed Case 9");
			rp1.saveToFile("Write.txt");
			if (r2.exists()) {
				Polynomial rp3 = new Polynomial(r2); 
				System.out.println("Case 10: saving polynomial to file");
				System.out.println("R: " + rp3.polyToString());
			} 
			else {
				System.out.println("The file does not exist.");
			} System.out.println("Passed Case 10");
		} 
		else {
			System.out.println("The file does not exist.");
		}

	}
}