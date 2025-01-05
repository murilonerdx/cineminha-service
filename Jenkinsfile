pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Executa o build do projeto usando o mvnw
                sh 'chmod +x mvnw'
                sh './mvnw clean install'
            }
        }

        stage('Run Backend Service Locally') {
            steps {
                // Subir o serviço backend localmente, permitindo o acesso no localhost
                echo 'Iniciando o serviço Spring Boot em modo produção...'
                sh './mvnw cineminha-service:run -Dspring.profiles.active=prod'
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
