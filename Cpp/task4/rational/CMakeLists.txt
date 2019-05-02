cmake_minimum_required(VERSION 3.12)

project(task4)

set(BUILD_SHARED_LIBS TRUE)

enable_testing()

add_subdirectory(lib_rational)
add_subdirectory(lib_polynomial)
add_subdirectory(rational_poly)
add_subdirectory(test)
