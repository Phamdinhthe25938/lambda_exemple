#!/bin/bash
set -eo pipefail
aws cloudformation deploy --stack-name my-s3-lambda-stack --template-file template.yml
