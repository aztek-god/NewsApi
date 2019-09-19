package com.sergei.news

import com.sergei.news.extension.mergeList
import com.sergei.news.extension.otherwise
import com.sergei.news.extension.then
import org.junit.Test
import java.util.*

class AlgorithmTest {

    @Test
    fun test1() {
        val treeNodeBuilder: TreeNodeBuilder<Person> = TreeNodeBuilder(personTreeNodes)

        val parentList: List<Person> = treeNodeBuilder.getParentList()

        println("parentList = $parentList")

        val person1 = parentList[0]
        val parentForIndex0 = treeNodeBuilder.getParent(person1)
        val person2 = parentList[1]
        val parentForIndex1 = treeNodeBuilder.getParent(person2)

        println("parent for index 0 = $parentForIndex0")
        println("parent for index 1 = $parentForIndex1")

        val person1ChildrenList = treeNodeBuilder.getChildList(person1)
        val person2ChildrenList = treeNodeBuilder.getChildList(person2)

        println("children from person - 1 = $person1ChildrenList")
        println("children from person - 2 = $person2ChildrenList")

//        treeNodeBuilder.childRecAction(person1) {
//            println("person1:child:name = ${it.name}")
//        }

        treeNodeBuilder.childStackAction(person1) {
            println("person1:child:name = ${it.name}")
        }
    }

    @Test
    fun test2() {
        recursion()
    }

    @Test
    fun test3() {
        val mapOf = mapOf<Int, List<String>>(
            1 to listOf("a", "b"),
            2 to listOf("a", "c"),
            3 to listOf("g", "m", "c")
        )

        val newMap = mapOf.swap()

        println(newMap)
    }

    fun <K, V> Map<K, List<V>>.swap(): Map<V, List<K>> {
        val mergedList: List<V> = this.mergeList()
        val returnMap = mutableMapOf<V, List<K>>()

        mergedList.forEach {
            val list: List<K> = this._do(it)

            returnMap.put(it, list)
        }

        return returnMap
    }

    private fun <K, V> Map<K, List<V>>._do(listItem: V): List<K> {
        val returnList = mutableListOf<K>()

        forEach { key: K, list: List<V> ->
            if (list.contains(listItem)) {
                returnList.add(key)
            }
        }

        return returnList
    }

    private fun recursion() {
        var counter = 0

        tailrec fun rec() {
            if (counter == 10) {
                println("exit")
                return
            } else {
                println("increase")
                counter++
            }

            println("continue")
            rec()
        }

        rec()
    }
}

interface TreeNode {
    val id: Long
    val parentId: Long


    companion object {
        const val NO_PARENT = (-1).toLong()
    }
}

data class Person(
    val name: String,
    override val id: Long,
    override val parentId: Long = TreeNode.NO_PARENT
) :
    TreeNode

val personTreeNodes = listOf(
    Person("Sergei", id = 1),
    Person("Alex", id = 2),
    Person("Dmitrii", id = 3, parentId = 1),
    Person("Ivan", id = 4, parentId = 3),
    Person("Olga", id = 5, parentId = 2),
    Person("Dasha", id = 6, parentId = 3),
    Person("Mitia", id = 7, parentId = 1),
    Person("Elena", id = 8, parentId = 3),
    Person("Dina", id = 9, parentId = 8),
    Person("Diana", id = 10, parentId = 7)
)

class TreeNodeBuilder<T : TreeNode>(private val treeNodeList: List<T>) {
    private val mParentIdMap: Map<Long, List<T>> = run {
        treeNodeList.groupBy { it.parentId }
    }

    fun getChildList(node: T): List<T> = mParentIdMap[node.id] ?: emptyList()

    fun getParent(node: T): T? = treeNodeList.find { it.id == node.parentId }

    fun isLeaf(node: T): Boolean {
        return getChildList(node).isEmpty()
    }

    fun isParent(node: T): Boolean {
        return getParent(node) == null
    }

    fun getParentList(): List<T> {
        if (treeNodeList.isEmpty()) {
            return emptyList()
        }

        val parentList = mutableListOf<T>()

        treeNodeList.forEach {
            isParent(it) then {
                parentList += it
            } otherwise {

            }
        }

        return parentList
    }

    fun childRecAction(parent: T, action: (T) -> Unit) {

        val childList: List<T> = getChildList(parent)

        fun recursion(childrenList: List<T>) {

            for (node in childrenList) {

                action(node)

                recursion(getChildList(node))
            }
        }

        recursion(childList)
    }

    fun childStackAction(parent: T, action: (T) -> Unit) {
        val stack = Stack<T>()
        stack.push(parent)
        stack.addAll(getChildList(parent))

        while (stack.peek() != parent) {
            val popItem = stack.pop()
            action(popItem)

            stack.addAll(getChildList(popItem))
        }

    }

    @Test
    fun simpleNumbersTest() {
        for (number in 0..100) {
            if (number.isSimple()) {
                println("number = $number")
            }
        }
    }

    fun Int.isSimple(): Boolean {
        if (this == 2 || this == 3 || this == 5 || this == 7) {
            return true
        }

        if (this % 2 != 0 || this % 3 != 0 || this % 5 != 0 || this % 7 != 0) {
            return true
        }

        return false
    }

}

