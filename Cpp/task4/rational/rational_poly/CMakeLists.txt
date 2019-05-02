cmake_minimum_required(VERSION 3.12)

include_directories(../lib_rational ../lib_polynomial)

project(rational_poly)

add_executable(rational_poly rational_poly.cpp)

target_link_libraries(rational_poly lib_rational lib_polynomial)

install(
	TARGETS rational_poly
	COMPONENT user
	DESTINATION bin)
install(
	TARGETS rational_poly
	COMPONENT developer
	DESTINATION bin)



