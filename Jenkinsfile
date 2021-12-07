pipeline {
    agent {
        label 'master'
    }
    environment {
        
        DOCKERHUB_CREDENTIALS=credentials('docker-cred')
        dockerImage = ''
    }

    stages {

        stage('Sonarqube Analysis') {
            steps {
                    withSonarQubeEnv('SonarQube') {
                        sh '''
                        cd $microservice
                        mvn clean verify sonar:sonar -Dsonar.projectKey=$microservice
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
               cd $microservice
               mvn package
               '''
            }
        }

        stage('Building image') {
            steps {
                    sh '''
                    cd $microservice
                    docker build -t souravcoder99/$microservice-dockerapp:latest .
                    '''
                
            }
        }
        stage('Login') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}

        stage('Upload Image') {
            steps {
                    sh 'docker push souravcoder99/$microservice-dockerapp:latest'
                }
            }
        stage('Spinning Containers') {
            steps {
                    sh '''
                    docker-compose down
                    docker-compose build
                    docker-compose up -d
                    '''
                }
            }
        }
       

        // stage('docker stop container') {
        //     steps {
        //         sh 'docker ps -f name=user-service -q | xargs --no-run-if-empty docker container stop'
        //         sh 'docker container ls -a -fname=user-service -q | xargs -r docker container rm'
        //     }
        // }
        // stage('Docker Run') {
        //     steps {
        //         script {
        //             dockerImage.run('-p 4001:4001 --rm --name user-service')
        //         }
        //     }
        // }
    
    post {
        always {
            cleanWs()
            sh 'docker logout'
        }
    }
}
