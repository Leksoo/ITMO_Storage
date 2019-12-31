import gen.antlr.JavaClassBaseVisitor
import gen.antlr.JavaClassParser
import structure.*
import java.lang.IllegalArgumentException

class ClassVisitor : JavaClassBaseVisitor<JavaClass>() {
    private val fieldVisitor = FieldVisitor()
    private val methodVisitor = MethodVisitor()
    override fun visitClassDeclaration(ctx: JavaClassParser.ClassDeclarationContext): JavaClass {
        return JavaClass(
            ctx.JAVA_NAME().text,
            ctx.classModifier().text,
            ctx.classSuperClass()?.JAVA_NAME()?.text ?: "",
            ctx.classInterfaces()?.JAVA_NAME()?.map { it.text }?.toList() ?: listOf(),
            ctx.classInside().field().map { it.accept(fieldVisitor) }.toList(),
            ctx.classInside().method().map { it.accept(methodVisitor) }.toList()
        )
    }
}

class FieldVisitor : JavaClassBaseVisitor<JavaItem.Variable>() {
    private val variableVisitor = VariableBodyVisitor()
    override fun visitField(ctx: JavaClassParser.FieldContext): JavaItem.Variable {
        return JavaItem.Variable(
            getModifiers(ctx.modifier()),
            ctx.variable().accept(variableVisitor)
        )
    }
}

class MethodVariableVisitor : JavaClassBaseVisitor<JavaItem.Variable>() {
    private val variableVisitor = VariableBodyVisitor()
    override fun visitMethodVariable(ctx: JavaClassParser.MethodVariableContext): JavaItem.Variable {
        return JavaItem.Variable(
            ctx.finalModifier().text,
            ctx.variable().accept(variableVisitor)
        )
    }
}

class VariableBodyVisitor : JavaClassBaseVisitor<VariableBody>() {
    private val assignVisitor = AssignVisitor()
    override fun visitVariable(ctx: JavaClassParser.VariableContext): VariableBody {
        return VariableBody(
            ctx.type().text, ctx.name().text, ctx.assign()?.accept(assignVisitor)
        )
    }
}

class AssignVisitor : JavaClassBaseVisitor<Assign>() {
    private val funcCallVisitor = FuncCallVisitor()
    override fun visitAssign(ctx: JavaClassParser.AssignContext): Assign {
        return if (ctx.funcCall() == null) Assign(ctx.text) else Assign(ctx.funcCall().accept(funcCallVisitor))
    }
}

class FuncCallVisitor : JavaClassBaseVisitor<JavaItem.FuncCall>() {
    private val paramsVisitor = ParamsVisitor()
    override fun visitFuncCall(ctx: JavaClassParser.FuncCallContext): JavaItem.FuncCall {
        return JavaItem.FuncCall(
            ctx.NEW()?.text ?: "",
            ctx.name().text,
            ctx.params()?.accept(paramsVisitor) ?: listOf()
        )
    }

    override fun visitMethodFuncCall(ctx: JavaClassParser.MethodFuncCallContext): JavaItem.FuncCall {
        return ctx.funcCall().accept(this)
    }
}

class ParamsVisitor : JavaClassBaseVisitor<List<Param>>() {
    private val paramVisitor = ParamVisitor()
    override fun visitParams(ctx: JavaClassParser.ParamsContext): List<Param> {
        return ctx.param().map { it.accept(paramVisitor) }.toList()

    }
}

class ParamVisitor : JavaClassBaseVisitor<Param>() {
    override fun visitParam(ctx: JavaClassParser.ParamContext): Param {
        return Param(ctx.name().text)
    }
}

class ArgumentsVisitor : JavaClassBaseVisitor<List<Argument>>() {
    private val argumentVisitor = ArgumentVisitor()
    override fun visitArguments(ctx: JavaClassParser.ArgumentsContext): List<Argument> {
        return ctx.argument().map { it.accept(argumentVisitor) }.toList()

    }
}

class MethodVisitor : JavaClassBaseVisitor<Method>() {
    private val methodBodyVisitor = MethodBodyVisitor()
    override fun visitMethod(ctx: JavaClassParser.MethodContext): Method {
        val argumentsVisitor = ArgumentsVisitor()
        return Method(
            getModifiers(ctx.modifier()),
            ctx.type()?.text ?: "",
            ctx.name().text,
            ctx.arguments()?.accept(argumentsVisitor) ?: listOf(),
            ctx.methodBody().accept(methodBodyVisitor)
        )

    }
}

class ConditionVisitor : JavaClassBaseVisitor<JavaItem>() {
    //private val methodBodyVisitor = MethodBodyVisitor()
    override fun visitLoop(ctx: JavaClassParser.LoopContext): JavaItem {
        return JavaItem.While(ctx.condition().bool().text, ctx.methodBody().accept(MethodBodyVisitor()))
    }

    override fun visitIfelse(ctx: JavaClassParser.IfelseContext): JavaItem {
        return JavaItem.IfElse(
            ctx.condIf().condition().bool().text, ctx.condIf().methodBody().accept(MethodBodyVisitor()),
            ctx.condElse()?.text ?: "", ctx.condElse().methodBody().accept(MethodBodyVisitor())
        )
    }
}

class MethodBodyVisitor : JavaClassBaseVisitor<List<JavaItem>>() {
    private val funcCallVisitor = FuncCallVisitor()
    private val methodVariableVisitor = MethodVariableVisitor()
    private val methodReturnVisitor = MethodReturnVisitor()
    private val conditionVisitor = ConditionVisitor()
    override fun visitMethodBody(ctx: JavaClassParser.MethodBodyContext): List<JavaItem> {
        if (ctx.childCount == 0) {
            return listOf()
        }
        return ctx.children.map {
            return@map when (it) {
                is JavaClassParser.MethodVariableContext -> {
                    it.accept(methodVariableVisitor) as JavaItem
                }
                is JavaClassParser.MethodFuncCallContext -> {
                    it.accept(funcCallVisitor) as JavaItem
                }
                is JavaClassParser.MethodReturnContext -> {
                    it.accept(methodReturnVisitor) as JavaItem
                }
                is JavaClassParser.LoopContext, is JavaClassParser.IfelseContext -> {
                    it.accept(conditionVisitor)
                }
                else -> {
                    throw IllegalArgumentException()
                }
            }
        }
    }
}

class MethodReturnVisitor : JavaClassBaseVisitor<JavaItem.Return>() {
    override fun visitMethodReturn(ctx: JavaClassParser.MethodReturnContext): JavaItem.Return {
        return JavaItem.Return(ctx.name()?.text ?: "")
    }
}

class ArgumentVisitor : JavaClassBaseVisitor<Argument>() {
    override fun visitArgument(ctx: JavaClassParser.ArgumentContext): Argument {
        return Argument(ctx.type().text, ctx.name().text)
    }
}

private fun getModifiers(ctx: JavaClassParser.ModifierContext): String {
    return join(" ", ctx.accessModifier().text, ctx.staticModifier().text, ctx.finalModifier().text)
}