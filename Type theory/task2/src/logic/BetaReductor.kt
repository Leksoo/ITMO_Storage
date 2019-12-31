package logic

import expression.*
import parse

class BetaReductor {
    lateinit var lambda: Expression
    val steps = ArrayList<String>()
    fun process(input: String, number: Int, step: Int): List<String> {
        lambda = parse(input)
        steps.add(lambda.toString())
        var counter = 0
        if (number == 0) {
            return steps
        }
        do {
            state = State.FINISH
            lambda = go(lambda)

            if(state == State.FINISH){
                if(counter % step != 0){
                    steps.add(lambda.toString())
                }
                break
            }
            if(number == counter){
                break
            }
            counter++
            if (counter % step == 0) {
                steps.add(lambda.toString())
            }

        } while (true)
        return steps
    }

    var nameCounter = 0
    private fun getNewName() = "l'2s$nameCounter".also { nameCounter++ }
    private fun renameAll(
        expr: Expression,
        chained: Map<String, String>
    ) {
        when (expr) {
            is Abstr -> {
                val newChained = HashMap(chained)
                var newName = getNewName()
                newChained[expr.str.str] = newName
                expr.str.str = newChained[expr.str.str]!!
                renameAll(expr.right, newChained)
            }
            is Apply -> {
                renameAll(expr.left, chained)
                renameAll(expr.right, chained)
            }
            is Variable -> {
                if (chained.containsKey(expr.str)) {
                    expr.str = chained[expr.str]!!
                }

            }
            is Wrapper -> {
                renameAll(expr.expr, chained)
            }
        }
    }

    enum class State {
        FINISH, AGAIN
    }

    var state = State.FINISH
    private fun go(cur: Expression): Expression {
        when (cur) {
            is Abstr -> {
                cur.right = go(cur.right)
                return cur
            }
            is Apply -> {
                var left = cur.left
                while (left is Wrapper) {
                    left = left.expr
                }
                if (left is Abstr) {
                    state = State.AGAIN
                    return reduct(left.copy(), cur.right)
                } else {
                    cur.left = go(cur.left)
                    if (state == State.AGAIN) {
                        return cur
                    }
                    cur.right = go(cur.right)
                    return cur
                }
            }
            is Variable -> {
                return cur
            }
            is Wrapper -> {
                while (cur.expr is Wrapper){
                    cur.expr = (cur.expr as Wrapper).expr
                }
                cur.expr = go(cur.expr)
                return cur
            }
        }
        throw NullPointerException()
    }

    private fun reduct(lambda: Abstr, right: Expression): Expression {
        renameAll(lambda, HashMap())
        return substitute(lambda.right, lambda.str, if (right is Wrapper) right else Wrapper(right))
    }

    private fun substitute(inside: Expression, what: Variable, to: Wrapper): Expression {
        when (inside) {
            is Abstr -> {
                if (inside.str == what) {
                    return inside
                } else {
                    return Abstr(inside.str, substitute(inside.right, what, to))
                }
            }
            is Apply -> {
                return Apply(
                    substitute(inside.left, what, to),
                    substitute(inside.right, what, to)
                )
            }
            is Variable -> {
                if (inside == what) {
                    return to
                }
                return inside
            }
            is Wrapper -> {
                //if (inside.expr.contains(what)) {
                return substitute(inside.expr, what, to)
                //}
                //return inside
            }
        }
        throw NullPointerException()
    }
}