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
    private BinaryTree.Node root;

    ExpressionConversion(int exprLength) {
        infixExpression = new char[exprLength];
        postfixExpression = new char[exprLength];
        invalidChar = new char[exprLength];
        undefined = new char[exprLength];
        BinaryTree BT = new BinaryTree;
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

        //BinaryTree.Node root = new BinaryTree.Node(null);
//        int treeDepth = 0;
//        BinaryTree.Node root = new BinaryTree.Node(null);
//        for (int i = 0; i < exprLength; i++) {
//            char tmp = prefixExpression[i];
//            root = makeTree(root, tmp);
//        }
        BinaryTree.Node root = null;
        int index = 0;
        root = makeTree(prefixExpression, exprLength, index);
        infixExpression = toInfix(root, exprLength);
        postfixExpression = toPostfix(root, exprLength);

        return;
    }

    private static BinaryTree.Node makeTree(char[] expression, int exprLength, int index) {
        char tmp = expression[index];
        if (operatorCheck(tmp)) {
//            if (root == null) {
//                BinaryTree.Node n = new BinaryTree.Node(tmp);
//                return n;
//            }
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
    }

    private static char[] toPostfix(BinaryTree.Node node, int exprLength) {
        if (node == null) {
            return expression;
        }
        else {
            toPostfix(node.leftPointer, exprLength);
            toPostfix(node.rightPointer, exprLength);
        }
    }

    private static char[] toInfix(BinaryTree.Node node, int exprLength) {
        return expression;
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
