#!/usr/bin/env bash

set -euo pipefail
MYDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "$MYDIR/parameters.sh"

SERVICE_METRIC_PREFIX=${SERVICE//[^[:alnum:]]/_} # Datadog converts all non-alpha characters on metric names to underscore.

aws cloudformation deploy \
    --stack-name "${SERVICE}-dashboard" \
    --template-file "${MYDIR}/../deploy/stack-dashboard.yaml" \
    --no-fail-on-empty-changeset \
    --parameter-overrides \
        "Service=${SERVICE}" \
        "ServiceMetricPrefix=${SERVICE_METRIC_PREFIX}" \
    --tags \
        "vertical=${VERTICAL}" \
        "segment=${SEGMENT}" \
        "usecase=${USECASE}" \
        "team=${TEAM}" \
        "service=${SERVICE}"
