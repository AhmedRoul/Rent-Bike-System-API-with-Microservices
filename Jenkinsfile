pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        script {
          sh 'cd Rent Bike System API with Microservices/BikeService && mvn clean compile'
        }

      }
    }

  }
}