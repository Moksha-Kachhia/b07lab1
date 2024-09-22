public class Polynomial {
	public double [] p;
	
	public Polynomial() {
		this.p = new double [0]; 
	}
	
	public Polynomial(double [] p) {
		this.p = p;
	}
	
	public Polynomial add(Polynomial poly) {
		int len_a = this.p.length; 
		int len_b = poly.p.length; 
		int res_len = Math.max(len_a, len_b);
		double[] res = new double [res_len];
		for(int i = 0; i < res_len; i++) {
			if(i >= len_a){ //we're done summing from calling object 
				res[i] = poly.p[i];
			}	
			else if (i >= len_b) { //we're done summing from argument 
				res[i] = this.p[i];
			}
			else { // both have a value for the index
				res[i] = poly.p[i] + this.p[i]; 
			}
		}
		return new Polynomial(res); 
	}
	
	public double evaluate(double x) {
		double res = 0; 
		for (int i = 0; i < p.length; i++) {
			res = res + p[i]*Math.pow(x, i); 
		}
		return res; 
	}
	
	public boolean hasRoot(double x) {
		if(evaluate(x) == 0) {
			return true; 
		}
		return false; 
	}
}