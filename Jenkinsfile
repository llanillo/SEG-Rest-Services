// -noinspection GroovyAssignabilityCheck

pipeline {
    agent any

    options {
        skipDefaultCheckout true
    }

    stages {
        stage('Test') {
            steps{
                script {
                    dir('Root'){
                        checkout scmGit(branches: [[name: '*/master']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'test']], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/llanillo/SEG-UNT-Rest-Services']])

                        sh 'ls'
                    }

                    sh 'cd test/'
                    sh 'pwd'
                    sh 'ls'
                }
            }
        }
    }
}