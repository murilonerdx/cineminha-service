pipeline {
    agent any

    environment {
        SPRING_PROFILES_ACTIVE = 'prod'
        DB_HOST = "${env.DB_HOST}"
        DB_PORT = "${env.DB_PORT}"
        DB_NAME = "${env.DB_NAME}"
        DB_USER = "${env.DB_USER}"
        DB_PASSWORD = "${env.DB_PASSWORD}"
        DOCKER_IMAGE = 'docker.io/murilonerdx/cineminha-backend' // Ajuste com o seu nome de usuário no Docker Hub
        VERSION = "1.0.${BUILD_ID}"
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'  // ID das credenciais no Jenkins
    }

    stages {
        stage('Build') {
            steps {
                // Executa o build do projeto usando o mvnw
                sh 'chmod +x mvnw'
                sh './mvnw clean install -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}'
            }
        }

        stage('Clean install') {
            steps {
                // Subir o serviço backend localmente, permitindo o acesso no localhost
                echo 'Iniciando clean install no serviço Spring Boot em modo produção...'
                sh './mvnw clean install -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Construir a imagem Docker
                    sh 'docker build -t ${DOCKER_IMAGE}:${VERSION} .'
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    // Logar no Docker Hub usando as credenciais configuradas no Jenkins
                    docker.withRegistry('https://registry.hub.docker.com', "${DOCKER_CREDENTIALS_ID}") {
                        echo 'Login no Docker Hub realizado com sucesso!'
                    }
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    // Publicar a imagem no Docker Hub
                    docker.withRegistry('https://registry.hub.docker.com', "${DOCKER_CREDENTIALS_ID}") {
                        sh "docker push ${DOCKER_IMAGE}:${VERSION}"
                    }
                }
            }
        }

        stage('Stop Existing Service (if any)') {
            steps {
                script {
                    // Verificar se existe um container rodando com a imagem e matá-lo
                    sh '''
                    EXISTING_CONTAINER=$(docker ps -q --filter "ancestor=${DOCKER_IMAGE}:${VERSION}")
                    if [ -n "$EXISTING_CONTAINER" ]; then
                        echo "Parando o container existente..."
                        docker stop $EXISTING_CONTAINER
                        docker rm $EXISTING_CONTAINER
                    else
                        echo "Nenhum container encontrado para parar."
                    fi
                    '''
                }
            }
        }

        stage('Run Backend Service in Docker') {
            steps {
                script {
                    // Rodar o serviço backend dentro de um container Docker
                    sh '''
                    docker run -d -p 8088:8088 \
                    --env DB_HOST=${DB_HOST} \
                    --env DB_PORT=${DB_PORT} \
                    --env DB_NAME=${DB_NAME} \
                    --env DB_USER=${DB_USER} \
                    --env DB_PASSWORD=${DB_PASSWORD} \
                    ${DOCKER_IMAGE}:${VERSION}
                    '''
                }
            }
        }
    }

    post {
        always {
            // Aqui podem ser incluídas ações de limpeza, relatórios ou notificações após a execução
            echo 'Pipeline executada!'
        }
    }
}
