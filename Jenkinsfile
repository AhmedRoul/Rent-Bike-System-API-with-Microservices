pipeline {
  agent any
  stages {
    stage('Check out') {
      steps {
        echo 'Check out successfully '
      }
    }

    stage('Build') {
      steps {
        script {
          def services=["BikeService", "api-gateway" ,"UserService",
          "config-server" ,"register-server","RentalRecordService"]

          // Loop through each service and build it
          services.each { serviceName ->
          bat "cd ${env.WORKSPACE}/Rent Bike System API with Microservices/${serviceName} && mvn clean compile"
        }
      }

      echo 'Build successfully '
    }
  }

  stage('Unit Tests') {
    steps {
      echo 'Unit Tests successfully'
      script {
        def services=["BikeService" ,"UserService","RentalRecordService"]

        // Loop through each service and build it
        services.each {
          serviceName ->
          bat "cd ${env.WORKSPACE}/Rent Bike System API with Microservices/${serviceName} && mvn test -Dspring.profiles.active=dev,default"
        }
      }

    }
  }

  stage('Packages ') {
    steps {
      script {
        def services=["BikeService", "api-gateway" ,"UserService",
        "config-server" ,"register-server","RentalRecordService"]

        // Loop through each service and build it
        services.each { serviceName ->
        bat "cd ${env.WORKSPACE}/Rent Bike System API with Microservices/${serviceName} && mvn package"
      }
    }

    echo 'Packages successfully '
  }
}

stage('Build Docker Images') {
  steps {
    script {
      bat "docker build -t ${DOCKER_IMAGE_PREFIX}:register-server ./register-server"
      bat "docker build -t ${DOCKER_IMAGE_PREFIX}:api-gateway ./api-gateway"
      bat "docker build -t ${DOCKER_IMAGE_PREFIX}:auth-server ./auth-server"
      bat "docker build -t ${DOCKER_IMAGE_PREFIX}:bike-server ./BikeService"
      bat "docker build -t ${DOCKER_IMAGE_PREFIX}:jwt-server ./JWT-server"
      bat "docker build -t ${DOCKER_IMAGE_PREFIX}:user-server ./UserService"
    }

  }
}

stage('Docker Login') {
  steps {
    script {
      sh "echo ${DOCKER_HUB_CREDENTIALS_PSW} | docker login -u ${DOCKER_HUB_CREDENTIALS_USR} --password-stdin"
    }

  }
}

stage('Docker Compose Up') {
  steps {
    script {
      sh 'docker-compose up -d'
    }

  }
}

}
environment {
registry = 'roular/rent-bike-system'
DOCKER_HUB_CREDENTIALS = 'credentials(\'docker-roular\')'
DOCKER_IMAGE_PREFIX = 'roular/rent-bike-system'
}
}