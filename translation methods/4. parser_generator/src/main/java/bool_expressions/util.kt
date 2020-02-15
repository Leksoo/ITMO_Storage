import java.io.FileWriter
import java.lang.StringBuilder

fun printTree(
    fileWriter: FileWriter,
    tree: Tree,
    level: Int = 0,
    lastChild: Boolean = true,
    fromLastChilds: ArrayList<Boolean> = ArrayList()
) {
    val resSb = StringBuilder()
    if (level > 1) {
        for (el in fromLastChilds) {
            if (el) resSb.append("   ") else resSb.append("│  ")
        }
    }
    if (level > 0) {
        if (lastChild)
            resSb.append("└─ ")
        else
            resSb.append("├─ ")
    }
    resSb.append(tree.node)
    fileWriter.write(resSb.toString() + "\n")
    val curChildren = tree.children.filter { it.node.isNotEmpty() }
    for (i in curChildren.indices) {
        val lastChilds = ArrayList(fromLastChilds)
        if (level > 0) {
            lastChilds.add(lastChild)
        }
        if (i == curChildren.size - 1) {
            printTree(fileWriter, curChildren[i], level + 1, true, lastChilds)
        } else {
            printTree(fileWriter, curChildren[i], level + 1, false, lastChilds)
        }

    }
}