pipeline {
  agent any
  stages {
    stage('Clone Repository') {
      steps {
        sh ''' if (!fileExists(\'Rent-Bike-System-API-with-Microservices/.git\')) {
 echo \'Repository not found. Cloning...\'
 git url: \'https://github.com/AhmedRoul/Rent-Bike-System-API-with-Microservices.git\', branch: \'main\', credentialsId: \'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\'
} 
else {
  echo \'Repository already exists. Skipping clone.\'
  }'''
          echo 'done step !'
        }
      }

    }
  }