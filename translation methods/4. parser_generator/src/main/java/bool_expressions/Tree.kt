import java.lang.StringBuilder



class Tree(val node: String = "", val children: Array<Tree> = emptyArray()) {


    override fun toString(): String {
        if (children.isEmpty()) {
            return node
        }
        val res = StringBuilder()
        for (child in children) {
            res.append(child.toString())
        }
        return res.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tree

        if (node != other.node) return false
        if (!children.contentEquals(other.children)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = node.hashCode()
        result = 31 * result + children.contentHashCode()
        return result
    }
}