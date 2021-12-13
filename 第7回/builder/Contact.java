package builder;

public class Contact {
	private String name;
	private String relation;

	public Contact(String name, String relation) {
		this.name = name ;
		this.relation = relation ;
	}

	public String toString(){
		return "\nContact:\n" + " Name: " + name + "\n" + " Relationship: " + relation;
	}

	public String getName() {
		return name ;
	}

	public String getRelation() {
		return relation ;
	}

	public void setName(String name) {
		this.name = name ;
	}

	public void setRelation(String relation) {
		this.relation = relation ;
	}
}