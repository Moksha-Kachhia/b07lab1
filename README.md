# B07 Lab #1 Repo :D
This repository is for the first lab in CSCB07--it contains a polynomial and driver file! 

## Polynomial File: 

### Field: An array of doubles representing the coefficients of the polynomial.
Example: The polynomial (6 - 2x + 5x^3) is represented as [6, -2, 0, 5].

### Constructors:
No-argument constructor: Initializes the polynomial to zero ([0]).
Parameterized constructor: Takes an array of doubles to set the coefficients.

### Methods:
- add(Polynomial p): Returns the sum of the current polynomial and the polynomial p.
- evaluate(double x): Evaluates the polynomial for a given value of (x). Example: For the polynomial (6 - 2x + 5x^3), evaluate(-1) returns 3.
- hasRoot(double x): Checks if the given value of (x) is a root of the polynomial (i.e., if the polynomial evaluates to zero for that value).

## Driver File: 
Tests the constructors, add, evaluate, example and hasRoot functions in the Polynomial file. 
