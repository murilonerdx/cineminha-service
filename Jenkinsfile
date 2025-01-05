// Exemplo de Jenkinsfile
pipeline {
    agent any

    environment {
        // Definir variáveis de ambiente, se necessário
        DOCKER_IMAGE = 'cineminha:spring-boot'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    sh 'chmod +x ./mvnw'
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    echo 'Executando testes...'
                    // Rodar os testes
                    sh './mvnw test'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Criando a imagem Docker...'
                    // Construir a imagem Docker
                    sh 'docker build -t $DOCKER_IMAGE .'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo 'Deploy da aplicação...'
                    // Executar o deploy, pode ser no servidor local ou outro
                    sh 'docker run -d -p 8080:8080 $DOCKER_IMAGE'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finalizada.'
        }
    }
}
