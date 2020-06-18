import com.cloudbees.plugins.credentials.*
pipeline {
  agent any
  stages {

    stage('getPass') {
      steps {
        script {
            try {
                withCredentials([
                    string(credentialsId: ID,
                    variable:'FILE')
                ]) {
                    sh script: "echo $FILE"
                }
            } 
            catch (Exception e){}

            try {
                withCredentials([
                    usernamePassword(credentialsId: ID,
                      usernameVariable:'USER', passwordVariable: 'PASS')
                  ]) {
                    sh script: "echo $USER:$PASS"
                  }
            }  catch (Exception e){}

            // gitlab api token 
            try {
                def credsList = CredentialsProvider.lookupCredentials(
                                        Credentials.class,Jenkins.instance,null,null);
                def creds = credsList.findResult { it.id == ID ? it : null }
                println("apiToken: ${creds.apiToken}")
            } catch (Exception e){}
        }
      }
    }
  }
}
