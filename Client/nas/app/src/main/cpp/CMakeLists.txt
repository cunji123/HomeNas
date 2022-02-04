cmake_minimum_required(VERSION 3.10.2)

project("nas")
add_library(
        nas
        SHARED
        native-lib.cpp
)
add_library(
        netWork_utils
        SHARED
        netWork_utils-lib.cpp
)

find_library(
        log-lib
        log
)

target_link_libraries(
        nas
        ${log-lib}
)
target_link_libraries(
        netWork_utils
        ${log-lib}
)