def call (namespace, BuildConfigName, buildNumber, buildUser, branchName){
	script {
		openshift.withCluster('neo-cluster') {
			openshift.withProject("$namespace"){
			
				try {
					def result = openshift.raw("new-build --name=$BuildConfigName --strategy=docker --binary")
					echo "Cluster status: ${result.out}"
				}
				catch(Exception e) {
					echo "Error Was Thrown while new build"
				}
				
					def StartBuild = openshift.startBuild("$BuildConfigName", "--from-dir=.")
					def result = StartBuild.logs('-f')
	
			}
		}
	}

