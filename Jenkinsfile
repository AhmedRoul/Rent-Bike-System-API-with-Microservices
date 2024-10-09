pipeline {
  agent any
  stages {
    stage('Check out') {
      steps {
        script {
          checkout scm
        }

      }
    }

    stage('Build') {
      steps {
        script {
          bat "cd ${env.WORKSPACE}/Rent Bike System API with Microservices/BikeService && mvn clean compile"
        }

        echo 'Build successfully '
      }
    }

  }
}