pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        script {
          sh 'cd $WORKSPACE/Rent Bike System API with Microservices/BikeService && mvn clean compile'
        }

        echo 'sdada'
      }
    }

  }
}