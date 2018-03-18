#!/usr/bin/env bash
# You need to pass the full path to GRPC_PLUGIN
# GRPC_PLUGIN=/path/to/protoc-gen-grpc-java ./protoc.bash

[ ! -z "$PROTOC" ] || PROTOC="protoc"
[ ! -z "$GRPC_PLUGIN" ] || exit 1

# .proto imports
PROTO_INCLUDE="proto"

# .proto files to be compiled
PROTO_PATH="src/main/resources/proto"

# List all .proto files in resources/proto
PROTOS=$(find $PROTO_PATH -name '*.proto' | sed -e 's|.*/||')

OUT_DIR="src/main/java"
[ -d "$OUT_DIR" ] || mkdir -p "$OUT_DIR"

$PROTOC \
  --proto_path="$PROTO_INCLUDE" \
  --proto_path="$PROTO_PATH" \
  --java_out="$OUT_DIR" \
  --grpc_out="$OUT_DIR" \
  --plugin=protoc-gen-grpc=$GRPC_PLUGIN \
  $PROTOS $USER_PROTOS
