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
include rational_poly/CMakeFiles/rational_poly.dir/depend.make

# Include the progress variables for this target.
include rational_poly/CMakeFiles/rational_poly.dir/progress.make

# Include the compile flags for this target's objects.
include rational_poly/CMakeFiles/rational_poly.dir/flags.make

rational_poly/CMakeFiles/rational_poly.dir/rational_poly.cpp.obj: rational_poly/CMakeFiles/rational_poly.dir/flags.make
rational_poly/CMakeFiles/rational_poly.dir/rational_poly.cpp.obj: rational_poly/CMakeFiles/rational_poly.dir/includes_CXX.rsp
rational_poly/CMakeFiles/rational_poly.dir/rational_poly.cpp.obj: C:/programming/cplusplus/cplusplus_3_sem/task4/rational/rational_poly/rational_poly.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\programming\cplusplus\cplusplus_3_sem\task4\build\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object rational_poly/CMakeFiles/rational_poly.dir/rational_poly.cpp.obj"
	cd /d C:\programming\cplusplus\cplusplus_3_sem\task4\build\rational_poly && C:\MinGW\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\rational_poly.dir\rational_poly.cpp.obj -c C:\programming\cplusplus\cplusplus_3_sem\task4\rational\rational_poly\rational_poly.cpp

rational_poly/CMakeFiles/rational_poly.dir/rational_poly.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/rational_poly.dir/rational_poly.cpp.i"
	cd /d C:\programming\cplusplus\cplusplus_3_sem\task4\build\rational_poly && C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\programming\cplusplus\cplusplus_3_sem\task4\rational\rational_poly\rational_poly.cpp > CMakeFiles\rational_poly.dir\rational_poly.cpp.i

rational_poly/CMakeFiles/rational_poly.dir/rational_poly.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/rational_poly.dir/rational_poly.cpp.s"
	cd /d C:\programming\cplusplus\cplusplus_3_sem\task4\build\rational_poly && C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\programming\cplusplus\cplusplus_3_sem\task4\rational\rational_poly\rational_poly.cpp -o CMakeFiles\rational_poly.dir\rational_poly.cpp.s

# Object files for target rational_poly
rational_poly_OBJECTS = \
"CMakeFiles/rational_poly.dir/rational_poly.cpp.obj"

# External object files for target rational_poly
rational_poly_EXTERNAL_OBJECTS =

rational_poly/rational_poly.exe: rational_poly/CMakeFiles/rational_poly.dir/rational_poly.cpp.obj
rational_poly/rational_poly.exe: rational_poly/CMakeFiles/rational_poly.dir/build.make
rational_poly/rational_poly.exe: lib_rational/liblib_rational.a
rational_poly/rational_poly.exe: lib_polynomial/liblib_polynomial.a
rational_poly/rational_poly.exe: lib_rational/liblib_rational.a
rational_poly/rational_poly.exe: rational_poly/CMakeFiles/rational_poly.dir/linklibs.rsp
rational_poly/rational_poly.exe: rational_poly/CMakeFiles/rational_poly.dir/objects1.rsp
rational_poly/rational_poly.exe: rational_poly/CMakeFiles/rational_poly.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\programming\cplusplus\cplusplus_3_sem\task4\build\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable rational_poly.exe"
	cd /d C:\programming\cplusplus\cplusplus_3_sem\task4\build\rational_poly && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\rational_poly.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
rational_poly/CMakeFiles/rational_poly.dir/build: rational_poly/rational_poly.exe

.PHONY : rational_poly/CMakeFiles/rational_poly.dir/build

rational_poly/CMakeFiles/rational_poly.dir/clean:
	cd /d C:\programming\cplusplus\cplusplus_3_sem\task4\build\rational_poly && $(CMAKE_COMMAND) -P CMakeFiles\rational_poly.dir\cmake_clean.cmake
.PHONY : rational_poly/CMakeFiles/rational_poly.dir/clean

rational_poly/CMakeFiles/rational_poly.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\programming\cplusplus\cplusplus_3_sem\task4\rational C:\programming\cplusplus\cplusplus_3_sem\task4\rational\rational_poly C:\programming\cplusplus\cplusplus_3_sem\task4\build C:\programming\cplusplus\cplusplus_3_sem\task4\build\rational_poly C:\programming\cplusplus\cplusplus_3_sem\task4\build\rational_poly\CMakeFiles\rational_poly.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : rational_poly/CMakeFiles/rational_poly.dir/depend

