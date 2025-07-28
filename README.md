# Infix Notation Java Calculator

This repository contains a single-file Java program designed to parse and solve arithmetic expressions written in infix notation. The core of this project is a custom-built parser that uses recursion to handle mathematical precedence and nested expressions, solving them directly without converting to other notations.

## What is Infix Notation?

Infix notation is the standard way humans write mathematical expressions, where operators (`+`, `-`, `*`, `/`) are placed *in between* the operands they act on.

*   **Infix:** `3 + 4`
*   **Prefix (Polish Notation):** `+ 3 4`
*   **Postfix (Reverse Polish Notation):** `3 4 +`

Many calculators solve infix expressions by first converting them to prefix or postfix notation, which are easier for computers to parse linearly using a stack. This calculator is different.

**This program solves infix expressions directly through a recursive bracketing method.**

## How It Works

The calculator processes expressions by enforcing the order of operations (PEMDAS/BODMAS) through a two-step recursive process:

1.  **Bracket Resolution:** The parser first identifies and isolates expressions within parentheses `()`. If brackets are nested, it recursively works its way from the innermost brackets outwards. Each bracketed expression is solved independently and replaced by its result.

2.  **Operator Precedence:** Within any given expression (whether it's the main equation or a sub-problem inside brackets), the calculator ensures operator precedence:
    *   It first scans the expression and performs all **Multiplication and Division** operations from left to right.
    *   It then takes the result of that pass and performs all **Addition and Subtraction** operations.

This is all handled by a series of functions that recursively call each other until the entire expression is reduced to a single number.

### Key Abilities

*   **Direct Infix Evaluation:** Solves expressions without converting them to prefix or postfix.
*   **Recursive Bracket Handling:** Correctly parses and solves complex nested brackets.
*   **Automatic Order of Operations:** Natively handles operator precedence (`*`, `/` before `+`, `-`).
*   **Implicit Multiplication:** The calculator is smart enough to understand implied multiplication. It automatically inserts a `*` operator in cases like `(a)(b)`, `5(a)`, and `(a)5`, making the input more natural. For example, it will treat `(5+5)(2+3)` as `(5+5)*(2+3)`.
*   **Handles Floating-Point Numbers:** Can perform calculations with decimals.

### Limitations

*   **Single File Implementation:** The entire logic is contained within `Main.java`.
*   **Basic Error Handling:** The parser expects a reasonably well-formed mathematical expression. It may not gracefully handle syntax errors like `5++5` or mismatched brackets.
*   **Limited Functionality:** This calculator is designed for the four basic arithmetic operations (`+`, `-`, `*`, `/`). It does not support exponents, roots, or other mathematical functions.

## How to Use

1.  **Compile the Code:**
    You will need a Java Development Kit (JDK) installed. Open a terminal or command prompt and run:
    ```sh
    javac Main.java
    ```

2.  **Run the Program:**
    After compiling, run the program with the following command:
    ```sh
    java Main
    ```

3.  **Enter an Equation:**
    The program will prompt you to enter an equation. Type your expression and press Enter.

    ```    ----- Enter an equation -----
    (5+5)2 + (8 / (2+2))
    ```

4.  **Get the Result:**
    The calculator will process the expression and print the final answer.
    ```
    Ans is 22.0
    -----     - Done -      -----
    ```

### Example Expressions You Can Try:

*   `8.0+(12*7+(4+8*2)/4)-6*(5+5)+4`
*   `5(5)`
*   `(10 / 2)(5 - 3)`
*   `100 / (25 * 2) * 5`
