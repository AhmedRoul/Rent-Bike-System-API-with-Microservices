pipeline {
  agent any
  stages {
    stage('Clone Repository') {
      steps {
        sh '''if (!fileExists(\'Rent-Bike-System-API-with-Microservices/.git\')) {
    git url: \'https://github.com/AhmedRoul/Rent-Bike-System-API-with-Microservices.git\',
        branch: \'main\', 
        credentialsId: \'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\'
    echo \'Repository cloned for the first time.\'
} else {
    echo \'Repository already exists.\'
}'''
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