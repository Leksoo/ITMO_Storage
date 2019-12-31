package type

class TypeInfo(val type: Type, val system: ArrayList<Pair<Type, Type>>, val rule: String) {
    fun getSolvedSystem(): Map<TypeVar, Type>? {
        val sys = ArrayList<Triple<Type,Type,Substed>>()
        system.forEach { sys.add(Triple(it.first.copy(),it.second.copy(),Substed(false))) }
       // val res =HashMap<TypeVar,Type>()
        while (true) {
            var done = false
            for (i in sys.indices) {
                val cur = sys[i]
                val l = cur.first
                val r = cur.second
                if (l is Impl && r is TypeVar) {
                    sys[i] = Triple(r, l,Substed(false))
                    done = true
                } else if (l is TypeVar && l == r) {
                    sys.removeAt(i)
                    done = true
                } else if (l is Impl && r is Impl) {
                    sys[i] = Triple(l.left, r.left, cur.third)
                    sys.add(Triple(l.right, r.right,Substed(false)))
                    done = true
                } else if (l is TypeVar) {
                    if (r.contains(l)) {
                        return null
                    }
                    if(!cur.third.a) {
                        cur.third.a = true
                        for (j in sys.indices) {
                            if (j != i) {
                                val subst1 = sys[j].first.substitute(l, r)
                                val subst2 = sys[j].second.substitute(l, r)
                                if (subst1.second || subst2.second) {
                                    sys[j] = Triple(subst1.first, subst2.first,sys[j].third)
                                    done = true
                                }
                            }
                        }
                    }
                }
                if (done) break
            }

            if (done) {
                continue
            } else {
//                for (j in sys.indices) {
//                    if (sys[j].second.contains(sys[j].first as TypeVar)) {
//                        return null
//                    }
//                }
                break
            }
        }
        return sys.map { Pair(it.first as TypeVar, it.second) }.toMap()
    }

    internal class Substed(var a : Boolean)
}