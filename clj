#!/bin/sh

# clj - Clojure launcher script

clh_home="$HOME/projects/dev/clojure"
cljjar="$clh_home/clojure.jar"
clj_lib_dir="$clh_home/lib"

jlinejar="$HOME/projects/dev/jline-0.9.94/jline-0.9.94.jar"

cljclass='clojure.main'
jlineclass='jline.ConsoleRunner'

dir=$0
while [ -h "$dir" ]; do
  ls=`ls -ld "$dir"`
  link=`expr "$ls" : '.*-> \(.*\)$'`

  if expr "$link" : '/.*' > /dev/null; then
    dir="$link"
  else
    dir=`dirname "$dir"`"/$link"
  fi
done

dir=`dirname $dir`
dir=`cd "$dir" > /dev/null && pwd`
cp="${PWD}:${jlinejar}:${cljjar}"

if [ -f $clj_lib_dir ]; then
  for i in $clj_lib_dir/* ; do
    cp=$cp:$i
  done
fi

# Add extra jars as specified by `.clojure` file
# Borrowed from <http://github.com/mreid/clojure-framework>
if [ -f .clojure ]; then
  cp=$cp:`cat .clojure`
fi

if [ -z "$1" ]; then
  exec java -classpath $cp $jlineclass $cljclass
else
  scriptname=$1
  exec java -classpath $cp $jlineclass $cljclass $scriptname -- $*
fi
