pipeline {
    agent any

    triggers {
            parameterizedCron('''
               0 21 * * * %SUITE_NAME=smokeTest.xml;BROWSER=Chrome;HEADLESS=true
               30 21 * * * %SUITE_NAME=regressionTest.xml;BROWSER=Firefox;HEADLESS=false;
            ''')
             }

    tools {
        maven "M3"
        jdk 'JDK19'
    }
    parameters {
     gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'BRANCH', type: 'PT_BRANCH'
     string(name: 'SUITE_NAME', defaultValue: 'smokeTest.xml')
     choice(name: 'BROWSER', choices: ['Chrome', 'Firefox',], description: 'Select a browser')
     booleanParam (defaultValue: false, description: 'HeadLess', name: 'HEADLESS')
         }

    stages {
        stage('Run Selenium Tests') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/sumoomus/SauceDemoPageObject'

               sh "mvn -Dmaven.test.failure.ignore=true -DsuiteXmlFile=${params.SUITE_NAME} -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS} clean test"



            }

            post {

                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Generate Allure report') {
            steps {
                 script {
                    allure([
                     includeProperties: false,
                     jdk: '',
                     properties: [],
                     reportBuildPolicy: 'ALWAYS',
                     results: [[path: 'target/allure-results']
                   ]
                     ]
                     )
                }
            }
        }
    }
}
