pipeline {
  agent any
  stages {
    stage('Clone Repository') {
      steps {
        git(url: ' git \'https://github.com/AhmedRoul/Rent-Bike-System-API-with-Microservices.git\'', branch: 'main', credentialsId: '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', poll: true)
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