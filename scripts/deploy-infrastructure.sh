#!/bin/bash

set -euo pipefail
MYDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "$MYDIR/parameters.sh"

aws cloudformation deploy \
    --no-fail-on-empty-changeset \
    --template-file "${MYDIR}/../deploy/stack-infrastructure.yaml" \
    --stack-name "${SERVICE}-infrastructure" \
    --parameter-overrides \
        "Service=$SERVICE" \
        "UseCase=$USECASE" \
        "Segment=$SEGMENT" \
        "Team=$TEAM" \
        "Vertical=$VERTICAL" \
    --tags \
        "vertical=${VERTICAL}" \
        "segment=${SEGMENT}" \
        "usecase=${USECASE}" \
        "team=${TEAM}" \
        "service=${SERVICE}"
