package ru.ifmo.rain.feponov.implementor;

import info.kgeorgiy.java.advanced.implementor.ImplerException;
import info.kgeorgiy.java.advanced.implementor.JarImpler;

import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;

/**
 * Implementation of {@link JarImpler}
 */
public class Implementor implements JarImpler {

    /**
     * Extension of generated source file
     */
    private static final String EXTENSION_JAVA = ".java";
    /**
     * Extension of compiled file
     */
    private static final String EXTENSION_CLASS = ".class";
    /**
     * Name ending of generated class
     */
    private static final String REQUIRED_CLASS_ENDING = "Impl";
    /**
     * Syntax keyword
     */
    private static final String IMPLEMENTS_KEYWORD = "implements";
    /**
     * Syntax keyword
     */
    private static final String EXTENDS_KEYWORD = "extends";
    /**
     * Syntax keyword
     */
    private static final String CLASS_KEYWORD = "class";
    /**
     * Syntax keyword
     */
    private static final String THROWS_KEYWORD = "throws";
    /**
     * Syntax keyword
     */
    private static final String PACKAGE_KEYWORD = "package";
    /**
     * Syntax keyword
     */
    private static final String RETURN_KEYWORD = "return";
    /**
     * String equals to 4 spaces
     */
    private static final String TAB = "    ";

    /**
     * Empty constructor
     */
    public Implementor(){}


