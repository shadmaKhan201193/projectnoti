@Library('sharedlibrary') _
pipeline {
    agent any
    tools {
        gradle "7.4.2"
        jdk "JDK17"
        dockerTool "docker"
        maven "maven-3.8.5"
    }
    stages {
        stage("Build") {
            steps {
                script{
                    build()
                }
            }
        }
        stage("Scan") {
            steps {
                script {
                    sonarScan()
                }
            }
        }
        stage("dockerfile") {
            steps {
                script {
                    dockerfile()
                }
            }
        }
        stage("deployonkiyavm48") {
            steps {
                script {
                    standaloneDeployment("172.21.0.128",["8888:8080"])                
                }
            }
        }
        stage("deployment-k8s") {
			when {
				anyOf {
					branch 'Notification_Shalu'
					branch 'main'
					}
				}
            steps {
                script {
                    helm()
                }
            }
        }
    }
}

