#!/usr/bin/bash

# Use este script para executar testes locais

RESULTS_WORKSPACE="D:\Projects\rinha-backend-java\rinhabackendedicao2\gatling\load-test\user-files\results"
GATLING_BIN_DIR="D:\development\gatling-charts-highcharts-bundle-3.10.4\bin"
GATLING_WORKSPACE="D:\Projects\rinha-backend-java\rinhabackendedicao2\gatling\load-test\user-files"

runGatling() {
    sh $GATLING_BIN_DIR/gatling.sh -rm local -s RinhaBackendCrebitosSimulation \
        -rd "Rinha de Backend - 2024/Q1: Crébito" \
        -rf $RESULTS_WORKSPACE \
        -sf "$GATLING_WORKSPACE/simulations"
}

startTest() {
    for i in {1..20}; do
        curl --fail http://localhost:8080/clientes/1/extrato && \
        echo "" && \
        runGatling && \
        break || sleep 2;
    done
}

startTest
