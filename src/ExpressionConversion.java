/*
 * Jonathan Fox
 * 64CB42
 * jfox49@jhu.edu
 */

public class ExpressionConversion {

    /*
    invalidChar : array for the invalid characters
    undefined : array for all the undefined operands due to expression balance
     */
    private static char[] infixExpression;
    private static char[] postfixExpression;
    private static char[] invalidChar;
    private static char[] undefined;
    private static char[] lostTerms;
    private static int invalidCount;
    private static int undefinedCount;
    private static int exprIndex;

    ExpressionConversion(int exprLength) {
        infixExpression = new char[exprLength];
        postfixExpression = new char[exprLength];
        invalidChar = new char[exprLength];
        undefined = new char[exprLength];
        lostTerms = new char[exprLength];
    }
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

        int invalidCount = 0;
        int undefinedCount = 0;

        BinaryTree.Node root = null;
        exprIndex = 0;
        root = makeTree(prefixExpression, exprLength);
        exprIndex = 0;
        toInfix(root, exprLength);
        exprIndex = 0;
        toPostfix(root, exprLength);

        /*
        The following does a check to see if the entire prefix term was processed
        by checking the class exprIndex variable is at the same value of as the
        prefix expression length (+1 due to Java indexing)
        If the criteria is met then
         */
        if (exprIndex + 1 >= exprLength) {
            int loopIndex = 0;
            while (exprIndex < exprLength) {
                lostTerms[loopIndex] = prefixExpression[++exprIndex];
                loopIndex++;
            }
        }
        return;
    }

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
    private static boolean notSupportedOperators(char operator) {
        switch(operator) {
            case '!': // too hard to implement the logic behind this one
            case '%': // modulus would be nice to have
            case '^': // using old fashioned sigil for exponentiation instead
            case '<': // bitwise shifts could be supported with the right code
            case '>': // same as above
                return true;
        }
        return false;
    }
}
