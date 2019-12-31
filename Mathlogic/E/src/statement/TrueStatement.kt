package statement

open class TrueStatement(
    str : String,
    val indexInList: Int
    ) : Statement(str){

    open fun canSetInProof(proof : Proof ) : Boolean{
        return true
    }

    open fun setParamsToProof(proof: Proof) {
    }
}