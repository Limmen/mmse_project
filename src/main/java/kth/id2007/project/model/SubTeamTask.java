package kth.id2007.project.model;

public class SubTeamTask {
	private long projectRefrenceId;
	private String description;
	private String assignedTo;
	private int priority;
	
	public SubTeamTask(long projectRefrenceId, String description, String assignedTo, int priority) {
		super();
		this.projectRefrenceId = projectRefrenceId;
		this.description = description;
		this.assignedTo = assignedTo;
		this.priority = priority;
	}

	public long getProjectRefrenceId() {
		return projectRefrenceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
}