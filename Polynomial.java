import java.io.File;   
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
public class Polynomial {
	public double [] coefs;
	public int [] powers; 
	
	public Polynomial() {
		//no argument constructor
		this.coefs = null; 
		this.powers = null;
	}
	
	public Polynomial(double [] coefs, int [] powers) {
		//coef and power array argument constructor
		this.coefs = coefs;
		this.powers = powers; 
	}


	public Polynomial(File file) {
		//file argument constructor 
        HashMap<Integer, Double> poly = new HashMap<>();
		try {
		Scanner myReader = new Scanner(file);
		String p = myReader.nextLine();
		lineParse(p, poly);
		myReader.close();
		} catch (FileNotFoundException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
		}
		
		Polynomial r = mapToPolynomial(poly); 
		this.coefs = r.coefs; 
		this.powers = r.powers;
	}

	public HashMap<Integer, Double> polynomialToMap(Polynomial p) {
		//Create a hashmap from a polynomial
		if (p.coefs == null){
			return new HashMap<>();
		}
		HashMap<Integer, Double> res = new HashMap<Integer, Double>(); 
		
		for(int i = 0; i < p.coefs.length; i++) {
    		final double coef = p.coefs[i]; // extract current coefficient
			if (res.containsKey(p.powers[i])) {
				res.compute(p.powers[i], (k,v) -> v + coef); 
			}
			else {
				res.put(p.powers[i], coef); 
			}
		}
		return res;
	}

	public Polynomial mapToPolynomial(HashMap<Integer, Double> p) {
		//Create a Polynomial for the non-zero coefs 
		int polyLen = 0;
		if(p.isEmpty()){
			return new Polynomial(); 
		}
		for(double i : p.values()) {
			if(i != 0){
				polyLen++; 
			}
		}
		if (polyLen == 0) {  
			return new Polynomial(); 
		}

		double [] coefs = new double[polyLen]; 
		int [] powers = new int[polyLen]; 
		int ind = 0;  
		for(int i: p.keySet()) {  
			powers[ind] = i;
			coefs[ind] = p.get(i);
			ind++;
		}
		return new Polynomial(coefs, powers);
	}

	private void lineParse(String p, HashMap<Integer, Double> poly) {
		//parse the file
		p = p.trim(); 
        String[] terms = p.split("(?=[+-])");  

        for (String term : terms) { 
            term = term.trim();  

            double coef = 1.0;
            int power = 0;

            if (term.contains("x")) {
                String[] parts = term.split("x"); //up to 3 parts
                if (parts[0].isEmpty() || parts[0].equals("+")) { //coef
                    coef = 1.0;
                } 
				else if (parts[0].equals("-")) {
                    coef = -1.0;
                } 
				else {
                    coef = Double.parseDouble(parts[0]);
                }

                if (parts.length > 1) { //power
                    if (!parts[1].isEmpty()) {
                        power = Integer.parseInt(parts[1].substring(1));
                    } 
					else {
                        power = 1; // If no power is specified, it's x^1
                    }
                }
            } else { //constant term 
                coef = Double.parseDouble(term); 
                power = 0; 
            }
            poly.put(power, coef);
        }
    }

	public void saveToFile(String name){
		try{ 
			FileWriter myWriter = new FileWriter(name);
            String pString = polyToString();  
            myWriter.write(pString);  
			myWriter.close();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
	}

	public String polyToString() { //convert a polynomial to a string
		HashMap<Integer, Double> p = polynomialToMap(this);
		String res = ""; 
		for(int i : p.keySet()){
			double coef = p.get(i); 
			if(i == 0){ //x^0 ie. constant 
				if(res == ""){
					res = res + coef; 
				}
				else{
					if(coef < 0){
						res = res + coef;
					}
					else{
						res = res + "+" + coef; 
					}
				}
			}

			else if(i == 1){ //x^1 ie. x
				if(res == ""){
					res = res + coef + "x"; 
				}
				else{
					if(coef < 0){
						res = res + coef + "x"; 
					}
					else{
						res = res + "+" + coef + "x"; 
					}
				}
			}

			else{// i in naturals > 1
				if(res == ""){
					res = res + coef + "x" + i; 
				}
				else{
					if(coef < 0){
						res = res + coef + "x" + i; 
					}
					else{
						res = res + "+" + coef + "x" + i; 
					}
				}
			}
		}
		if(res == ""){
			res = "0";
		}
		return res; 
	}
	
	public Polynomial add(Polynomial poly) {
		HashMap<Integer, Double> instPoly = polynomialToMap(this); 
		HashMap<Integer, Double> res = polynomialToMap(poly);//map with all in poly
		for (int i : instPoly.keySet()) { //go through and add to res
			if (res.containsKey(i)){ //adding to coef
				res.compute(i, (k,v) -> v + instPoly.get(i)); 
			}
			else {//adding new power and coef
				res.put(i, instPoly.get(i)); 
			}
		}
		
		return mapToPolynomial(res);
	}
	
	public double evaluate(double x) {
		double res = 0; 
		HashMap<Integer, Double> m = polynomialToMap(this); 

		for (int i : m.keySet()) {
			res = res + m.get(i)*Math.pow(x, i); 
		}
		return res; 
	}
	
	public boolean hasRoot(double x) {
		if(evaluate(x) == 0) {
			return true; 
		}
		return false; 
	}

	public Polynomial multiply(Polynomial poly){
		HashMap<Integer, Double> instPoly = polynomialToMap(this); 
		HashMap<Integer, Double> times = polynomialToMap(poly);//map with all in poly
		HashMap<Integer, Double> res = new HashMap<>();
		for (int i : instPoly.keySet()) { //go through and add the products to res
			double coef1 = instPoly.get(i); 
			for(int j : times.keySet()){
				double coef2 = times.get(j);
				int newPow = (i + j); 
				double newCoef = coef1*coef2; 

				if (res.containsKey(newPow)){ //adding to coef
					res.compute(newPow, (k,v) -> v + newCoef); 
				}
				else {//adding new power and coef
					res.put(newPow, newCoef); 
				}
			}
		}
		return mapToPolynomial(res);
	}
}