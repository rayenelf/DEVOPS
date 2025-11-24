pipeline {
    agent any

    environment {
           IMAGE_NAME = "rayenelf/mon-projet"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/rayenelf/DEVOPS.git',
                    credentialsId: 'github-cred'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${BUILD_NUMBER} ."
            }
        }

        stage('Login DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-cred', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                sh "docker push ${IMAGE_NAME}:${BUILD_NUMBER}"
            }
        }
    }

    post {
        success {
            echo "✅ Build & Push terminé: ${IMAGE_NAME}:${BUILD_NUMBER}"
        }
        failure {
            echo "❌ Echec du build"
        }
    }
}
