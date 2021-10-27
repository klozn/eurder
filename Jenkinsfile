pipeline {
    agent any
        tools {
            jdk 'jdk-16'
            maven 'Maven'
        }

    stages {
        stage('Get Git') {
            steps {
                checkout scm
            }
        }
        stage('Build 🏗') {
            steps {
                sh 'mvn clean install'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
    }
}