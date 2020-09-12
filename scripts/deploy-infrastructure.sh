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
        "Team=$TEAM" \
    --tags \
        "team=${TEAM}" \
        "service=${SERVICE}"
