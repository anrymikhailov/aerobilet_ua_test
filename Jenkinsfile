#!groovy

node('master') {
    stage('Checkout') {
        checkout scm
    }    
    stage('Run tests') {
            sh 'set +e'
            sh 'mvn clean test'
            sh 'set -e'
    }
    
    stage('reports') {
    allure includeProperties: false, jdk: '', results: [[path: '/var/jenkins_home/workspace/aerobilet_ua_test/allure-report']]
}
}