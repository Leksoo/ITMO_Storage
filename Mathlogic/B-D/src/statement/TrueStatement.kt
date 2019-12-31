package statement

import expression.Expression

open class TrueStatement : Statement{
    val indexInList:Int

    constructor(str: String, indexInList: Int) : super(str) {
        this.indexInList = indexInList
    }

    constructor(expr: Expression, indexInList: Int) : super(expr) {
        this.indexInList = indexInList
    }

    open fun canSetInProof(proof : Proof ) : Boolean{
        return true
    }

    open fun setParamsToProof(proof: Proof) {
    }
}