pipeline {
  agent any
  stages {
    stage('Clone Repository') {
      steps {
        fileExists 'Rent-Bike-System-API-with-Microservices/.git'
        echo 'done'
      }
    }

    stage('Test Stages') {
      steps {
        echo 'Done'
      }
    }

    stage('Deploy') {
      steps {
        echo 'run '
      }
    }

  }
}