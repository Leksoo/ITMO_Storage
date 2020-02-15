package generator

import java.io.FileWriter
import java.nio.file.Path

class Writer (
     path : String
) : AutoCloseable{
    private val fileWriter = FileWriter(path)
    override fun close() {
        fileWriter.close()
    }

    private val TAB = "    "

    fun printTabs (count : Int){
        repeat(count){
            fileWriter.write(TAB)
        }
    }

    fun print(tabs : Int, str : String){
        printTabs(tabs)
        fileWriter.write(str)
    }

    fun print(str : String){
        fileWriter.write(str)
    }

    fun println(tabs : Int, str : String){
        print(tabs,str)
        print("\n")
    }

    fun println(str : String){
        print(str)
        print("\n")
    }

}