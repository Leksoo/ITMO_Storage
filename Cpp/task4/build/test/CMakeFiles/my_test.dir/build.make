# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.13

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\CMake\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\CMake\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\programming\cplusplus\cplusplus_3_sem\task4\rational

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\programming\cplusplus\cplusplus_3_sem\task4\build

# Include any dependencies generated for this target.
include test/CMakeFiles/my_test.dir/depend.make

# Include the progress variables for this target.
include test/CMakeFiles/my_test.dir/progress.make

# Include the compile flags for this target's objects.
include test/CMakeFiles/my_test.dir/flags.make

test/CMakeFiles/my_test.dir/my_test.cpp.obj: test/CMakeFiles/my_test.dir/flags.make
test/CMakeFiles/my_test.dir/my_test.cpp.obj: test/CMakeFiles/my_test.dir/includes_CXX.rsp
test/CMakeFiles/my_test.dir/my_test.cpp.obj: C:/programming/cplusplus/cplusplus_3_sem/task4/rational/test/my_test.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\programming\cplusplus\cplusplus_3_sem\task4\build\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object test/CMakeFiles/my_test.dir/my_test.cpp.obj"
	cd /d C:\programming\cplusplus\cplusplus_3_sem\task4\build\test && C:\MinGW\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\my_test.dir\my_test.cpp.obj -c C:\programming\cplusplus\cplusplus_3_sem\task4\rational\test\my_test.cpp

test/CMakeFiles/my_test.dir/my_test.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/my_test.dir/my_test.cpp.i"
	cd /d C:\programming\cplusplus\cplusplus_3_sem\task4\build\test && C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\programming\cplusplus\cplusplus_3_sem\task4\rational\test\my_test.cpp > CMakeFiles\my_test.dir\my_test.cpp.i

test/CMakeFiles/my_test.dir/my_test.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/my_test.dir/my_test.cpp.s"
	cd /d C:\programming\cplusplus\cplusplus_3_sem\task4\build\test && C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\programming\cplusplus\cplusplus_3_sem\task4\rational\test\my_test.cpp -o CMakeFiles\my_test.dir\my_test.cpp.s

# Object files for target my_test
my_test_OBJECTS = \
"CMakeFiles/my_test.dir/my_test.cpp.obj"

# External object files for target my_test
my_test_EXTERNAL_OBJECTS =

test/my_test.exe: test/CMakeFiles/my_test.dir/my_test.cpp.obj
test/my_test.exe: test/CMakeFiles/my_test.dir/build.make
test/my_test.exe: lib_rational/liblib_rational.a
test/my_test.exe: lib_polynomial/liblib_polynomial.a
test/my_test.exe: lib_rational/liblib_rational.a
test/my_test.exe: test/CMakeFiles/my_test.dir/linklibs.rsp
test/my_test.exe: test/CMakeFiles/my_test.dir/objects1.rsp
test/my_test.exe: test/CMakeFiles/my_test.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\programming\cplusplus\cplusplus_3_sem\task4\build\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable my_test.exe"
	cd /d C:\programming\cplusplus\cplusplus_3_sem\task4\build\test && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\my_test.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
test/CMakeFiles/my_test.dir/build: test/my_test.exe

.PHONY : test/CMakeFiles/my_test.dir/build

test/CMakeFiles/my_test.dir/clean:
	cd /d C:\programming\cplusplus\cplusplus_3_sem\task4\build\test && $(CMAKE_COMMAND) -P CMakeFiles\my_test.dir\cmake_clean.cmake
.PHONY : test/CMakeFiles/my_test.dir/clean

test/CMakeFiles/my_test.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\programming\cplusplus\cplusplus_3_sem\task4\rational C:\programming\cplusplus\cplusplus_3_sem\task4\rational\test C:\programming\cplusplus\cplusplus_3_sem\task4\build C:\programming\cplusplus\cplusplus_3_sem\task4\build\test C:\programming\cplusplus\cplusplus_3_sem\task4\build\test\CMakeFiles\my_test.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : test/CMakeFiles/my_test.dir/depend

