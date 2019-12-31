package expression

class Or(left : Expression, right: Expression) : BinaryOperator(left, right, "|") {
    override fun getNewExpr(letterToExpr: Map<String, Expression>): Expression {
        return Or(left.getNewExpr(letterToExpr),right.getNewExpr(letterToExpr))
    }
}