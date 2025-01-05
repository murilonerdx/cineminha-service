// Exemplo de Jenkinsfile
pipeline {

       stages {
           stage('Checkout') {
               steps {
                   script {
                       checkout scm
                   }
               }
           }

           stage('Build and Test') {
               steps {
                   script {
                       sh 'mvn clean package'
                   }
               }
           }

           stage('Build Docker Image') {
               steps {
                   script {
                       sh 'docker build -t your-docker-repo/your-app:latest .'
                   }
               }
           }

    post {
        always {
            echo 'Pipeline finalizada.'
        }
    }
}
