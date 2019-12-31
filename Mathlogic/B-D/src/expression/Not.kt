package expression

class Not(right: Expression) : UnaryOperator(right, "!") {
    override fun getNewExpr(letterToExpr: Map<String, Expression>): Expression {
        return Not(right.getNewExpr(letterToExpr))
    }
}