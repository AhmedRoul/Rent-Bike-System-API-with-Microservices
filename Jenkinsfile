pipeline {
  agent any
  stages {
    stage('Clone Repository') {
      steps {
        git(url: ' git \'https://github.com/AhmedRoul/Rent-Bike-System-API-with-Microservices.git\'', branch: 'main')
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