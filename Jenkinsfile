pipeline {
    agent any

    environment {
       SPRING_PROFILES_ACTIVE = 'prod'
        DB_HOST = "${env.DB_HOST}"
        DB_PORT = "${env.DB_PORT}"
        DB_NAME = "${env.DB_NAME}"
        DB_USER = "${env.DB_USER}"
        DB_PASSWORD = "${env.DB_PASSWORD}"
    }

    stages {
        stage('Variables') {
               steps {
                       // Exibe os valores das variáveis de ambiente
                       echo "${DB_HOST}"
                       echo "${DB_PORT}"
                       echo "${DB_NAME}"
                       echo "${DB_USER}"
                       echo "${DB_PASSWORD}"
                   }
            }
        stage('Build') {
            steps {
                // Executa o build do projeto usando o mvnw
                sh 'chmod +x mvnw'
                sh './mvnw clean install -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}'
            }
        }

        stage('Run Backend Service Locally') {
            steps {
                // Subir o serviço backend localmente, permitindo o acesso no localhost
                echo 'Iniciando o serviço Spring Boot em modo produção...'
                sh './mvnw clean install -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}'
            }
        }

        stage('Deploy to Production (Local)') {
            steps {
                // Caso queira algo adicional para o "deploy" (por exemplo, rodar o JAR diretamente)
                echo 'Rodando o backend Spring Boot em modo produção...'
                // Executa o JAR gerado no build em modo produção
                sh 'java -jar target/cineminha-0.0.1-SNAPSHOT.jar &'
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
