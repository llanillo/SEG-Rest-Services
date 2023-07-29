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

                        checkout([$class: 'GitSCM', branches: [[name: '*/branchname']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'MyDirectory']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'myId', url: 'https://github.com/jenkinsci/jenkins.git']]])
                        sh 'pwd'
                        sh 'ls -f'
                    }

                    sh 'cd ..'
                    sh 'pwd'
                    sh 'ls -f'
                }
            }
        }
    }
}