package lab1

class LRUCache<K, V>(
    private val capacity: Int
) {
    private val hashMap = HashMap<K, Node>(capacity)
    private var startNode: Node? = null
    private var endNode: Node? = null

    val size: Int
        get() {
            // PRE
            assert(hashMap.size <= capacity) { "actual size must not exceed capacity" }

            return hashMap.size
        }

    init {
        assert(capacity > 0) { "cache capacity should be more than 0" }
    }

    fun put(key: K, item: V) {
        // PRE
        assert(size <= capacity) { "actual size must not exceed capacity" }
        val oldSize = size

        getNode(key)?.apply {
            this.item = item
        } ?: run {
            val newNode = Node(key, item)
            if (size == capacity) {
                endNode?.let { end ->
                    hashMap.remove(end.key)
                    removeNode(end)
                }
            }
            updateNode(newNode)
            hashMap[key] = newNode
        }

        // POST - new key was added
        assert(hashMap.containsKey(key)) { "added/updated item is present in cache" }
        assert(size <= capacity) { "capacity was not exceeded after adding/updating item" }
        assert(startNode?.item == item) { "added/updated item is most recent now" }
        assert(hashMap.containsKey(key)) { "added/updated item is present in cache" }
    }

    fun get(key: K): V? {
        // PRE
        assert(size <= capacity) { "actual size must not exceed capacity" }
        val oldSize = size
        val oldStartItem = startNode?.item

        val result = getNode(key)?.item

        // POST
        assert(size <= capacity) { "actual size must not exceed capacity" }
        assert(size == oldSize) { "new entry was not added" }
        assert(
            result == null && oldStartItem == startNode?.item || startNode?.item == result
        ) { "received item is most recent now" }
        assert(
            result == null && oldStartItem == startNode?.item || startNode?.key == key
        ) { "requested item is most recent now" }

        return result
    }

    fun remove(key: K): V? {
        // PRE
        assert(size <= capacity) { "actual size must not exceed capacity" }
        val oldSize = size

        val result = hashMap.remove(key)?.let { node ->
            removeNode(node)
            node.item
        }

        // POST
        assert(!hashMap.containsKey(key)) { "item with given key is no longer in cache" }
        assert(size <= oldSize) { "size was decreased or not changed" }
        assert(size <= capacity) { "actual size must not exceed capacity" }

        return result
    }

    fun clear() {
        // PRE
        assert(size <= capacity) { "actual size must not exceed capacity" }

        hashMap.clear()
        startNode = null
        endNode = null

        // POST
        assert(size == 0) { "cache is empty" }
        assert(startNode == endNode && startNode == null) { "cache is empty" }
    }

    private fun getNode(key: K): Node? {
        return hashMap[key]?.let { node ->
            removeNode(node)
            updateNode(node)
            node
        }
    }

    private fun removeNode(node: Node) {
        node.left?.apply {
            right = node.right
        } ?: run { startNode = node.right }
        node.right?.apply {
            left = node.left
        } ?: kotlin.run { endNode = node.left }
    }

    private fun updateNode(node: Node) {
        node.left = null
        node.right = startNode
        startNode?.apply {
            left = node
        }
        startNode = node
        if (endNode == null) {
            endNode = node
        }
    }

    inner class Node(
        val key: K,
        var item: V,
        var left: Node? = null,
        var right: Node? = null
    )
}