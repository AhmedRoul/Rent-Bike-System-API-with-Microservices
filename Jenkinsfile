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
          bat "cd ${env.WORKSPACE}/Rent Bike System API with Microservices/${serviceName} && mvn test"
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

}
}