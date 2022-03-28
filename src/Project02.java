/*
 * Jonathan Fox
 * 64CB42
 * jfox49@jhu.edu
 */

import java.io.*;

public class Project02 {

    public static void main(String[] args) throws IOException {

        FileReader inputStream = null;
        FileWriter outputStream = null;
        int whiteSpaceAccept;  // this variable is used to set user inputted value of acceptable continuous whitespace.

        try {
            inputStream = new FileReader(args[0]); // input file
            outputStream = new FileWriter(args[1]); // output file
            whiteSpaceAccept = Integer.parseInt(args[2]); // acceptable number of whitespaces
        } catch (Exception ioe) {
            System.err.println(ioe);
            System.out.println("Recheck arguments, there should be three:");
            System.out.println("Input file with path;");
            System.out.println("Output file with path;");
            System.out.println("Number of acceptable whitespaces that are continuous in the expression.");
            return;
        }

        int c;  // scanned character variable
        int whiteSpaceCount = 0;  // counter for continuous white space
        int exprLength = 0;  // counter to track expression length
        /*
         Stack has an arbitrary max of thousand characters on input.
         For this to be full, that would mean a thousand characters in
         the prefix expression not including line breaks.
         */
        char[] prefixExpression = new char[1000];

        while ((c = inputStream.read()) != -1) { // read and process one character
            if (exprLength == 0) {
                /*
                 Every new prefix expression begins with a zero counter
                 */
            }
            if (c != '\n') {
                if (c == ' ') {
                    whiteSpaceCount++;  // track continuous whitespace
                } else {
                    prefixExpression[exprLength] = (char) c;
                    ++exprLength;
                    whiteSpaceCount = 0; // whitespace counter resets when a character is found
                }
            }
            /*
            If the file has a new line character or the white space counter exceeds user
            specifications then the expression is considered ended and is passed to the
            Expression Conversion class.
            A new stack is declared for each expression and passed.
            There is then four character arrays afterwards:
            invalidChar : used for grabbing invalid characters that couldn't be parsed in
                          the conversion process.
            undefined :   undefined contains operands that were left over as the result of
                          an unbalanced expression.
            output :      the postfix expression, it is popped off the stack
            lostTerms :   used to get the leftover terms that couldn't be balanced correctly
                          due to an unbalanced expression.
             */
            if (c == '\n' || whiteSpaceCount > whiteSpaceAccept) {
                if (exprLength != 0) {
                    ExpressionConversion ec = new ExpressionConversion(exprLength);
                    ExpressionConversion.expressionConverter(prefixExpression, exprLength);
                    char[] infixExpression = ExpressionConversion.getInfix();
                    char[] postfixExpression = ExpressionConversion.getPostfix();
                    char[] invalidChar = ExpressionConversion.getInvalid();
                    char[] undefined = ExpressionConversion.getUndefined();
                    char[] lostTerms = new char[0];
                    /*
                    The following will make sure the output stack doesn't have any unexpressed terms
                    left in case the fed prefix expression was unbalanced.
                     */
//                    if (!outputArray.isEmpty()) {
//                        int currentIndex = 0;
//                        lostTerms = new char[exprLength - output.length];
//                        while (!outputStack.isEmpty()) {
//                            char[] term = outputStack.pop();
//                            for (int i = 0; i < term.length; i++) {
//                                lostTerms[currentIndex] = term[i];
//                                currentIndex++;
//                            }
//                        }
//                    }

                    /*
                    Writing everything to file.
                     */

                    outputStream.write("Prefix: ");
                    outputStream.write(prefixExpression);
                    outputStream.write('\n');
                    outputStream.write("Infix: ");
                    if (infixExpression != null) {
                        outputStream.write(infixExpression);
                        outputStream.write('\n');
                    } else {
                        outputStream.write("Invalid Expression");
                        outputStream.write('\n');
                    }
                    outputStream.write("Postfix: ");
                    if (postfixExpression != null) {
                        outputStream.write(postfixExpression);
                        outputStream.write('\n');
                    } else {
                        outputStream.write("Invalid Expression");
                        outputStream.write('\n');
                    }
                    if (lostTerms != null) {
                        outputStream.write("Lost Terms: ");
                        outputStream.write(lostTerms);
                        outputStream.write('\n');
                    }
                    if (invalidChar != null) {
                        outputStream.write("Invalid Characters: ");
                        outputStream.write(invalidChar);
                        outputStream.write('\n');
                    }
                    if (undefined != null) {
                        outputStream.write("Undefined Operators: ");
                        outputStream.write(undefined);
                        outputStream.write('\n');
                    }
                    exprLength = 0;
                    whiteSpaceCount = 0;
                    outputStream.write('\n');
                }
            }
        }

        try {
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
        } catch (Exception x) {
            System.err.println(x);
        }
        return;
    }
}