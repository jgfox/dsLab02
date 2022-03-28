/*
 * Jonathan Fox
 * 64CB42
 * jfox49@jhu.edu
 */
:w

public class ExpressionConversion {

    /*
    invalidChar : array for the invalid characters
    undefined : array for all the undefined operands due to expression balance
     */
    private static char[] infixExpression;
    private static char[] postfixExpression;
    private static char[] invalidChar;
    private static char[] undefined;
    private static int invalidCount;
    private static int undefinedCount;
    private static int exprIndex;
    private BinaryTree.Node root;

    ExpressionConversion(int exprLength) {
        infixExpression = new char[exprLength];
        postfixExpression = new char[exprLength];
        invalidChar = new char[exprLength];
        undefined = new char[exprLength];
        BinaryTree BT = new BinaryTree();
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

    public static void expressionConverter(char[] prefixExpression, int exprLength) {

        int invalidCount = 0;
        int undefinedCount = 0;

        BinaryTree.Node root = null;
        int index = 0;
        root = makeTree(prefixExpression, exprLength, index);
        exprIndex = 0;
        toInfix(root, exprLength);
        exprIndex = 0;
        toPostfix(root, exprLength);
        return;
    }

    private static BinaryTree.Node makeTree(char[] expression, int exprLength, int index) {
        char tmp = expression[index];
        if (operatorCheck(tmp)) {
            BinaryTree.Node currentNode = new BinaryTree.Node(tmp);
            currentNode.leftPointer = makeTree(expression, exprLength, index++);
            currentNode.rightPointer = makeTree(expression, exprLength, index++);
            return currentNode;

        } else if (Character.isLetterOrDigit(tmp)) {
            BinaryTree.Node currentNode = new BinaryTree.Node(tmp);
            return currentNode;

        } else if (index == exprLength) {
            return null;

        } else {
            invalidChar[invalidCount] = tmp;
            invalidCount++;
            try {
                throw new Exception("Unbalanced prefix expression");
            } catch (Exception e) {
                e.printStackTrace();
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
            toPostfix(node.leftPointer, exprLength);
            infixExpression[exprIndex] = (char) node.data;
            toPostfix(node.rightPointer, exprLength);
            exprIndex++;
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
                return true;
        }
        return false;
    }
}
