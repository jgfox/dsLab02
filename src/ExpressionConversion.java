public class ExpressionConversion {

    /*
    invalidChar : array for the invalid characters
    undefined : array for all the undefined operands due to expression balance
     */
    private static char[] invalidChar;
    private static char[] undefined;
    private static int invalidCount;
    private static int undefinedCount;

    ExpressionConversion(int exprLength) {
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

    public static char[] expressionConverter(char[] prefixExpression, int exprLength) {

        int invalidCount = 0;
        int undefinedCount = 0;

        //BinaryTree.Node root = new BinaryTree.Node(null);
        int treeDepth = 0;
        BinaryTree.Node root = new BinaryTree.Node(null);
        for (int i = 0; i < exprLength; i++) {
            char tmp = prefixExpression[i];
            root = makeTree(root, tmp);
        }

        return;
    }

    private static BinaryTree.Node makeTree(BinaryTree.Node root, char tmp) {

        if (operatorCheck(tmp)) {
            if (root == null) {
                BinaryTree.Node n = new BinaryTree.Node(tmp);
                return n;
            }



        } else if (Character.isLetterOrDigit(tmp)) {

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

    private char[] toPostfix() {
        return expression;
    }

    private char[] toInfix() {
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
