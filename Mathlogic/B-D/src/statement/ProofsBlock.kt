package statement

import expression.Expression

class ProofsBlock {
    val proofList: List<Expression>
    val size: Int
    var resultExpr: Expression? =null
    val indexOfBlock: Int

    constructor(
        templatesList: List<Expression>,
        indexOfBlock: Int,
        letterToExpr: Map<String, Expression>
    ) {
        val tmp = ArrayList<Expression>()
        size = templatesList.size
        this.indexOfBlock = indexOfBlock
        for (expr in templatesList) {
            tmp.add(expr.getNewExpr(letterToExpr))
        }
        if(!tmp.isEmpty()) {
            resultExpr = tmp.last()
        }
        proofList = tmp
    }

    constructor(
       proofList:List<Expression>,
       indexOfBlock: Int
    ) {
        size = proofList.size
        this.proofList = proofList
        this.indexOfBlock = indexOfBlock
        if(!proofList.isEmpty()) {
            resultExpr = proofList.last()
        }
    }

    init {

    }

    override fun toString() = proofList.joinToString("\n") { it.toStr() }


}
