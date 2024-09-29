# B07 Lab #2 Branch :D
This repository is for the second lab in CSCB07--it contains a polynomial and driver file! 

## Polynomial File: 

### Field: An array of doubles representing the non-zero coefficients and an array of ints representing the powers of the polynomial.
Example: The polynomial 6 - 2x + 5x^3 is represented as [6, -2, 5] [0, 1, 3].

### Constructors:
No-argument constructor: Initializes the polynomial to zero ([0]).
Parameterized constructor: Takes an array of non-zero doubles and ints to set the coefficients and powers.

### Methods:
add(Polynomial p): Returns type polynomial containing the sum of the current polynomial and the polynomial p.
evaluate(double x): Returns a double that represents the polynomial's evaluation for a given value of (x).
Example: For the polynomial 6 - 2x + 5x^3, evaluate(-1) returns 3.
hasRoot(double x): Returns true if the given value of (x) is a root of the polynomial (i.e., if the polynomial evaluates to zero for that value).

## Driver File: 
Tests the constructors, add, evaluate, example and hasRoot functions in the Polynomial file. 
