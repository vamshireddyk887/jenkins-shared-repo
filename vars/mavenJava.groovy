def call(String repoUrl) {
    pipeline{
  agent any
   environment {
        CC = 'java'
    }
    parameters {
        string(name: 'branch', defaultValue: 'main', description: 'give branch')
        choice(name: 'choice', choices: ['main', 'second'], description: 'choose branch')
    }
    stages{
        stage('Checkout'){
        steps{
            //echo "${params.branch} for one"
            //echo "${params.choice} among two"
            git branch: 'main', url: ${repoUrl}
        }
    }
    stage('Code Test'){
        steps{
            sh '''export PATH=/mnt/c/Program\\ Files/apache-maven-3.9.2/bin:$PATH
                  mvn test'''
        }
    }
    stage('Code Build'){
        steps{
            sh '''export PATH=/mnt/c/Program\\ Files/apache-maven-3.9.2/bin:$PATH
                  mvn clean install'''
        }
    }
    //stage('CodeQualityAnalysis'){
      //  steps{
        //    sh '''export PATH=/mnt/c/Program\\ Files/apache-maven-3.9.2/bin:$PATH
                  //mvn clean verify sonar:sonar -Dsonar.projectKey=java-base-project -Dsonar.projectName=java-base-project -Dsonar.login=3c7dc24279c3b1b6657eaabf184fd6d912ad8520'''
        //}
    //}
    //stage('SnapshotBuild'){
      //  steps{
        //    sh '''export PATH=/mnt/c/Program\\ Files/apache-maven-3.9.2/bin:$PATH
          //        mvn deploy -s settings.xml'''
        //}
    //}
    //stage('ReleaseArtifact'){
      //  steps{
        //    sh '''export PATH=/mnt/c/Program\\ Files/apache-maven-3.9.2/bin:$PATH
          //        mvn -B release:prepare -s settings.xml
            //      mvn -B release:perform -s settings.xml'''
        //}
    //}
  }
}
}
