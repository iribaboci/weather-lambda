#!/bin/bash

set -euo pipefail
MYDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "$MYDIR/parameters.sh"

# Lint & validate CF templates
for STACK in ${MYDIR}/../deploy/stack*.y*ml; do
    cfn-lint "$STACK"
    aws cloudformation validate-template --template-body "file://${STACK}"
done

sbt "all test universal:packageBin"
