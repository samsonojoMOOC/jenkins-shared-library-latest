#!/user/bin/env groovy

def call() {

    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-credential', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        sh 'docker build -t samsonojo/demo-app:jmal-2.1 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push samsonojo/demo-app:jmal-2.1'
    }
}