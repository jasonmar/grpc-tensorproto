#!/usr/bin/env bash
# Copies proto files from tensorflow repositories

err () {
	echo "$@"
	exit 1
}

[ ! -z "$SERVING_DIR" ] || err "missing SERVING_DIR"
[ ! -z "$TF_DIR" ] || err "missing TF_DIR"
[ ! -z "$OUT_DIR" ] || OUT_DIR="proto"
[ ! -z "$TMP_DIR" ] || TMP_DIR="."

[ -d "$TMP_DIR" ] || mkdir -p $TMP_DIR
[ -d "$OUT_DIR" ] || mkdir -p $OUT_DIR

get_protos () {
	PROTO_DIR=$1
	OUT_DIR=$2
	cd $PROTO_DIR
	find ./ -name "*.proto" > $TMP_DIR/protos.lst
	while read f; do
		f1=$(echo $f | sed -e 's|^\./||')
		d=$(dirname $f1)
		src="$PROTO_DIR/$f1"
		dest="$OUT_DIR/$d/"
		mkdir -p "$dest"
		cp -v "$src" "$dest"
	done<$TMP_DIR/protos.lst
}

for d in $TF_DIR $SERVING_DIR; do
	get_protos $d $OUT_DIR
done

