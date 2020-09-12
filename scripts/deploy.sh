#!/bin/bash

set -euo pipefail
MYDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "$MYDIR/parameters.sh"

export VERSION=1 #change version everytime you deploy it

aws cloudformation deploy \
    --no-fail-on-empty-changeset \
    --template-file "${MYDIR}/../deploy/stack.yaml" \
    --capabilities CAPABILITY_IAM \
    --stack-name "$SERVICE" \
    --parameter-overrides \
        "Service=${SERVICE}" \
        "Version=${VERSION}" \
    --tags \
        "team=${TEAM}" \
        "service=${SERVICE}"
