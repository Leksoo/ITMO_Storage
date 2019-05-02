cmake_minimum_required(VERSION 3.12)

include_directories(../lib_rational)

project(lib_polynomial)

add_library(lib_polynomial polynomial.cpp polynomial.h)

target_link_libraries(lib_polynomial lib_rational)

get_property(
	LIB_TYPE
	TARGET lib_polynomial
	PROPERTY TYPE)
	
if(LIB_TYPE STREQUAL SHARED_LIBRARY)
	install(
	TARGETS lib_polynomial
	COMPONENT user
	RUNTIME
	DESTINATION bin
	LIBRARY
	DESTINATION lib)
endif()

install(
	TARGETS lib_polynomial
	EXPORT lib_polynomial
	COMPONENT developer
	RUNTIME
	DESTINATION bin
	LIBRARY
	DESTINATION lib
	ARCHIVE
	DESTINATION lib)
	
install(
	DIRECTORY .
	DESTINATION include
	COMPONENT developer
	FILES_MATCHING
	PATTERN "*.h")
	
install(
	EXPORT lib_polynomial
	DESTINATION share
	COMPONENT developer) 


