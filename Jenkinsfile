pipeline {
    agent {
        label 'master'
    }
    environment {
        registry = 'souravcoder99/user-service-dockerapp'
        registryCredential = 'docker-cred'
        dockerImage = ''
    }

    stages {

        stage('Sonarqube Analysis') {
            steps {
                    withSonarQubeEnv('SonarQube') {
                        sh '''
                        cd user-service
                        mvn clean verify sonar:sonar -Dsonar.projectKey=user-service
                        '''
                    }
            }
        }
        // stage('Quality gate') {
        //     steps {
        //         waitForQualityGate abortPipeline: true
        //     }
        // }
        

        stage ('Exec Maven') {
            steps {
               sh '''
               cd user-service
               mvn package
               '''
            }
        }

        stage('Building image') {
            steps {
                script {
                    dockerImage = docker.build registry
                }
            }
        }

        stage('Upload Image') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('docker stop container') {
            steps {
                sh 'docker ps -f name=user-service -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -fname=user-service -q | xargs -r docker container rm'
            }
        }
        stage('Docker Run') {
            steps {
                script {
                    dockerImage.run('-p 4001:4001 --rm --name user-service')
                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