    /**
     * Main function of {@link Implementor}.
     * <ul>
     * <li> 2 args (className, rootPath) : execute {@link #implement(Class, Path)}</li>
     * <li> 3 args (-jar, className, pathToJar) : execute {@link #implementJar(Class, Path)}</li>
     * </ul>
     * In case of error prints error message
     *
     * @param args arguments for executing the app
     */
    public static void main(String[] args) {
        if (args == null || (args.length != 1 && args.length != 3)) {
            System.err.println("Error: Wrong argument format in main function");
            return;
        }
        for (var arg : args) {
            if (arg == null) {
                System.err.println("Error: Null argument in main function");
                return;
            }
        }
        var implementor = new Implementor();
        try {
            if (args.length == 1) {
                implementor.implement(Class.forName(args[0]), Paths.get("root"));
            } else if (args[0].equals("-jar")) {
                implementor.implementJar(Class.forName(args[1]), Paths.get(args[2]));
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        } catch (InvalidPathException e) {
            System.out.println("Invalid path: " + e.getMessage());
        } catch (ImplerException e) {
            System.err.println("Error while implementing: " + e.getMessage());
        }

    }

    /**
     * Builds the {@link Path} starting in {@code root} according to {@code root} package name
     *
     * @param root            {@link Path} start point
     * @param token           {@link Class} from which package name is extracted
     * @param extension       defines the extension of path target file.
     *                        Could be {@link #EXTENSION_CLASS} or {@link #EXTENSION_JAVA}
     * @param needToCreateDir {@link Boolean} defines if directories on the path will be created
     * @return resolved {@link Path} to file and creates directories if {@code needToCreateDir} is true
     * @throws IOException if couldn't create directories or resolve path
     */
    private Path getDestinationPath(Path root, Class<?> token,
                                    String extension, Boolean needToCreateDir) throws IOException {
        var path = root;
        if (token.getPackage() != null) {
            path = root.resolve(token.getPackageName().replace(".", File.separator));
            if (needToCreateDir) {
                Files.createDirectories(path);
            }
        }
        return path.resolve(token.getSimpleName() + REQUIRED_CLASS_ENDING + extension);

    }

    /**
     * Converts given {@link String} to Unicode Escaped
     *
     * @param str {@link String} to convert
     * @return Unicode Escaped {@link String}
     */
    private String unicodeEscape(String str) {
        var sb = new StringBuilder();
        for (var c : str.toCharArray()) {
            if (c >= 128) {
                sb.append(String.format("\\u%04X", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * @throws ImplerException for one of the following reasons:
     *                         <ul>
     *                         <li> One of the arguments is <tt>null</tt></li>
     *                         <li> Given <tt>class</tt> cannot be implemented</li>
     *                         <li> Problem while writing to target file resolved by {@link #getDestinationPath(Path, Class, String, Boolean)}</li>
     *                         </ul>
     */
    @Override
    public void implement(Class<?> token, Path root) throws ImplerException {
        if (token == null || root == null) {
            throw new ImplerException("Error : null argument");
        }
        if (token == Enum.class || Modifier.isFinal(token.getModifiers())
                || token.isPrimitive() || token.isArray()) {
            throw new ImplerException("Error: cannot implement this type of class");
        }
        String inheritanceType = IMPLEMENTS_KEYWORD;
        if (!token.isInterface()) {
            inheritanceType = EXTENDS_KEYWORD;
        }
        try (var out = Files.newBufferedWriter(
                getDestinationPath(root, token, EXTENSION_JAVA, true))) {
            out.write(unicodeEscape(getPackage(token) +
                    "\n\n" +
                    getClassName(token, inheritanceType) +
                    "{" +
                    "\n" +
                    getConstructors(token) +
                    "\n" +
                    getMethods(token) +
                    "\n" +
                    "}"));
        } catch (IOException e) {
            throw new ImplerException("Error writing to file: " + e.getMessage());
        }

    }

    /**
     * @throws ImplerException for one of the following reasons:
     *                         <ul>
     *                         <li> {@link #implement(Class, Path)} threw {@link ImplerException} </li>
     *                         <li> Problem while getting path to <tt>.class</tt> or <tt>.java</tt> files</li>
     *                         <li> {@link #createJarFile(Path, String, Path)} threw {@link ImplerException}</li>
     *                         </ul>
     */
    @Override
    public void implementJar(Class<?> token, Path pathToJar) throws ImplerException {
        try {
            var root = Paths.get("");
            implement(token, root);
            var createdJavaFilePath = getDestinationPath(root, token, EXTENSION_JAVA, false);
            compileFiles(root, createdJavaFilePath);
            var compiledJavaFilePath = getDestinationPath(root, token, EXTENSION_CLASS, false);
            var pathInJar = token.getName().replace('.', '/') + REQUIRED_CLASS_ENDING + EXTENSION_CLASS;
            createJarFile(pathToJar, pathInJar, compiledJavaFilePath);
        } catch (IOException e) {
            throw new ImplerException("Error: problem with path while creating files" + e.getMessage());
        }
    }

    /**
     * Writes file from <tt>filePath</tt> to given <tt>.jar</tt> file from <tt>pathToJar</tt>.
     *
     * @param pathToJar {@link Path} to <tt>.jar</tt> file
     * @param pathInJar {@link Path} to file that will be in <tt>.jar</tt> file
     * @param filePath  {@link Path} to target file that will be copied to <tt>.jar</tt> file
     * @throws ImplerException if problem happened while writing to <tt>.jar</tt> file
     */
    private void createJarFile(Path pathToJar, String pathInJar, Path filePath) throws ImplerException {
        var manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        try (var out = new JarOutputStream(Files.newOutputStream(pathToJar), manifest)) {
            out.putNextEntry(new ZipEntry(pathInJar));
            Files.copy(filePath, out);
            out.closeEntry();
        } catch (IOException e) {
            throw new ImplerException("Error: cannot create jar file" + e.getMessage());
        }
    }

    /**
     * Compiles given <tt>.java</tt> file from {@link Path} pathToFile to <tt>.class</tt> file.
     * Created <tt>.class</tt> file will be stored in the same directory as <tt>.java</tt> file
     *
     * @param root       {@link Path} to root directory that will be passed to <tt>-cp</tt> option
     * @param pathToFile {@link Path} to <tt>.java</tt> file
     * @throws ImplerException if compilation didn't succeed
     */
    private void compileFiles(Path root, Path pathToFile) throws ImplerException {
        var compiler = ToolProvider.getSystemJavaCompiler();
        var args = new String[3];
        args[0] = "-cp";
        args[1] = root + File.pathSeparator + System.getProperty("java.class.path");
        args[2] = pathToFile.toString();
        if (compiler == null || compiler.run(null, null, null, args) != 0) {
            throw new ImplerException("Error: file was not compiled");
        }
    }


    //------------------------PACKAGE AND CLASS DECLARATION------------------------

    /**
     * Extracts package name from given {@link Class} token
     *
     * @param token {@link Class} from which package is extracted
     * @return package name as {@link String} in java syntax format
     */
    private String getPackage(Class<?> token) {
        if (token.getPackage() == null) {
            return "";
        }
        return String.format("%s %s;", PACKAGE_KEYWORD, token.getPackage().getName());
    }

    /**
     * Extracts class name from give {@link Class} token
     *
     * @param token           {@link Class} from which name is extracted
     * @param inheritanceType defines inheritance keyword.
     *                        <ul>
     *                        <li> {@link #EXTENDS_KEYWORD} if <tt>token</tt> is class</li>
     *                        <li> {@link #IMPLEMENTS_KEYWORD} if <tt>token</tt> is interface</li>
     *                        </ul>
     * @return class name as {@link String} in java syntax format
     */
    private String getClassName(Class<?> token, String inheritanceType) {
        return String.format("%s %s %s %s",
                CLASS_KEYWORD, token.getSimpleName() + REQUIRED_CLASS_ENDING
                , inheritanceType, token.getSimpleName());
    }


    //--------------------------METHODS---------------------------

    /**
     * Collects {@link Method} from <tt>methods</tt> to <tt>setOfMethods</tt>.
     * Only abstract methods will be collected. {@link Method} will be mapped to {@link InheritedMethod}
     *
     * @param setOfMethods {@link Set} where methods will be collected
     * @param methods      {@link Method}[] from which methods will be taken
     */
    private void collectAbstractMethods(Set<InheritedMethod> setOfMethods, Method[] methods) {
        Stream.of(methods)
                .filter(it -> Modifier.isAbstract(it.getModifiers()))
                .map(InheritedMethod::new)
                .collect(Collectors.toCollection(() -> setOfMethods));

    }

    /**
     * Extracts methods from given {@link Class} token. Collects all <tt>abstract</tt> methods in
     * inheritance hierarchy of given {@link Class} token. Then converts them to {@link String} according
     * to java syntax format of methods.
     *
     * @param token {@link Class} from which methods are extracted
     * @return methods as {@link String} in java syntax format
     */
    private String getMethods(Class<?> token) {
        var methods = new HashSet<InheritedMethod>();
        collectAbstractMethods(methods, token.getMethods());
        while (token != null) {
            collectAbstractMethods(methods, token.getDeclaredMethods());
            token = token.getSuperclass();
        }
        return methods.stream()
                .map(it -> {
                    var m = it.value;
                    return String.format(
                            "%s%s %s %s(%s) %s %s",
                            tab(1), getExecModifiers(m), m.getReturnType().getCanonicalName(),
                            m.getName(), getArguments(m, false), getExceptions(m),
                            getMethodBody(m));
                })
                .collect(Collectors.joining("\n\n"));
    }

    /**
     * Creates {@link Method} body according to given <tt>method.getReturnType()</tt>.
     * Created body always returns default value.
     *
     * @param method {@link Method} from which <tt>return type</tt> will be taken
     * @return method body as {@link String} in java syntax format
     */
    private String getMethodBody(Method method) {
        var returnType = method.getReturnType();
        String body;
        if (returnType.equals(void.class)) {
            body = "";
        } else if (returnType.equals(boolean.class)) {
            body = "true";
        } else if (returnType.isPrimitive()) {
            body = "0";
        } else {
            body = "null";
        }
        return String.format("{%n%s%s %s;%n}", tab(2), RETURN_KEYWORD, body);

    }


    //--------------------------CONSTRUCTORS---------------------------

    /**
     * Extracts constructors from given {@link Class} token. Collects all not <tt>private</tt>
     * constructors of given {@link Class} token. Then converts them to {@link String} according
     * to java syntax format of constructors.
     *
     * @param token {@link Class} from which constructors are extracted
     * @return constructors as {@link String} in java syntax format
     * @throws ImplerException if all constructors are <tt>private</tt>
     */
    private String getConstructors(Class<?> token) throws ImplerException {
        if (token.isInterface()) return "";
        var constructorName = token.getSimpleName() + REQUIRED_CLASS_ENDING;
        var result = Stream.of(token.getDeclaredConstructors())
                .filter(it -> !Modifier.isPrivate(it.getModifiers()))
                .map(it -> String.format(
                        "%s%s %s(%s) %s{ super(%s); }",
                        tab(1), getExecModifiers(it), constructorName,
                        getArguments(it, false), getExceptions(it),
                        getArguments(it, true)))
                .collect(Collectors.joining("\n\n"));
        if (result.isEmpty()) {
            throw new ImplerException("Error: all constructors are private");
        }
        return result;
    }


    //-----------------------UTILITY FOR METHODS AND CONSTRUCTORS--------------------------------

    /**
     * Extracts all exceptions that can be thrown from {@link Executable}.
     * Then converts them to {@link String}.
     *
     * @param exec {@link Executable} from which exceptions are extracted
     * @return list of exceptions as {@link String} splitted by space with {@link #THROWS_KEYWORD}
     */
    private String getExceptions(Executable exec) {
        var exceptions = exec.getExceptionTypes();
        var str = "";
        if (exceptions.length != 0) {
            str = String.format("%s %s", THROWS_KEYWORD,
                    Stream.of(exceptions).map(Class::getCanonicalName)
                            .collect(Collectors.joining(", ")));
        }
        return str;
    }

    /**
     * Extracts all arguments of {@link Executable}.
     * Then converts them to {@link String}.
     *
     * @param exec      {@link Executable} from which arguments are extracted
     * @param onlyNames if <tt>true</tt> arguments without parameter types will be extracted
     * @return list of arguments as {@link String} in java syntax format
     */
    private String getArguments(Executable exec, Boolean onlyNames) {
        var arguments = exec.getParameters();
        return Stream.of(arguments)
                .map(it -> String.format("%s %s", onlyNames ? "" : it.getType().getCanonicalName(), it.getName()))
                .collect(Collectors.joining(", "));
    }

    /**
     * Extracts all modifiers of given {@link Executable}.
     * Removes <tt>native</tt>, <tt>abstract</tt>, <tt>transient</tt>  modifiers.
     * Then converts them to {@link String}.
     *
     * @param exec {@link Executable} from which modifiers are extracted
     * @return list of modifiers as {@link String} splitted by space
     */
    private String getExecModifiers(Executable exec) {
        return Modifier.toString(exec.getModifiers()
                & ~(Modifier.NATIVE | Modifier.ABSTRACT | Modifier.TRANSIENT));
    }

    /**
     * Concatinates {@link #TAB} <tt>count</tt> times
     * @param count number of {@link #TAB}
     * @return {@link String} as concatination of {@link #TAB}
     */
    private String tab(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(TAB);
        }
        return sb.toString();
    }

    /**
     * Wrapper of {@link Method} used for correct equality comparison
     */
    private static class InheritedMethod {
        /**
         * Base for evaluating hashcode
         */
        private static final int HASH_BASE = 47;
        /**
         * Instance of {@link Method}
         */
        private Method value;

        /**
         * Constructs {@link InheritedMethod} from instance of {@link Method}.
         * @param value instance of {@link Method} to create {@link InheritedMethod}
         */
        InheritedMethod(Method value) {
            this.value = value;
        }

        /**
         * Compares given object with this instance of {@link InheritedMethod} for equality.
         * {@link InheritedMethod} are equal, if {@link Method} {@link #value}
         * have equal name, return type and parameter's types.
         *
         * @param o object to be compared with this instance of {@link InheritedMethod}
         * @return true if given object is equal to this instance of {@link InheritedMethod}
         */
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            InheritedMethod that = (InheritedMethod) o;
            if (!value.getName().equals(that.value.getName())) return false;
            if (!value.getReturnType().equals(that.value.getReturnType())) return false;
            return Arrays.equals(value.getParameterTypes(), that.value.getParameterTypes());
        }

        /**
         * Calculates polynomial hashcode based on hash of name, return type and parameter's types of {@link #value}
         *
         * @return hashcode for this instance of {@link InheritedMethod}
         */
        @Override
        public int hashCode() {
            return value.getName().hashCode() + value.getReturnType().hashCode() * HASH_BASE
                    + Arrays.hashCode(value.getParameterTypes()) * HASH_BASE * HASH_BASE;
        }
    }

}












































































