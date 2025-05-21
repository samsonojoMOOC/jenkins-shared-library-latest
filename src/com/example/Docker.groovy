#!/user/bin/env groovy

package com.example

class Docker implements Serializable {
    def script

    Docker(script){
        this.script = script
    }

    def dockerBuildImage(String imageName){
        script.echo "building the docker image..."
           script.sh "docker build -t $imageName ."
        }


    def dockerLogin(){
        script.echo "docker login..."
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-credential', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
            script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin"
        }
    }

    def dockerPushImage(String imageName){
        script.echo "pushing the docker image..."
            script.sh "docker push $imageName"
        }
}