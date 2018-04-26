# customs-inventory-linking-imports-performance-test

Performance tests for the `customs-inventory-linking-imports` service.

To run tests locally: 

    sbt -Djava.io.tmpdir=${WORKSPACE}/tmp -DrunLocal=true test 

To run a smoke test locally: 

    sbt -Dperftest.runSmokeTest=true -Djava.io.tmpdir=${WORKSPACE}/tmp -DrunLocal=true test

To run against deployed applications in a managed environment (e.g. staging etc) replace `-DrunLocal=true` with `-DrunLocal=false` in above examples.  

[For more details about configuring or running](https://github.com/hmrc/performance-test-runner)

# seeding api-subscription-fields data

You will have to make sure when running locally that you have seeded data for clientId `d9210257-7cfc-49eb-a6cc-0a6e6c26c4cb`
This can be done with the following command:

    curl -v -X PUT "http://localhost:9650/field/application/d9210257-7cfc-49eb-a6cc-0a6e6c26c4cb/context/customs%2Finventory-linking-imports/version/1.0" -H "Cache-Control: no-cache" -H "Content-Type: application/json" -d '{ "fields" : { "callbackUrl" : "https://postman-echo.com/post", "securityToken" : "securityToken" } }'