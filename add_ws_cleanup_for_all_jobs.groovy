// this snippet creates a post-build cleanup step for all jobs which don't have a cleanup step yet

Jenkins.instance.getAllItems(Job.class).each{ item ->
  //item = Jenkins.instance.getItemByFullName(job.getName())
  ws = new hudson.plugins.ws_cleanup.WsCleanup()
  def hasCleanWS = false
  item.getPublishersList().each() {
    if(it.getClass() == hudson.plugins.ws_cleanup.WsCleanup){
      hasCleanWS = true
    }
  }
  if(!hasCleanWS){
    item.getPublishersList().add(ws)
	//item.save()
  }
  //println(item.getName()+":"+hasCleanWS)
}

