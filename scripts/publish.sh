#!/bin/bash

set -euo pipefail
MYDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "$MYDIR/parameters.sh"

aws s3 cp "${MYDIR}/../target/universal/${SERVICE}-${VERSION}.zip" "s3://${ARTIFACTS_BUCKET}/${SERVICE}-${VERSION}.zip" --acl bucket-owner-full-control
