package expression

class Impl(left : Expression, right: Expression) : BinaryOperator(left, right, "->") {
    override fun getNewExpr(letterToExpr: Map<String, Expression>): Expression {
        return Impl(left.getNewExpr(letterToExpr),right.getNewExpr(letterToExpr))
    }
}