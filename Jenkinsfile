pipeline {
  agent any
  stages {
    stage('Clone Repository') {
      steps {
        git(url: 'https://github.com/AhmedRoul/Rent-Bike-System-API-with-Microservices.git', branch: 'main', changelog: true, poll: true, credentialsId: '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')
        echo 'Repository cloned successfully'
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