mkdir build
cd build

cmake^
 -G "MinGW Makefiles"^
 -D CMAKE_INSTALL_PREFIX=C:\programming\cplusplus\cplusplus_3_sem\task4\_install^
 C:\programming\cplusplus\cplusplus_3_sem\task4\rational

mingw32-make 
 
cmake -D COMPONENT=developer -P cmake_install.cmake

cd..