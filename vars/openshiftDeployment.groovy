def call (clusterName, projectName, kustomEnv, dcName)
 {
	script {
		   openshift.withCluster("$clusterName") {
						openshift.withProject("$projectName") {
								sh """kustomize build '$kustomEnv' > tmp.yaml"""
								def result = openshift.raw("apply -f tmp.yaml")
								//   echo "Cluster status: ${result.out}"
		 						def dc = openshift.selector('dc', "${dcName}")
								 	dc.rollout().latest()
									dc.rollout().status()
						 }
					 }
		}
	}
	
