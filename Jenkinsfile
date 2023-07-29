// -noinspection GroovyAssignabilityCheck

pipeline {
    agent any

    stages {
        stage('Test') {
            steps{
                script {
                    dir('Test'){
                        checkout scm
                        sh 'pwd'
                    }

                    sh 'cd ..'
                    sh 'pwd'
                }
            }
        }
    }
}