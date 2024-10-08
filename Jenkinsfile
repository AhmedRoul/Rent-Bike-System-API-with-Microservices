pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        script {
          bat "cd ${env.WORKSPACE}/Rent Bike System API with Microservices/BikeService && mvn clean compile"
        }

        echo 'sdada'
      }
    }

  }
}