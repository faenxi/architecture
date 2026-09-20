#!/usr/bin/env bash
set -euo pipefail

project_dir="$(cd "$(dirname "$0")" && pwd)"
build_dir="$project_dir/build"
classes_dir="$build_dir/classes"
test_classes_dir="$build_dir/test-classes"
command_name="${1:-lab1}"

compile_main() {
    mkdir -p "$classes_dir"
    find "$project_dir/src/main/java" -name '*.java' -print0 \
        | xargs -0 javac -encoding UTF-8 -Xlint:all -Werror -d "$classes_dir"
}

compile_tests() {
    compile_main
    mkdir -p "$test_classes_dir"
    find "$project_dir/src/test/java" -name '*.java' -print0 \
        | xargs -0 javac -encoding UTF-8 -Xlint:all -Werror \
            -cp "$classes_dir" -d "$test_classes_dir"
}

case "$command_name" in
    lab1)
        compile_main
        java -Dfile.encoding=UTF-8 -cp "$classes_dir" ua.edu.shopcart.demo.Lab1Demo
        ;;
    lab2)
        compile_main
        java -Dfile.encoding=UTF-8 -cp "$classes_dir" ua.edu.shopcart.demo.Lab2Demo
        ;;
    test)
        compile_tests
        java -Dfile.encoding=UTF-8 -cp "$classes_dir:$test_classes_dir" ua.edu.shopcart.SelfTest
        ;;
    javadoc)
        rm -rf "$project_dir/docs/javadoc"
        javadoc -encoding UTF-8 -charset UTF-8 -docencoding UTF-8 \
            -private -Xdoclint:all -d "$project_dir/docs/javadoc" \
            -sourcepath "$project_dir/src/main/java" \
            -subpackages ua.edu.shopcart
        ;;
    *)
        echo "Використання: ./run.sh {lab1|lab2|test|javadoc}" >&2
        exit 2
        ;;
esac
