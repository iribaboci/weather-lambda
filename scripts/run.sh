#!/bin/bash

sh "deploy-infrastructure.sh"
sh "build.sh"
sh "publish.sh"
sh "deploy.sh"