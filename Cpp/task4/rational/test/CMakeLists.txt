cmake_minimum_required(VERSION 3.12)

include_directories(../lib_rational ../lib_polynomial)

project(my_test)

add_executable(my_test my_test.cpp)

target_link_libraries(my_test lib_rational lib_polynomial)

add_test(
	NAME my_test_1
	COMMAND my_test 1 1/2 1/2 1 1 equal
	)
	
add_test(
	NAME my_test_2
	COMMAND my_test 3 1 3 1/6 1/10 1/5 -1/5 bigger
	)
	
add_test(
	NAME my_test_3
	COMMAND my_test 3 1 1 1 1 1/5 1/2 smaller
	)
