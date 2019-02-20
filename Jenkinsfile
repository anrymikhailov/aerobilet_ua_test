#!groovy

node('master') {
    stage('Checkout') {
        checkout scm
    }    
    stage('Run tests') {
            sh 'mvn clean test'
    }
    
    stage('reports') {
    steps {
	    script {
	            allure([
	                    includeProperties: false,
	                    jdk: '',
	                    properties: [],
	                    reportBuildPolicy: 'ALWAYS',
	                    results: [[path: '/var/jenkins_home/workspace/aerobilet_ua_test_maven/allure-report']]
	            ])
	    }
    }
}
}