#!groovy

node('master') {
    stage('Checkout') {
        checkout scm
    }    
    stage('Run tests') {
            sh 'mvn clean test'
    }
    
    stage('reports') {
    allure includeProperties: false, jdk: '', results: [[path: '/var/jenkins_home/workspace/aerobilet_ua_test/allure-report']]
}
}