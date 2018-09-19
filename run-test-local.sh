#!/usr/bin/env bash
sbt -Djava.io.tmpdir=${WORKSPACE}/tmp -DrunLocal=true clean test