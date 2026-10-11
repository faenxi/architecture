#!/bin/sh
set -eu

PROJECT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
BUILD_DIR="$PROJECT_DIR/build"
MAIN_CLASSES="$BUILD_DIR/classes"
SOURCE_LIST="$BUILD_DIR/sources.txt"

compile_project() {
    mkdir -p "$MAIN_CLASSES"
    find "$PROJECT_DIR/src" -name '*.java' -type f | sort > "$SOURCE_LIST"
    javac -encoding UTF-8 -d "$MAIN_CLASSES" @"$SOURCE_LIST"
}

case "${1:-demo}" in
    demo)
        compile_project
        java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 \
            -Dsun.stderr.encoding=UTF-8 -cp "$MAIN_CLASSES" shop.Main
        ;;
    test)
        compile_project
        java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 \
            -Dsun.stderr.encoding=UTF-8 -cp "$MAIN_CLASSES" shop.Lab5Test
        java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 \
            -Dsun.stderr.encoding=UTF-8 -cp "$MAIN_CLASSES" shop.StructuralPatternsTest
        ;;
    javadoc)
        mkdir -p "$PROJECT_DIR/docs"
        find "$PROJECT_DIR/src" -name '*.java' -type f | sort > "$SOURCE_LIST"
        javadoc -quiet -encoding UTF-8 -charset UTF-8 -docencoding UTF-8 \
            -d "$PROJECT_DIR/docs" @"$SOURCE_LIST"
        echo "Javadoc створено: $PROJECT_DIR/docs/index.html"
        ;;
    clean)
        find "$BUILD_DIR" -type f -delete 2>/dev/null || true
        find "$BUILD_DIR" -depth -type d -empty -delete 2>/dev/null || true
        ;;
    *)
        echo "Використання: ./run.sh [demo|test|javadoc|clean]" >&2
        exit 2
        ;;
esac
