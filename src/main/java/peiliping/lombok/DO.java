package peiliping.lombok;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude="name")
public class DO {

	@Getter
	@Setter 
	private long id;

	@Getter
	@Setter
	private String name;
}
