#----------------------------------------------------------------
# Generated CMake target import file.
#----------------------------------------------------------------

# Commands may need to know the format version.
set(CMAKE_IMPORT_FILE_VERSION 1)

# Import target "lib_polynomial" for configuration ""
set_property(TARGET lib_polynomial APPEND PROPERTY IMPORTED_CONFIGURATIONS NOCONFIG)
set_target_properties(lib_polynomial PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_NOCONFIG "CXX"
  IMPORTED_LOCATION_NOCONFIG "${_IMPORT_PREFIX}/lib/liblib_polynomial.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS lib_polynomial )
list(APPEND _IMPORT_CHECK_FILES_FOR_lib_polynomial "${_IMPORT_PREFIX}/lib/liblib_polynomial.a" )

# Commands beyond this point should not need to know the version.
set(CMAKE_IMPORT_FILE_VERSION)
