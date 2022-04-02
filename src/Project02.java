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
         char array has an arbitrary max of thousand characters on input.
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
            else if (whiteSpaceCount > whiteSpaceAccept) {
                if (exprLength != 0) {
                    ExpressionConversion ec = new ExpressionConversion(exprLength);
                    /**
                     * What you've done here:
                     * ExpressionConversion.expressionConverter(prefixExpression, exprLength);
                     * is execute a function on a one-time instance of ExpressionConverter, rather
                     * than on the local class instance "ec" you created above. You want to use that
                     * local instance instead, and that way you don't have to use static vars. Then you
                     * have access to all its internal variables through getters/setters.
                     */

                    /**
                     * You could also just avoid using these local variables too since they're already
                     * stored in your local ExpressionConversion instance. If you kept them, you'd be
                     * storing duplicate data and wasting memory.
                     */
                    /*
                    char[] infixExpression = ec.getInfix();
                    char[] postfixExpression = ec.getPostfix();
                    char[] invalidChar = ec.getInvalid();
                    char[] undefined = ec.getUndefined();
                    char[] lostTerms = ec.getLostTerms();
                    */

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
                    if (ec.getInfix() != null) {
                        outputStream.write(ec.getInfix());
                        outputStream.write('\n');
                    } else {
                        outputStream.write("Invalid Expression");
                        outputStream.write('\n');
                    }
                    outputStream.write("Postfix: ");
                    if (ec.getPostfix() != null) {
                        outputStream.write(ec.getPostfix());
                        outputStream.write('\n');
                    } else {
                        outputStream.write("Invalid Expression");
                        outputStream.write('\n');
                    }
                    if ( ec.getLostTerms() != null) {
                        outputStream.write("Lost Terms: ");
                        outputStream.write( ec.getLostTerms());
                        outputStream.write('\n');
                    }
                    if (ec.getInvalid() != null) {
                        outputStream.write("Invalid Characters: ");
                        outputStream.write(ec.getInvalid());
                        outputStream.write('\n');
                    }
                    if (ec.getUndefined() != null) {
                        outputStream.write("Undefined Operators: ");
                        outputStream.write(ec.getUndefined());
                        outputStream.write('\n');
                    }
                    /*
                    Clearing out variables and arrays used.
                    prefixExpression is declared outside the while loop but retains values
                    Re-declaring it clears all values in case the next term is shorter.
                     */
                    exprLength = 0;
                    whiteSpaceCount = 0;
                    prefixExpression = new char[1000];
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