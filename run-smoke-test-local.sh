#!/usr/bin/env bash
sbt -Dperftest.runSmokeTest=true -Dperftest.labels=init -Djava.io.tmpdir=${WORKSPACE}/tmp -DrunLocal=true clean test