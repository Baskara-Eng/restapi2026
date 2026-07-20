pipeline 
{
    agent any
    
    tools{
    	maven 'maven'
        }
        
        
          stages 
              {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
             
             
                
                
        stage('Run regression tests') {
    steps {
			catchError(buildResult:'SUCCESS', stageResult:'FAILURE'){
			git 'https://github.com/Baskara-Eng/restapi2026'
			sh "mvn clean install "
			
		}
		
		
		
		stage('Publish Allure Report'){
            steps{
                     script{
					 allure([
					 includeProperties:false,
					 jdk:'',
					 properties:[],
					 reportBuildPolicy: 'ALWAYS',
					 results: [[path: '/allure-results']]
					 
					 ])
            }
        }

    }
}
}
}
}