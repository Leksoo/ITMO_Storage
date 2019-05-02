import exceptions.ValidationProofException
import processor.INCORRECT_PROOF_MESSAGE
import java.io.BufferedReader

fun getHypothesisesAndTarget(statement: String): Pair<List<String>, String> {
    val splitByTurnstile = statement.split("|-")
    if (splitByTurnstile.size != 2) {
        throw ValidationProofException(INCORRECT_PROOF_MESSAGE)
    }
    return Pair(if (splitByTurnstile[0] == "") emptyList() else splitByTurnstile[0].split(","), splitByTurnstile[1])
}

fun getProofLines(inp : BufferedReader) : List<String>{
    val proofLines = ArrayList<String>()
    var line = inp.readLine()
    while (line != null && line != "ex") {
        proofLines.add(line)
        line = inp.readLine()
    }
    return proofLines
}