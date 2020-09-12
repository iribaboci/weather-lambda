

pipeline {
    agent none

    options {
        timestamps()
        timeout(time: 2, unit: 'HOURS')
        buildDiscarder(logRotator(daysToKeepStr: '90'))
        preserveStashes(buildCount: 50)
    }

    environment {
        VERSION = getInvokedBuildNumber()
    }

    stages {
        stage('Deploy Infrastructure') {
            when {
                beforeAgent true
                branch 'master'
            }
            agent { node { label 'deploy-as24prod' } }
            steps {
                script {
                    sh 'scripts/deploy-infrastructure.sh'
                }
            }
        }
        stage('Build') {
            agent { node { label 'build-docker' } }
            steps {
                script {
                    dockerfile('Dockerfile.build').inside {
                        caching('~/.sbt', '~/.ivy2/cache', '~/.cache/coursier') {
                            sh 'scripts/build.sh'
                            if (env.BRANCH_NAME == 'master') {
                                sh 'scripts/publish.sh'
                            }
                        }
                    }
                    stash includes: 'deploy/**,scripts/**', name: 'deploy'
                }
            }
            post {
                always {
                    junit '**/target/test-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
            when { branch 'master' }
            steps {
                lock(resource: 'weather-lambda', inversePrecedence: true) {
                    script {
                        milestone ordinal: 1, label: 'weather-lambda'
                        node("deploy-as24prod") {
                            unstash 'deploy'
                            sh "scripts/deploy.sh"
                            sh "scripts/deploy-dashboard.sh"
                        }
                    }
                }
            }
        }

    }

    post {
        failure {
            script {
                if (env.BRANCH_NAME == 'master') {
                    slackSend channel: 'template-slack-channel', color: 'danger',
                              message: "The pipeline <${env.BUILD_URL}|${currentBuild.fullDisplayName}> failed."
                }
            }
        }
    }

}
