/*
 * Jonathan Fox
 * 64CB42
 * jfox49@jhu.edu
 */

public class ExpressionConversion {

    /*
    infixExpression : array for infix expression
    postfixExpression : array for postfix expression
    invalidChar : array for the invalid characters
    undefined : array for all the undefined operands due to expression balance
    lostTerms : array for terms that can't be evaluated due to expression balance
    invalidCount : count for invalids for indexing invalid array
    undefinedCount : count for undefined for indexing undefined operators
    exprIndex : count for indexing infix and postfix, eventually used for lostTerms
     */
    private static char[] infixExpression;
    private static char[] postfixExpression;
    private static char[] invalidChar;
    private static char[] undefined;
    private static char[] lostTerms;
    private static int invalidCount;
    private static int undefinedCount;
    private static int exprIndex;

    // initializing method
    ExpressionConversion(int exprLength) {
        infixExpression = new char[exprLength];
        postfixExpression = new char[exprLength];
        invalidChar = new char[exprLength];
        undefined = new char[exprLength];
        lostTerms = new char[exprLength];
        invalidCount = 0;
        undefinedCount = 0;
    }
    // return methods to avoid abuse of global variables
    public static char[] getInfix() {
        return infixExpression;
    }
    public static char[] getPostfix() {
        return postfixExpression;
    }
    public static char[] getInvalid() {
        return invalidChar;
    }
    public static char[] getUndefined() {
        return undefined;
    }
    public static char[] getLostTerms() {
        return lostTerms;
    }

    public static void expressionConverter(char[] prefixExpression, int exprLength) {

        BinaryTree.Node root;
        exprIndex = 0;
        root = makeTree(prefixExpression, exprLength);
        exprIndex = 0;
        toInfix(root, exprLength);
        exprIndex = 0;
        toPostfix(root, exprLength);

        /*
        The following does a check to see if the entire prefix term was processed
        by checking the class exprIndex variable is less than that of the prefix
        expression length (+1 due to Java indexing)
        If the criteria is met then each remaining character of the prefix expression
        is checked for categorization into respective arrays
        Specific error handling isn't included as the accessed portion will not be a
        part of the expression
         */
        if (exprIndex + 1 < exprLength) {
            int loopIndex = 0;
            while (exprIndex < exprLength) {
                char tmp = prefixExpression[exprIndex++];
                if (notSupportedOperators(tmp)) {
                    undefined[undefinedCount] = tmp;
                    undefinedCount++;
                } else if (operatorCheck(tmp) || Character.isLetterOrDigit(tmp)) {
                    lostTerms[loopIndex] = tmp;
                    loopIndex++;
                } else {
                    invalidChar[invalidCount] = tmp;
                    invalidCount++;
                }
            }
            try {
                throw new Exception("Unbalanced prefix expression, unevaluated terms detected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return;
    }

    /*
    makeTree is a recursive method that generates the binary tree as long as there is a balanced
    path for it.  If an expression is unbalanced then the tree won't capture all terms of the
    expression.
     */
    private static BinaryTree.Node makeTree(char[] expression, int exprLength) {
        while (exprIndex < exprLength) {
            char tmp = expression[exprIndex];
            exprIndex++;
            if (operatorCheck(tmp)) {
                BinaryTree.Node currentNode = new BinaryTree.Node(tmp);
                currentNode.leftPointer = makeTree(expression, exprLength);
                currentNode.rightPointer = makeTree(expression, exprLength);
                return currentNode;

            } else if (Character.isLetterOrDigit(tmp)) {
                BinaryTree.Node currentNode = new BinaryTree.Node(tmp);
                return currentNode;

            } else if (notSupportedOperators(tmp)) {
                undefined[undefinedCount] = tmp;
                undefinedCount++;
                try {
                    throw new Exception("Unbalanced expression, unsupported operator detected");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                invalidChar[invalidCount] = tmp;
                invalidCount++;
                try {
                    throw new Exception("Unbalanced prefix expression");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    private static void toPostfix(BinaryTree.Node node, int exprLength) {
        if (node == null) {
            return;
        }
        else {
            toPostfix(node.leftPointer, exprLength);
            toPostfix(node.rightPointer, exprLength);
            postfixExpression[exprIndex] = (char) node.data;
            exprIndex++;
        }
    }

    private static void toInfix(BinaryTree.Node node, int exprLength) {
        if (node == null) {
            return;
        }
        else {
            toInfix(node.leftPointer, exprLength);
            infixExpression[exprIndex] = (char) node.data;
            exprIndex++;
            toInfix(node.rightPointer, exprLength);
        }
    }

    /*
    This method provides a place to put accepted operators and return a boolean
    value whether they are allowed or not.
     */
    private static boolean operatorCheck(char operator) {
        switch(operator) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '$':
            //case '^':
                return true;
        }
        return false;
    }
    /*
    this method has operators or portions of operators that are not supported
     */
    private static boolean notSupportedOperators(char operator) {
        switch(operator) {
            case '!': // factorial
            case '%': // modulus would be nice to have
            case '^': // using old fashioned sigil for exponentiation instead
            case '<': // bitwise shifts could be supported with the right code
            case '>': // same as above
                return true;
        }
        return false;
    }
}
