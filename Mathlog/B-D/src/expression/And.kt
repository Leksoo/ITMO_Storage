package expression

class And(left : Expression, right: Expression) : BinaryOperator(left, right, "&") {

    override fun getNewExpr(letterToExpr: Map<String, Expression>): Expression {
        return And(left.getNewExpr(letterToExpr),right.getNewExpr(letterToExpr))
    }
}