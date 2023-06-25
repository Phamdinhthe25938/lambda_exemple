#!/bin/bash
set -eo pipefail
aws cloudformation package --template-file template.yml --s3-bucket test-bucket-lambda-dev-01 --output-template-file out.yml
aws cloudformation deploy --template-file out.yml --stack-name lambda-test-trigger --capabilities CAPABILITY_NAMED_IAM